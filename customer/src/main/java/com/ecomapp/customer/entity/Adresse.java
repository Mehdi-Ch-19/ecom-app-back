package com.ecomapp.customer.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table( name = "adresse", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
@ToString
public class Adresse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String street;
    private String city;
    private String country;
    @OneToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnoreProperties("adresse")
    private Customer customer;
}
