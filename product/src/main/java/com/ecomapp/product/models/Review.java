package com.ecomapp.product.models;

import lombok.Data;

@Data
public class Review {
    private Long id;
    private Long customer_id;
    private int rating;
    private String comment;
    private  String product_id;
}
