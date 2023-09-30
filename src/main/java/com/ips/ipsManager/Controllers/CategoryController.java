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

import com.ips.ipsManager.Dtos.CategoryDto;
import com.ips.ipsManager.Dtos.CustomerRequest;
import com.ips.ipsManager.Services.CategoryService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/ip")
@Tag(name = "Categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PutMapping("/create/{ipAddress}")
    public ResponseEntity<CategoryDto> create(@PathVariable("ipAddress") String ip) {
        return new ResponseEntity<CategoryDto>(categoryService.createCategory(ip), HttpStatus.CREATED);

    }
    @PutMapping("/release/{ipAddress}")
    public ResponseEntity<CategoryDto> releaseIp(@PathVariable("ipAddress") String ip) {
        return new ResponseEntity<CategoryDto>(categoryService.releaseIpAddress(ip), HttpStatus.OK);

    }

    @PostMapping("/allocate")
    public ResponseEntity<CategoryDto> allocateIp(@RequestBody @Validated CustomerRequest customerRequest ) {
        return new ResponseEntity<CategoryDto>(categoryService.allocateIp(customerRequest), HttpStatus.CREATED);

    }



    @GetMapping("/allocated/")
    public ResponseEntity<List<CategoryDto>> getAllocatedIps() {
        return new ResponseEntity<List<CategoryDto>>(categoryService.getAllCategories("allocated"), HttpStatus.OK);

    }

    @GetMapping("/available/")
    public ResponseEntity<List<CategoryDto>> getAvailableIps() {
        return new ResponseEntity<List<CategoryDto>>(categoryService.getAllIps(), HttpStatus.OK);

    }


}
