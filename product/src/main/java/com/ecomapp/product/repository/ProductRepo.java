package com.ecomapp.product.repository;

import com.ecomapp.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,String > {
    @Modifying
    @Query("update Product p set p.numReviews = p.numReviews  + 1 where p.id = :product_id")
    void updateNumReview(@Param("product_id") String product_id);

}
