package com.ecomapp.product.service;

import com.ecomapp.feign.review.Review;
import com.ecomapp.feign.review.ReviewRest;
import com.ecomapp.product.entity.Product;
import com.ecomapp.product.exeption.ProductNotFound;
import com.ecomapp.product.repository.ProductRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Executable;
import java.util.Date;
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
        product.setAddeedAt(new Date());
        return productRepo.save(product);
    }

    @Transactional
    @Override
    public Product UpdateNumReviews(String product_id) {
        productRepo.updateNumReview(product_id);
        return productRepo.findById(product_id).orElse(null );
    }

    @Override
    public Product UpdateProduct(Product product) {
        Product product1 = getById(product.getId());
        product1.setProductTitle(product.getProductTitle());
        product1.setCategory(product.getCategory());
        product1.setCountInStock(product.getCountInStock());
        product1.setDescription(product.getDescription());
        product1.setImageUrl(product.getImageUrl());
        product.setPrice(product.getPrice());
        return productRepo.save(product1);
    }

    @Transactional
    @Override
    public void UpdateRating(String product_id, int rating) {
        Product product = getById(product_id);
        double oldrating = product.getRating();
        double newrating = 0 ;
        if(oldrating == 0){
            newrating = rating;
        }else {
            oldrating+=rating;
            newrating = oldrating/product.getNumReviews();
        }
        product.setRating(newrating);
        productRepo.save(product);
    }

    @Override
    public boolean deleteProduct(String id) {
        try {
            productRepo.deleteById(id);
            return true;
        }catch (Exception e){
            throw new RuntimeException("product not found");
        }
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
