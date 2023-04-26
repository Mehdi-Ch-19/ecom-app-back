package com.ecomapp.auth.models;

import lombok.Data;

@Data
public class CustomerRequest {
    private String email;
    private String password;
}
