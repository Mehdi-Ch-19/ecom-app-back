package com.ecomapp.product.entity;


import com.ecomapp.feign.review.Review;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
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
    @Temporal(TemporalType.DATE)
    private Date addeedAt;
    private double price;
    @Column(name = "quantity")
    private int countInStock ;
    @Column(name = "rating")
    private double rating =  0;
    @ManyToOne
    @JoinColumn(name = "category_Id")
    @JsonIgnoreProperties("products")
    private Category category;
    @Transient
    private List<Review> reviews;
}
