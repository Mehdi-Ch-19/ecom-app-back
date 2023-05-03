package com.ecomapp.auth.service;


import com.ecomapp.auth.config.JwtService;
import com.ecomapp.auth.feign.CustomerRest;
import com.ecomapp.auth.models.*;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpHeaders;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AuthenticationService {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final CustomerRest customerRest;

    public AuthenticationService(JwtService jwtService, AuthenticationManager authenticationManager, CustomerRest customerRest) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.customerRest = customerRest;
    }

    public AuthenticationResponse register(CustomerSignUp request) {
        // register user in the user-service
        CustomerDto customerDto = new CustomerDto();
        customerDto.setName(request.getName());
        customerDto.setEmail(request.getEmail());
        customerDto.setPassword(request.getPassword());
        customerDto.setAdmin(request.isAdmin());
        CustomerDto registercustomer = customerRest.register(customerDto);
        CustomerDto newuser = customerRest.findByEmail(registercustomer.getEmail());
        System.out.println(registercustomer.toString());
        System.out.println(newuser.toString());
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        newuser.getRoles().forEach(r -> {
            GrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(r.getRolename());
            authorities.add(simpleGrantedAuthority);
        });
        User user = new User(newuser.getEmail()
                ,newuser.getPassword(),authorities);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public AuthenticationResponse authenticate(CustomerRequest request) {
        log.info(request.getEmail());
        try {
            log.info(request.getPassword());
            Authentication authentication = new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword());
            log.info(String.valueOf(authentication.isAuthenticated()));
            authenticationManager.authenticate(authentication);

            SecurityContextHolder.getContext().setAuthentication(authentication);
            CustomerDto registercustomer = customerRest.findByEmail(request.getEmail());
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            registercustomer.getRoles().forEach(r -> {
                GrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(r.getRolename());
                authorities.add(simpleGrantedAuthority);
            });
            
            User user = new User(registercustomer.getEmail()
                    ,registercustomer.getPassword(),authorities);
            var jwtToken = jwtService.generateToken(user);
            var refreshToken = jwtService.generateRefreshToken(user);
            return AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .refreshToken(refreshToken)
                    .email(user.getUsername())
                    .roles(user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                    .build();
        }catch (Exception e){
            log.info(e.getMessage());
            throw new RuntimeException();
        }

    }
    public RefreshTokenResponce refreshToken(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        try {
            final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
            final String refreshToken;
            final String userEmail;
            if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
                throw new RuntimeException("Jwt expected");
            }
            refreshToken = authHeader.substring(7);
            userEmail = jwtService.extractUsername(refreshToken);
            if (userEmail != null) {
                CustomerDto registercustomer = customerRest.findByEmail(userEmail);
                Collection<GrantedAuthority> authorities = new ArrayList<>();
                registercustomer.getRoles().forEach(r -> {
                    GrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(r.getRolename());
                    authorities.add(simpleGrantedAuthority);
                });
                User user = new User(registercustomer.getEmail()
                        ,registercustomer.getPassword(),authorities);
                if (jwtService.isTokenValid(refreshToken, user)) {
                    var accessToken = jwtService.generateToken(user);
                    return RefreshTokenResponce.builder()
                            .accessToken(accessToken)
                            .refreshToken(refreshToken)
                            .build();
                    //new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
                }
            }
            throw new RuntimeException("invalid jwt ");
        }catch (Exception e){
            log.info(e.getMessage());
            throw new RuntimeException();
        }

    }
}

