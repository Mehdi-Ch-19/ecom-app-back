package com.ecomapp.product.service;

import com.ecomapp.feign.review.Review;
import com.ecomapp.feign.review.ReviewRest;
import com.ecomapp.product.entity.Product;
import com.ecomapp.product.exeption.ProductNotFound;
import com.ecomapp.product.repository.ProductRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductServiceInterf{
    private final ProductRepo productRepo;
    private final ReviewRest reviewRest;

    public ProductServiceImpl(ProductRepo productRepo, ReviewRest reviewRest) {
        this.productRepo = productRepo;
        this.reviewRest = reviewRest;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product saveOrUpdateProduct(Product product) {
        product.setId(UUID.randomUUID().toString());
        return productRepo.save(product);
    }

    @Transactional
    @Override
    public Product UpdateNumReviews(String product_id) {
        productRepo.updateNumReview(product_id);
        return productRepo.findById(product_id).orElse(null );
    }

    @Override
    public void deleteProduct(String id) {
        productRepo.deleteById(id);
    }

    @Override
    public Product getById(String id) {
        List<Review> reviewList = reviewRest.getAllReviewByProduct(id);
        Product product = productRepo.findById(id).orElseThrow(() -> new ProductNotFound("Product not found"));
        product.setReviews(reviewList);
        return product;
    }

    @Override
    public List<Review> findAllReviewsByProduct(String productId) {
        return  reviewRest.getAllReviewByProduct(productId);

    }

}
