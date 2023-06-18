package com.ecomapp.feign.review;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Review {
    private Long id;
    private Long customerId;
    private int rating;
    private String title;
    private String comment;
    private  String productId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime reviewedAt;
}
