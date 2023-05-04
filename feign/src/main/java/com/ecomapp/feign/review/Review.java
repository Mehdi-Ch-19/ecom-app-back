package com.ecomapp.feign.review;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Review {
    private Long id;
    private Long customerId;
    private int rating;
    private String comment;
    private  String productId;
    private LocalDateTime reviewedAt;
}
