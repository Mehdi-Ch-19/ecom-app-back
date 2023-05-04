package com.ecomapp.feign.product;

import lombok.Data;

@Data
public class Product {
    private String id;
    private String productTitle;
    private String imageUrl;
    private String description;
    private int numReviews ;
    private double price;
    private int countInStock ;
}
