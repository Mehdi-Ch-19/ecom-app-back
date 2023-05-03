package com.ecomapp.order.dto;

import com.ecomapp.order.entitiy.ProductItem;
import com.ecomapp.order.entitiy.ShippingAdresse;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderRequest {
    private Long customerId;
    private double totalamount;
    private List<ProductItemDto> products;
    private ShippingAdresse shippingAdresse;
}
