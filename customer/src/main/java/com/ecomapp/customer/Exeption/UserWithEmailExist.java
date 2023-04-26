package com.ecomapp.customer.Exeption;

public class UserWithEmailExist extends RuntimeException {
    public UserWithEmailExist(String s) {
        super(s);
    }
}
