package com.ecomapp.feign.review;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "review",url = "${clients.review.url}")
public interface ReviewRest {

    @GetMapping("/api/v1/review/product/{product_id}")
    List<Review> getAllReviewByProduct(@PathVariable("product_id") String product_id);
}
