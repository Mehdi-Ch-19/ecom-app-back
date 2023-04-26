package com.ecomapp.gateway.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;


public class WebcClientConfig {

    @Bean
    @LoadBalanced
    public WebClient.Builder loadbalancedclienbuilde(){
        return WebClient.builder();
    }
}
