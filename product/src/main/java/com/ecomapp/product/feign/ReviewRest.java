package com.ecomapp.product.feign;

import com.ecomapp.product.models.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "REVIEW-SERVICE")
public interface ReviewRest {

    @GetMapping("/api/v1/review/{product_id}")
    List<Review> getAllReviewByProduct(@PathVariable String product_id);
}
