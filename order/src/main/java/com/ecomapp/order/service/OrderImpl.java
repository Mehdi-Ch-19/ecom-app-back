package com.ecomapp.order.service;

import com.ecomapp.amqp.RabbitMQMessageProducer;
import com.ecomapp.feign.customer.Customer;
import com.ecomapp.feign.customer.CustomerRest;
import com.ecomapp.feign.product.Product;
import com.ecomapp.feign.product.ProductRest;
import com.ecomapp.order.dto.OrderRequest;
import com.ecomapp.order.entitiy.Order;
import com.ecomapp.order.entitiy.ProductItem;
import com.ecomapp.order.entitiy.ShippingAdresse;
import com.ecomapp.order.exeption.OrderNotFound;
import com.ecomapp.order.respository.OrderRepo;
import com.ecomapp.order.respository.ProductItemRepo;
import com.ecomapp.order.respository.ShippingRepo;
import com.ecomapp.order.util.JwtToken;
import com.ecomapp.order.util.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class OrderImpl implements OrderService{

    private final OrderRepo orderRepo;
    private final CustomerRest customerRest;
    private final ProductRest productRest;
    private final ShippingRepo shippingRepo;
    private final ProductItemRepo productItemRepo;
    private final RabbitMQMessageProducer producer;

    @Override
    public String makeOrder(OrderRequest orderRequest) {
        Customer customer = customerRest.findCustomerById(orderRequest.getCustomerId(), JwtToken.token);
        if (customer == null)throw new RuntimeException("customer not found");
        Order order = Order.builder().orderId(UUID.randomUUID().toString())
                .totalamount(orderRequest.getTotalamount())
                .status(String.valueOf(OrderStatus.PENDING))
                .orderDate(LocalDate.now().atStartOfDay())
                .customer(customer)
                .customerId(customer.getId())
                .build();
        ShippingAdresse shippingAdresse = new ShippingAdresse();
        shippingAdresse.setCity(orderRequest.getShippingAdresse().getCity());
        shippingAdresse.setStreet(orderRequest.getShippingAdresse().getStreet());
        shippingAdresse.setCountry(orderRequest.getShippingAdresse().getCountry());
        shippingAdresse.setOrder(order);
        List<ProductItem> productItems = new ArrayList<>();
         order.setProductItem(productItems);
        log.info(order.getProductItem().toString());
        orderRequest.getProducts().forEach(productItemDto -> {
            ProductItem productItem = new ProductItem();
            Product productById = productRest.findProductById(productItemDto.getProductId());
            productItem.setProductId(productItemDto.getProductId());
            productItem.setProduct(productById);
            productItem.setPrice(productItemDto.getPrice());
            productItem.setQuantity(productItemDto.getQuantity());
            productItem.setOrder(order);
            addProductToOrder(order,productItem);
        });
        orderRepo.saveAndFlush(order);
        shippingRepo.save(shippingAdresse);
        //producer.publish();
        return order.getOrderId();
    }

    @Override
    public Order findOrder(String oderId) {
        Optional<Order> byId = orderRepo.findById(oderId);
        if (byId.isPresent()){
            return byId.get();
        }else throw new OrderNotFound("ORDER NOT FOUUND");
    }


    @Override
    public List<Order> findOrdersByCustomer(Long customerId) {
        List<Order> orders = orderRepo.findAllByCustomerId(customerId);
        orders.forEach(order ->{
            order.setCustomer(customerRest.findCustomerById(customerId,JwtToken.token));
            order.getProductItem().forEach(productItem -> {
                productItem.setProduct(productRest.findProductById(productItem.getProductId()));
            });
            order.setShippingAdresse(shippingRepo.findByOrder(order));
        }
        );
        return orders;
    }

    @Override
    public void addProductToOrder(Order order, ProductItem productItem) {
        order.getProductItem().add(productItem);
    }
}
