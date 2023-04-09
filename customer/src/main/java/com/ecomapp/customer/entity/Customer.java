package com.ecomapp.customer.entity;


import com.ecomapp.customer.enums.Role;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Customer {
    @Id
    @Column(name = "id",nullable = false , updatable = false  )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id  ;
    @Nonnull
    @Column(name = "name",length = 20)
    private String name;
    @Column(name = "email",length = 100)
    @Nonnull
    private String email;
    @Nonnull
    @Column(name = "password",length = 50)
    private String password;
    private Role role;
}
