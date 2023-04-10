package com.ecomapp.product.models;

import lombok.Data;

@Data
public class Review {
    private Long id;
    private Long customerId;
    private int rating;
    private String comment;
    private  String productId;
}
