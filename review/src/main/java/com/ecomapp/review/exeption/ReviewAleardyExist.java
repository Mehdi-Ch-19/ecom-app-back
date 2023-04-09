package com.ecomapp.review.exeption;

public class ReviewAleardyExist extends RuntimeException {
    public ReviewAleardyExist(String message){
        super(message);
    }
}
