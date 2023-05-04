package com.ecomapp.feign.customer;

import lombok.Data;

@Data
public class CustomerRequest {
    private String email;
    private String password;
}
