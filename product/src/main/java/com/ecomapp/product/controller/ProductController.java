package com.ecomapp.product.controller;

import com.ecomapp.product.entity.Product;
import com.ecomapp.product.exeption.ProductNotFound;
import com.ecomapp.product.service.ProductServiceInterf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    @Autowired
    private ProductServiceInterf productService;

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        if (products.isEmpty()){
            return new ResponseEntity<>(products, HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
    }
    @GetMapping("/{productId}")
    public ResponseEntity<?> getByProductId(@PathVariable String productId){
        try {
            Product product = productService.getById(productId);
            return new ResponseEntity<>(product,HttpStatus.OK);
        }catch (ProductNotFound productNotFound){
            return new ResponseEntity<>(productNotFound.getMessage(),HttpStatus.NOT_FOUND);
        }


    }
    //UPDATE METHOD
    @PostMapping()
    public ResponseEntity<?> saveOrUpdateProduct(@RequestBody Product product){
        productService.saveOrUpdateProduct(product);
        return new ResponseEntity<>("added succesfuly",HttpStatus.OK);
    }
    @PutMapping("/{product_id}")
    public void updateNumReview(@PathVariable String product_id){
        productService.UpdateNumReviews(product_id);
    }

}
