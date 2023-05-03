package com.ecomapp.review.controller;

import com.ecomapp.review.entity.Review;
import com.ecomapp.review.exeption.ReviewAleardyExist;
import com.ecomapp.review.service.ReviewService;
import com.ecomapp.review.util.SecureWithToken;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/review")
@AllArgsConstructor
public class ReviewController {

    private ReviewService reviewService;
    @PostMapping
    @SecureWithToken
    public ResponseEntity<?> addReview(@RequestBody  Review review){
        try {
            Review addReview = reviewService.addReview(review);
            return new ResponseEntity<>(addReview, HttpStatus.CREATED);
        }catch (ReviewAleardyExist reviewAleardyExist){
            return new ResponseEntity<>(reviewAleardyExist.getMessage(),HttpStatus.CONFLICT);
        }
    }
    @GetMapping("/product/{productId}")
    public ResponseEntity<?> getReviewsByProduct(@PathVariable String productId){
        try {
            List<Review> reviewsByProduct = reviewService.getReviewsByProduct(productId);
            if (reviewsByProduct.isEmpty()){
                return new ResponseEntity<>("no reviews for this product",HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(reviewsByProduct,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("product not found",HttpStatus.NOT_FOUND);
        }
    }
}
