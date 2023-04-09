package com.ecomapp.customer.Dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CustomerDto {
    private Long id;
    private String name;
    private String email;
}
