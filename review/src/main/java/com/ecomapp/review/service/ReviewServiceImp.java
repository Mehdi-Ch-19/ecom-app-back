package com.ecomapp.review.service;

import com.ecomapp.review.entity.Review;
import com.ecomapp.review.exeption.ReviewAleardyExist;
import com.ecomapp.review.feign.ProductRestFeign;
import com.ecomapp.review.repository.ReviewRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ReviewServiceImp implements ReviewService{
    private ReviewRepo reviewRepo;
    private ProductRestFeign productRestFeign;

    public ReviewServiceImp(ReviewRepo reviewRepo, ProductRestFeign productRestFeign) {
        this.reviewRepo = reviewRepo;
        this.productRestFeign = productRestFeign;
    }

    @Override
    public Review addReview(Review review) {
        Review review1 = reviewRepo.findReviewByProductIdAndCustomerId(review.getProductId(),review.getCustomerId());
        if(review1 != null){
            throw new ReviewAleardyExist("you aleardy reviewd this product");
        }
        productRestFeign.updateNumReiews(review.getProductId());
        return reviewRepo.saveAndFlush(review);

    }

    @Override
    public List<Review> getReviewsByProduct(String productid) {
        return reviewRepo.findAllByProductId(productid);

    }
}
