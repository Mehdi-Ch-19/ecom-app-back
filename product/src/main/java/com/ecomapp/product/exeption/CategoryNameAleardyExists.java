package com.ecomapp.product.exeption;

public class CategoryNameAleardyExists extends RuntimeException{
    public CategoryNameAleardyExists(String message) {
        super(message);
    }
}
