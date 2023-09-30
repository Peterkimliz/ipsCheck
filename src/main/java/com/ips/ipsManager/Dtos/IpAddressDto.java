package com.ips.ipsManager.Dtos;

import com.ips.ipsManager.Models.CustomerModel;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IpAddressDto {
    private String ipAddress; 
    private String status;
    private Long id;
    private CustomerModel customerModel;
    
}
