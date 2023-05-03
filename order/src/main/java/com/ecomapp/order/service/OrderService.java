package com.ecomapp.order.service;

import com.ecomapp.order.dto.OrderRequest;
import com.ecomapp.order.entitiy.Order;
import com.ecomapp.order.entitiy.ProductItem;

import java.util.List;

public interface OrderService {
    String makeOrder(OrderRequest order);
    Order findOrder(String oderId);
    List<Order> findOrdersByCustomer(Long customerId);
    void addProductToOrder(Order order, ProductItem productItem);
}
