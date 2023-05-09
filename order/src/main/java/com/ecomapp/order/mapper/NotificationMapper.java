package com.ecomapp.order.mapper;

import com.ecomapp.feign.notification.ProductItemDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationMapper {

    public List<ProductItemDto> toProductNotDto(List<com.ecomapp.order.dto.ProductItemDto> productItemDto){
        List<ProductItemDto> productItemDtos = new ArrayList<>();
        productItemDto.forEach(p -> {
            productItemDtos.add(ProductItemDto.builder().productId(p.getProductId())
                    .productName(p.getProductName())
                            .unitPrice(p.getUnitPrice())
                    .price(p.getPrice()).quantity(p.getQuantity()).build());
        });

        return productItemDtos;
    }
}
