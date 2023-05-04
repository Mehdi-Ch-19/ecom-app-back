package com.ecomapp.review.feign;

import com.ecomapp.review.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

/*@FeignClient(name = "PRODUCT-SERVICE")*/
public interface ProductRestFeign {

   /* @PutMapping("/api/v1/product/{product_id}")
    Product updateNumReiews(@PathVariable String product_id);*/
}
