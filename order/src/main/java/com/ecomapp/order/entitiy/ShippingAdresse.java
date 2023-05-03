package com.ecomapp.order.entitiy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "ShippingAdresse", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})

public class ShippingAdresse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;
    @Column(name = "Shipping_street")
    private String street;
    @Column(name = "Shipping_City")
    private String city;
    @Column(name = "Shipping_country")
    private String country;
    @OneToOne
    @JoinColumn(name = "order_id",nullable = false)
    @JsonIgnoreProperties("ShippingAdresse")
    private Order order;
}
