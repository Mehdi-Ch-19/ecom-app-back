package com.ecomapp.order.service;

import com.ecomapp.order.entitiy.Order;

import java.util.List;

public interface OrderService {
    Order makeOrder(Order order);
    Order findOrder(String oderId);
    List<Order> findOrdersByCustomer(Long customerId);
}
