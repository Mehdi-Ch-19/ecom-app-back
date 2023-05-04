package com.ecomapp.feign.product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "product",url = "${clients.product.url}")
public interface ProductRest {
    @PutMapping("/api/v1/product/{product_id}")
    Product updateNumReiews(@PathVariable("product_id") String product_id,@RequestHeader(value = "Authorization") String authorization);
    @GetMapping("/api/v1/product/{productId}")
    Product findProductById(@PathVariable("productId") String productId);
}
