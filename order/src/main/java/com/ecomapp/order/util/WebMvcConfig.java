package com.ecomapp.order.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Register TokenAuthenticationInterceptor and specify the path patterns to intercept
        registry.addInterceptor(new TokenAuthenticationInterceptor())
                .addPathPatterns("/**") // Specify the path patterns to intercept, e.g., "/*", "/api/**", etc.
                .excludePathPatterns("/public/**"); // Specify any path patterns to exclude from interception, if needed
    }
}
