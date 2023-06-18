package com.ecomapp.customer.Dto;

import com.ecomapp.customer.entity.Adresse;
import com.ecomapp.customer.entity.Role;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class CustomerDto {
    private Long id;
    private String name;
    private String email;
    private Adresse adresse;
    private List<Role> roles = new ArrayList<>();
}
