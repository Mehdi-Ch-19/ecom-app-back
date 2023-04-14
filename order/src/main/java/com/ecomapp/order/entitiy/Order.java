package com.ecomapp.order.entitiy;

import com.ecomapp.order.model.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Order {

    @Id
    @Column(name = "osderId",nullable = false,updatable = false)
    private String orderId;

    @Column(name = "customerId",nullable = false)
    private Long customerId;
    @Column(name = "orderDate")
    private LocalDateTime orderDate;

    @Column(name = "orderStatus")
    private String status;
    @Column(name = "TotalAmount",nullable = false)
    private Long amount;

    @OneToMany(mappedBy = "order")
    private List<ProductItem> productItem;
    @Transient
    private Customer customer;
}
