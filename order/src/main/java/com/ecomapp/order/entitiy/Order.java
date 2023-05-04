package com.ecomapp.order.entitiy;

import com.ecomapp.feign.customer.Customer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "Orders")
public class Order {
    @Id
    @Column(name = "orderId",nullable = false,updatable = false)
    private String orderId;
    @Column(name = "customerId",nullable = false)
    private Long customerId;
    @Column(name = "orderDate")
    private LocalDateTime orderDate;
    @Column(name = "orderStatus")
    private String status;
    @Column(name = "TotalAmount",nullable = false)
    private double totalamount;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    @JsonIgnoreProperties("order")
    private List<ProductItem> productItem = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties("order")
    private ShippingAdresse shippingAdresse;
    @Transient
    private Customer customer;

}
