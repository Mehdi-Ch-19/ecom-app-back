package com.ecomapp.order.dto;

import lombok.Data;
@Data
public class ProductItemDto {
    private String productId;
    private int quantity;
    private double price;
}
