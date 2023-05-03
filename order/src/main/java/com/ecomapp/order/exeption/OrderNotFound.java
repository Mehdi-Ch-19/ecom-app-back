package com.ecomapp.order.exeption;

public class OrderNotFound extends RuntimeException {
    public OrderNotFound(String orderNotFouund) {
        super(orderNotFouund);
    }
}
