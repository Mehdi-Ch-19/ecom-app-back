package com.ecomapp.customer.Dto;

import com.ecomapp.customer.entity.Role;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CustomerRegistrationDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private boolean isAdmin;
    private List<Role> roles = new ArrayList<>();
}
