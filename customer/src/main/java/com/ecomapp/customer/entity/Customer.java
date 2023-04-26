package com.ecomapp.customer.entity;


import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
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
    @Column(name = "password",length = 100)
    private String password;

    private boolean isAdmin;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Collection<Role> roles = new ArrayList<>();

    public void addRole(Role role){
        this.getRoles().add(role);
    }
}
