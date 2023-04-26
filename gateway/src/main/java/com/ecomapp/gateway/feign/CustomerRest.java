package com.ecomapp.gateway.feign;

import com.ecomapp.gateway.models.CustomerDto;
import com.ecomapp.gateway.models.CustomerRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("CUSTOMER-SERVICE")
public interface CustomerRest {

    @GetMapping("/api/v1/customer/login")
    CustomerDto login(@RequestBody CustomerRequest customerRequest);
}
