package com.ecomapp.order.respository;

import com.ecomapp.order.entitiy.Order;
import com.ecomapp.order.entitiy.ShippingAdresse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingRepo  extends JpaRepository<ShippingAdresse,Long> {
    ShippingAdresse findByOrder(Order order);
}
