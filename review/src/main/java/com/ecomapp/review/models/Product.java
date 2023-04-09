package com.ecomapp.review.models;

import lombok.Data;

@Data
public class Product {
    private String id;
    private String name;
    private String image;
    private String description;
    private int numReviews ;
    private double price;
    private int countInStock ;
}
