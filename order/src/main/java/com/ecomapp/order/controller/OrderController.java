package com.ecomapp.order.controller;

import com.ecomapp.order.dto.OrderRequest;
import com.ecomapp.order.entitiy.Order;
import com.ecomapp.order.service.OrderService;
import com.ecomapp.order.util.SecureWithToken;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            return new ResponseEntity<>(HttpStatus.OK);
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
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders(){
        return new ResponseEntity<>(orderService.findAll(),HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<Map<String,Object>> getAllByPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ){
        try {
            Pageable paging = PageRequest.of(page, size);
            Page<Order> orderPage = orderService.getAll(paging);
            Map<String, Object> response = new HashMap<>();
            response.put("orders", orderPage);
            response.put("currentPage", orderPage.getNumber());
            response.put("totalItems", orderPage.getTotalElements());
            response.put("totalPages", orderPage.getTotalPages());
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }


    }
}
