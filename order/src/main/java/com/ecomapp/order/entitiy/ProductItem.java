package com.ecomapp.order.entitiy;

import com.ecomapp.order.model.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class ProductItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productId;
    @ManyToOne
    private Order order;
    @Column(name = "quantity",nullable = false)
    private int quantity;
    private double price;
    @Transient
    private Product product;

}
