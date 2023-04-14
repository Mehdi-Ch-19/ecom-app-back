package com.ecomapp.product.entity;

import com.ecomapp.product.models.Review;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table( name = "products", uniqueConstraints = {@UniqueConstraint(columnNames = {"product_id"})})
public class Product {

    @Id
    @Column(name = "product_id", unique = true, nullable = false, updatable = false)
    private String id;
    @Column(name = "product_title")
    private String productTitle;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "description")
    private String description;
    private int numReviews = 0;
    private double price;
    @Column(name = "quantity")
    private int countInStock ;
    @ManyToOne
    @JoinColumn(name = "category_Id")
    @JsonIgnoreProperties("products")
    private Category category;
    @Transient
    private List<Review> reviews;
}
