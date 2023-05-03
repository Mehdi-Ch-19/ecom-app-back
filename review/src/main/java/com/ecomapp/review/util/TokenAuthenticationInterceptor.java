package com.ecomapp.review.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

public class TokenAuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Check if the handler is a method in a controller
        if (handler instanceof HandlerMethod handlerMethod) {
            // Check if the method is annotated with @SecureWithToken
            if (handlerMethod.getMethod().isAnnotationPresent(SecureWithToken.class)) {
                // Perform token authentication logic here
                // You can access the token from the request header, validate it, and return appropriate response
                // If the token is valid, return true to allow the request to proceed to the endpoint
                // If the token is invalid or not present, return false to block the request
                // You can also set appropriate response status, headers, and body as needed
                if (request.getHeader(HttpHeaders.AUTHORIZATION) == null) {
                    response.setStatus(HttpStatus.SC_FORBIDDEN);
                    return false;
                }
                return true; // Return false to block the request for this example
            }
        }
        // Return true to allow the request to proceed to the endpoint for other cases
        return true;
    }
}
