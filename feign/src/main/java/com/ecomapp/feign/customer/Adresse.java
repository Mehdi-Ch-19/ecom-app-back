package com.ecomapp.feign.customer;

import lombok.Data;

@Data
public class Adresse {
    private Long id;
    private  String street;
    private String city;
    private String country;
}
