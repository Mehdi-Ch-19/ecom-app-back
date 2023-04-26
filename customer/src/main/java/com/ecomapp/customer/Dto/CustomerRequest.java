package com.ecomapp.customer.Dto;

import lombok.Data;

@Data
public class CustomerRequest {
    private String email;
    private String password;
}
