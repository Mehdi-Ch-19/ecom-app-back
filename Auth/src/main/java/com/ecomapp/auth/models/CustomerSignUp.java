package com.ecomapp.auth.models;

import lombok.Data;

@Data
public class CustomerSignUp {
    private String name;
    private String email;
    private String password;
    private boolean isAdmin;
}
