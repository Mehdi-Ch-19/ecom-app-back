package com.ecomapp.order.service;

import com.ecomapp.amqp.RabbitMQMessageProducer;
import com.ecomapp.feign.customer.Customer;
import com.ecomapp.feign.customer.CustomerRest;
import com.ecomapp.feign.notification.NotificationRequest;
import com.ecomapp.feign.notification.OrderPlaced;
import com.ecomapp.feign.notification.ProductItemDto;
import com.ecomapp.feign.product.Product;
import com.ecomapp.feign.product.ProductRest;
import com.ecomapp.order.dto.OrderRequest;
import com.ecomapp.order.entitiy.Order;
import com.ecomapp.order.entitiy.ProductItem;
import com.ecomapp.order.entitiy.ShippingAdresse;
import com.ecomapp.order.exeption.OrderNotFound;
import com.ecomapp.order.mapper.NotificationMapper;
import com.ecomapp.order.respository.OrderRepo;
import com.ecomapp.order.respository.ProductItemRepo;
import com.ecomapp.order.respository.ShippingRepo;
import com.ecomapp.order.util.JwtToken;
import com.ecomapp.order.util.OrderStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
@Slf4j
public class OrderImpl implements OrderService{

    private final OrderRepo orderRepo;
    private final CustomerRest customerRest;
    private final ProductRest productRest;
    private final ShippingRepo shippingRepo;
    private final ProductItemRepo productItemRepo;
    private final NotificationMapper notificationMapper;
    private final RabbitMQMessageProducer producer;

    @Override
    public String makeOrder(OrderRequest orderRequest) throws JsonProcessingException {
        Customer customer = customerRest.findCustomerById(orderRequest.getCustomerId(), JwtToken.token);
        if (customer == null)throw new RuntimeException("customer not found");
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formatDateTime = now.format(formatter);
        System.out.println("After : " + formatDateTime);
        Order order = Order.builder().orderId(UUID.randomUUID().toString())
                .totalamount(orderRequest.getTotalamount())
                .status(String.valueOf(OrderStatus.PENDING))
                .orderDate(LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),formatter))
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
            productItem.setProductName(productItemDto.getProductName());
            productItem.setUnitPrice(productItemDto.getUnitPrice());
            productItem.setProduct(productById);
            productItem.setPrice(productItemDto.getPrice());
            productItem.setQuantity(productItemDto.getQuantity());
            productItem.setOrder(order);
            addProductToOrder(order,productItem);
        });
         orderRepo.saveAndFlush(order);
        shippingRepo.save(shippingAdresse);
        //Publish to the notification service
        OrderPlaced orderPlaced = OrderPlaced.builder()
                .orderId(order.getOrderId())
                .orderDate(order.getOrderDate()).orderId(order.getOrderId())
                .productItems(notificationMapper.toProductNotDto(orderRequest.getProducts()))
                .totalamount(order.getTotalamount())
                .customerId(order.getCustomerId())
                .build();

        /*notificationRequest.setCustomerEmail(customer.getEmail());
        notificationRequest.setCustomerId(customer.getId());
        notificationRequest.setCustomerName(customer.getName());
        notificationRequest.setMessage("Your order was Succes");
        notificationRequest.setSender("Admin");
        notificationRequest.setOrderPlaced(orderPlaced);*/
       /* ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();*/
        NotificationRequest notificationRequest = NotificationRequest.builder().customerEmail(customer.getEmail())
                .orderPlaced(orderPlaced).message("Your order was Succes")
                .customerName(customer.getName()).customerId(customer.getId())
                .sender("Admin").build();

        producer.publish(notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key");
        return orderPlaced.getOrderId();
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
    public List<Order> findAll() {

        return orderRepo.findAll().stream().peek(o ->
                o.setCustomer(customerRest.findCustomerById(o.getCustomerId(), JwtToken.token))).collect(Collectors.toList()
        );
    }

    @Override
    public Page<Order> getAll(Pageable pageable) {
        return orderRepo.findAll(pageable);
    }

    @Override
    public void addProductToOrder(Order order, ProductItem productItem) {
        order.getProductItem().add(productItem);
    }
}
