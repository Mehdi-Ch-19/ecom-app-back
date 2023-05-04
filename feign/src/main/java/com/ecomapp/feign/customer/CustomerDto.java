package com.ecomapp.feign.customer;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CustomerDto {

    private Long id  ;
    private String name;
    private String email;
    private String password;
    private boolean isAdmin;
    private List<RoleDto> roles = new ArrayList<>() ;
}
