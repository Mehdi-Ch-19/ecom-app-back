package com.ecomapp.customer.Dto;

import com.ecomapp.customer.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSignUpDto {
    private String name;
    private String email;
    private String password;
    private boolean isAdmin;


}
