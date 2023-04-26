package com.ecomapp.order.entitiy;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class ShippingAdresse {
    @Id
    private String id;
    private String number;
    private String street;
    private String city;
    private String postcode;
    private String country;
    @OneToOne
    private Order order;
}
