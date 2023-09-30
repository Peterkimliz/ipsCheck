package com.ips.ipsManager.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ips.ipsManager.Dtos.IpAddressDto;
import com.ips.ipsManager.Dtos.CustomerRequest;
import com.ips.ipsManager.Services.IpAddressService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/ip")
@Tag(name = "IpAddress")
public class IpAddressController {
    @Autowired
    private IpAddressService categoryService;

    @PutMapping("/create/{ipAddress}")
    public ResponseEntity<IpAddressDto> create(@PathVariable("ipAddress") String ip) {
        return new ResponseEntity<IpAddressDto>(categoryService.createCategory(ip), HttpStatus.CREATED);

    }
    @PutMapping("/release/{ipAddress}")
    public ResponseEntity<IpAddressDto> releaseIp(@PathVariable("ipAddress") String ip) {
        return new ResponseEntity<IpAddressDto>(categoryService.releaseIpAddress(ip), HttpStatus.OK);

    }

    @PostMapping("/allocate")
    public ResponseEntity<IpAddressDto> allocateIp(@RequestBody @Validated CustomerRequest customerRequest ) {
        return new ResponseEntity<IpAddressDto>(categoryService.allocateIp(customerRequest), HttpStatus.CREATED);

    }



    @GetMapping("/allocated/")
    public ResponseEntity<List<IpAddressDto>> getAllocatedIps() {
        return new ResponseEntity<List<IpAddressDto>>(categoryService.getAllCategories("allocated"), HttpStatus.OK);

    }

    @GetMapping("/available/")
    public ResponseEntity<List<IpAddressDto>> getAvailableIps() {
        return new ResponseEntity<List<IpAddressDto>>(categoryService.getAllIps(), HttpStatus.OK);

    }


}
