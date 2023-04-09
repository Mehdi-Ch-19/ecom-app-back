package com.ecomapp.review.repository;

import com.ecomapp.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepo extends JpaRepository<Review,Long> {
    Review findReviewByProductIdAndCustomerId(String product_id,Long customer_id);
}
