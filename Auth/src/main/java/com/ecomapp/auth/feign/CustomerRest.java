package com.ecomapp.auth.feign;

import com.ecomapp.auth.models.CustomerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@FeignClient("CUSTOMER-SERVICE")
@Service
public interface CustomerRest {

    @PostMapping("/api/v1/customer/find")
    CustomerDto findByEmail(@RequestParam(value = "email")  String email);
    @PostMapping("/api/v1/customer/register")
    CustomerDto register(@RequestBody CustomerDto customerDto);


}
