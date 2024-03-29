package com.ecomapp.order.respository;

import com.ecomapp.order.entitiy.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order,String> {
    Page<Order> findAll(Pageable pageable);
    List<Order> findAllByCustomerId(Long customerId);
}
