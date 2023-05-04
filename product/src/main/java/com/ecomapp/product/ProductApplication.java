package com.ecomapp.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(
        scanBasePackages = {
                "com.ecomapp.product"
                ,"com.ecomapp.amqp"
        }
)
@EnableFeignClients(
        basePackages = {
                "com.ecomapp.feign"
        }
)
public class ProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }

}
