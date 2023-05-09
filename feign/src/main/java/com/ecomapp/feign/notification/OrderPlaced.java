package com.ecomapp.feign.notification;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


@Builder
@Getter
public class OrderPlaced implements Serializable {

    @JsonProperty("orderId")
    private String orderId;
    @JsonProperty("customerId")
    private Long customerId;
    @JsonProperty("productItems")
    private List<ProductItemDto> productItems;
    @JsonProperty("totalamount")
    private double totalamount;
    @JsonProperty("orderDate")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime orderDate;


    @JsonCreator
    public OrderPlaced(@JsonProperty("orderId") String orderId, @JsonProperty("customerId") Long customerId, @JsonProperty("productItems") List<ProductItemDto> productItems,@JsonProperty("totalamount") double totalamount,@JsonProperty("orderDate") LocalDateTime orderDate) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.productItems = productItems;
        this.totalamount = totalamount;
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "OrderPlaced{" +
                "orderId='" + orderId + '\'' +
                ", customerId=" + customerId +
                ", productItems=" + productItems +
                ", totalamount=" + totalamount +
                ", orderDate=" + orderDate +
                '}';
    }
}
