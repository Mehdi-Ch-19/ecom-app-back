package com.ecomapp.auth.models;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class CustomerRegistrationResponce {
    private Long id;
    private String name;
    private String email;
    private boolean isAdmin;
}
