package com.ecomapp.order.controller;

import com.ecomapp.order.dto.OrderRequest;
import com.ecomapp.order.entitiy.Order;
import com.ecomapp.order.service.OrderService;
import com.ecomapp.order.util.SecureWithToken;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @SecureWithToken
    @PostMapping("/create")
    public ResponseEntity<?> createorder(@RequestBody OrderRequest orderRequest){
        try {
            String orderId = orderService.makeOrder(orderRequest);
            return new ResponseEntity<>(orderId, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_GATEWAY);
        }
    }
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<?> findAllOrdersByCustomer(@PathVariable Long  customerId){
        try {
            List<Order> orders = orderService.findOrdersByCustomer(customerId);
            if (orders.isEmpty())return new ResponseEntity<>(orders,HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{orderId}")
    public ResponseEntity<?> findById(@PathVariable String  orderId){
        try {
            Order order = orderService.findOrder(orderId);
            return new ResponseEntity<>(order, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
