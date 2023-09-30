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
    private String id;
    private CustomerModel customerModel;
    
}
