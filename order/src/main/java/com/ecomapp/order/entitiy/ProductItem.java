package com.ecomapp.order.entitiy;

import com.ecomapp.order.model.Product;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Table(name = "ProductItem", uniqueConstraints = {@UniqueConstraint(columnNames = {"product_Item_Id"})})
public class ProductItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_Item_Id",nullable = false)
    private Long id;
    @Column(name = "product_Id",nullable = false)
    private String productId;
    @ManyToOne
    @JoinColumn(name = "order_id",nullable = false)
    @JsonIgnoreProperties("productItem")
    private Order order;
    @Column(name = "Product_Item_quantity",nullable = false)
    private int quantity;
    @Column(name = "Product_Item_Price")
    private double price;
    @Transient
    private Product product;

}
