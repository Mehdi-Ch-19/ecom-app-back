package com.ecomapp.customer.Dto;

import com.ecomapp.customer.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRegistrationDto {
    private Long id;
    private String name;
    private String email;
    private boolean isAdmin;
    private List<Role> roles = new ArrayList<>();
}
