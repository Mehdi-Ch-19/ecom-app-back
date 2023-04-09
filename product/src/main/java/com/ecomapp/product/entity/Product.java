package com.ecomapp.product.entity;

import com.ecomapp.product.models.Review;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
public class Product {
    @Id
    private String id;
    private String name;
    private String image;
    private String description;
    private int numReviews = 0;
    private double price;
    private int countInStock ;
    @Transient
    private List<Review> reviews;
}
