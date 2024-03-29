package com.ecomapp.product.controller;

import com.ecomapp.product.entity.Product;
import com.ecomapp.product.exeption.ProductNotFound;
import com.ecomapp.product.service.ProductServiceInterf;
import com.ecomapp.product.util.SecureWithToken;
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
    @DeleteMapping("/{productid}")
    public ResponseEntity<?> deleteProduct(@PathVariable(value = "productid") String productid){
        try {
            productService.deleteProduct(productid);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
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
    @PostMapping("/update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product){
        return new ResponseEntity<>(productService.UpdateProduct(product),HttpStatus.OK);
    }
    //UPDATE METHOD
    @PostMapping()
    @SecureWithToken
    public ResponseEntity<?> saveOrUpdateProduct(@RequestBody Product product){
        productService.saveOrUpdateProduct(product);
        return new ResponseEntity<>("added succesfuly",HttpStatus.OK);
    }
    @PutMapping("/{product_id}")
    @SecureWithToken
    public void updateNumReview(@PathVariable String product_id){
        productService.UpdateNumReviews(product_id);
    }

    @PutMapping("/{product_id}/rating/{rating}")
    public void updateRating(@PathVariable String product_id , @PathVariable int rating){
        productService.UpdateRating(product_id,rating);
    }
}
