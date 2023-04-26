package com.ecomapp.gateway.config;

import com.ecomapp.gateway.feign.AuthenticationClient;
import com.ecomapp.gateway.security.AuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;


@Configuration
public class GatewayConfig {



    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                // ...
                .csrf().disable();
        return http.build();
    }




}
