package com.ecomapp.order.respository;

import com.ecomapp.order.entitiy.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductItemRepo extends JpaRepository<ProductItem,Long> {

}
