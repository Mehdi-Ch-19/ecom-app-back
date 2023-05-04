package com.ecomapp.gateway.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/*@FeignClient("AUTH-SERVICE")*/
public interface AuthenticationClient {

   /* @PostMapping("/api/v1/auth")
    boolean validateToken(String token);*/
}
