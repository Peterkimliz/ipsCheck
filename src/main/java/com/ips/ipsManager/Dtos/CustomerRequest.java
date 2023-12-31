package com.ips.ipsManager.Dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CustomerRequest {
    @NotBlank(message = "Customer name required")
    private String customerName;

    @Email(message = "Please enter avalid email")
    @NotBlank(message = "Customer email required")
    private String email;
    
}
