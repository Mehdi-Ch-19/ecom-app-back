package com.ecomapp.product.service;

import com.ecomapp.product.entity.Product;
import com.ecomapp.product.exeption.ProductNotFound;
import com.ecomapp.product.feign.ReviewRest;
import com.ecomapp.product.models.Review;
import com.ecomapp.product.repository.ProductRepo;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductServiceInterf{
    private final ProductRepo productRepo;
    private final MongoTemplate mongoTemplate;
    private final ReviewRest reviewRest;

    public ProductServiceImpl(ProductRepo productRepo, MongoTemplate mongoTemplate, ReviewRest reviewRest) {
        this.productRepo = productRepo;
        this.mongoTemplate = mongoTemplate;
        this.reviewRest = reviewRest;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product saveOrUpdateProduct(Product product) {
        return productRepo.save(product);
    }

    @Override
    public Product UpdateNumReviews(String product_id) {
        Optional<Product> product = productRepo.findById(product_id);
        Query query = new Query().addCriteria(Criteria.where("numReviews").is(product.get().getNumReviews()));
        Update update = new Update().set("numReviews",product.get().getNumReviews()+1);
        FindAndModifyOptions options = new FindAndModifyOptions().returnNew(true).upsert(false);
        Product newproduct = mongoTemplate.findAndModify(query, update, options, Product.class);
        return newproduct;
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
}
