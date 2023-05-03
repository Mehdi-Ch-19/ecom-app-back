package com.ecomapp.gateway.security;

import com.ecomapp.gateway.config.JwtUtil;
import com.ecomapp.gateway.feign.AuthenticationClient;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;


@Component
@Slf4j
public class AuthFilter implements WebFilter {
    private final Logger logger = LoggerFactory.getLogger(AuthFilter.class);
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";


    @Autowired
    private RouteValidator validator;

    //    @Autowired
//    private RestTemplate template;
    @Autowired
    private JwtUtil jwtUtil;





   /* @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        HttpHeaders headers = request.getHeaders();

        // Extract JWT token from Authorization header
        String authorizationHeader = headers.getFirst(AUTHORIZATION_HEADER);
        String jwtToken = null;
        if (!StringUtils.isEmpty(authorizationHeader) && authorizationHeader.startsWith(BEARER_PREFIX)) {
            jwtToken = authorizationHeader.substring(BEARER_PREFIX.length());
        }

        // Perform JWT token validation
        if (!StringUtils.isEmpty(jwtToken)) {
            // Implement your JWT validation logic here, e.g., using a JWT library
            // If the token is valid, continue processing the request
            // Otherwise, return an error response
        } else {
            // If JWT token is not present, return an error response
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        // Continue processing the request
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }*/

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        if(exchange.getRequest().getURI().getPath().contains("/api/v1/auth") ){
            log.info(exchange.getRequest().getURI().getPath());
            log.info("shema " + exchange.getRequest().getURI().getScheme());
            log.info("rawpath " + exchange.getRequest().getURI().getRawPath());
            log.info("method  " + exchange.getRequest().getMethod());
            return chain.filter(exchange);
        }else {
            ServerHttpRequest request = exchange.getRequest();
            HttpHeaders headers = request.getHeaders();

            // Extract JWT token from Authorization header
            String authorizationHeader = headers.getFirst(AUTHORIZATION_HEADER);
            String jwtToken = null;
            if (!StringUtils.isEmpty(authorizationHeader) && authorizationHeader.startsWith(BEARER_PREFIX)) {
                jwtToken = authorizationHeader.substring(BEARER_PREFIX.length());
            }

            // Perform JWT token validation
            if (!StringUtils.isEmpty(jwtToken)) {
                // Implement your JWT validation logic here, e.g., using a JWT library
                // If the token is valid, continue processing the request
                // Otherwise, return an error response

//                    //REST call to AUTH service
                boolean validateToken = jwtUtil.validateToken(jwtToken);
                if (validateToken){
                    return chain.filter(exchange);
                }else  {
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();

                }
            } else {
                // If JWT token is not present, return an error response
                //exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                //return exchange.getResponse().setComplete();
                return chain.filter(exchange);
            }

            // Continue processing the request

        }


    }


    public static class Config {

    }
}

