package com.ecomapp.auth.service;

import com.ecomapp.auth.feign.CustomerRest;
import com.ecomapp.auth.models.CustomerDto;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class CustomerUserDetails implements UserDetailsService {
    private  final CustomerRest customerRest;

    public CustomerUserDetails(CustomerRest customerRest) {
        this.customerRest = customerRest;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        CustomerDto customerDto = customerRest.findByEmail(email);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        customerDto.getRoles().forEach(r -> {
            GrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(r.getRolename());
            authorities.add(simpleGrantedAuthority);
        });
        return new User(customerDto.getEmail(),customerDto.getPassword(),authorities);
    }
}
