package com.ecomapp.feign.customer;

import lombok.Data;

@Data
public class Customer {
    private Long id;
    private String name;
    private String email;
}
