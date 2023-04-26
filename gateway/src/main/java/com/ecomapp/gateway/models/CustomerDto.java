package com.ecomapp.gateway.models;

import lombok.Data;

import java.util.Collection;

@Data
public class CustomerDto {

    private Long id  ;
    private String name;
    private String email;
    private String password;
    private Collection<RoleDto> roles ;
}
