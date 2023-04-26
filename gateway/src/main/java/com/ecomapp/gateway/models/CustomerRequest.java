package com.ecomapp.gateway.models;

import lombok.Data;

@Data
public class CustomerRequest {
    private String email;
    private String password;
}
