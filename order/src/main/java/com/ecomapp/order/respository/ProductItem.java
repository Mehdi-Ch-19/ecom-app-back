package com.ecomapp.order.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductItem extends JpaRepository<ProductItem,Long> {
}
