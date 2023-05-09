package com.ecomapp.feign.notification;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Builder
@Getter
public class ProductItemDto implements Serializable {
    @JsonProperty("productId")
    private String productId;

    @JsonProperty("productName")
    private String productName;
    @JsonProperty("unitPrice")
    private double unitPrice;

    @JsonProperty("quantity")
    private int quantity;
    @JsonProperty("price")
    private double price;

    @JsonCreator
    public ProductItemDto(@JsonProperty("productId")String productId
            ,@JsonProperty("productName")String productName
            ,@JsonProperty("unitPrice")double unitPrice
            ,@JsonProperty("quantity") int quantity
            ,@JsonProperty("price") double price) {
        this.productId = productId;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductItemDto{" +
                "productId='" + productId + '\'' +
                ",productName = "+productName+
                ",unitPrice = "+unitPrice+
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
