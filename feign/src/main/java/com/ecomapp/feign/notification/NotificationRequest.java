package com.ecomapp.feign.notification;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Builder
@Getter

public class NotificationRequest implements Serializable {
    @JsonProperty("customerId")
    private Long customerId;
    @JsonProperty("customerName")
    private String customerName;
    @JsonProperty("customerEmail")
    private String customerEmail;
    @JsonProperty("sender")
    private String sender;
    @JsonProperty("message")
    private String message;
    @JsonProperty("orderPlaced")
    private OrderPlaced orderPlaced;

    public NotificationRequest(
            @JsonProperty("customerId")
            Long customerId,
            @JsonProperty("customerName")
              String customerName,
            @JsonProperty("customerEmail")
              String customerEmail,
            @JsonProperty("sender")
              String sender,
            @JsonProperty("message")
              String message,
            @JsonProperty("orderPlaced")
              OrderPlaced orderPlaced
    ) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.sender = sender;
        this.message = message;
        this.orderPlaced = orderPlaced;
    }

    @Override
    public String toString() {
        return "NotificationRequest{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", sender='" + sender + '\'' +
                ", message='" + message + '\'' +
                ", orderPlaced=" + orderPlaced +
                '}';
    }
}
