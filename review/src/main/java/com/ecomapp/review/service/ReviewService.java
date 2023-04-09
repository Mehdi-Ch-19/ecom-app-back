package com.ecomapp.review.service;

import com.ecomapp.review.entity.Review;

import java.util.List;

public interface ReviewService {
    Review addReview(Review review);
    List<Review> getReviewsByProduct(String productid);

}
