package com.ecomapp.product.repository;

import com.ecomapp.product.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends MongoRepository<Product,String > {
    @Query("update Product p set p.numReviews = p.numReviews  + 1 where p.id = ?1")
    void updateNumReview(String product_id);
}
