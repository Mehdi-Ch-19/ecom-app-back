package com.ecomapp.review.controller;

import com.ecomapp.review.entity.Review;
import com.ecomapp.review.exeption.ReviewAleardyExist;
import com.ecomapp.review.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/review")
@AllArgsConstructor
public class ReviewController {

    private ReviewService reviewService;
    @PostMapping
    public ResponseEntity<?> addReview(@RequestBody  Review review){
        try {
            Review addReview = reviewService.addReview(review);
            return new ResponseEntity<>(addReview, HttpStatus.CREATED);
        }catch (ReviewAleardyExist reviewAleardyExist){
            return new ResponseEntity<>(reviewAleardyExist.getMessage(),HttpStatus.CONFLICT);
        }
    }
}
