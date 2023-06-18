package com.ecomapp.order.service;

import com.ecomapp.order.dto.OrderRequest;
import com.ecomapp.order.entitiy.Order;
import com.ecomapp.order.entitiy.ProductItem;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {
    String makeOrder(OrderRequest order) throws JsonProcessingException;
    Order findOrder(String oderId);
    List<Order> findOrdersByCustomer(Long customerId);
    List<Order> findAll();
    Page<Order> getAll(Pageable pageable);
    void addProductToOrder(Order order, ProductItem productItem);
}
