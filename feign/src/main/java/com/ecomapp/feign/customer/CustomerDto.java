package com.ecomapp.feign.customer;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class CustomerDto {

    private Long id  ;
    private String name;
    private String email;
    private String password;
    private boolean isAdmin;
    private List<RoleDto> roles = new ArrayList<>() ;
}
