package com.ecomapp.feign.auth;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "auth",url = "${clients.auth.url}")
@Service
public interface AuthenticationClient {

    @PostMapping("/api/v1/auth")
    boolean validateToken(String token);
}
