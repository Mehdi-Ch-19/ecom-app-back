package com.ecomapp.product.service;

import com.ecomapp.feign.review.Review;
import com.ecomapp.product.entity.Product;

import java.util.List;

public interface ProductServiceInterf {
    List<Product> getAllProducts();
    Product saveOrUpdateProduct(Product product);
    Product UpdateNumReviews(String product_id);
    Product UpdateProduct(Product product);
    void UpdateRating(String product_id,int rating);
    boolean deleteProduct(String id);
    Product getById(String id);
    List<Review> findAllReviewsByProduct(String  productId);
}
