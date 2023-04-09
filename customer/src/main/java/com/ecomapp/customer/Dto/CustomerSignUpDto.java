package com.ecomapp.customer.Dto;

import com.ecomapp.customer.enums.Role;
import lombok.Data;

@Data
public class CustomerSignUpDto {
    private String name;
    private String email;
    private String password;
    private Role role;

}
