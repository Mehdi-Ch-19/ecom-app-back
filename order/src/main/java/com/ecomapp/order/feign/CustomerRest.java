package com.ecomapp.order.feign;

import com.ecomapp.order.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient("CUSTOMER-SERVICE")
public interface CustomerRest {

    @GetMapping(value = "/api/v1/customer/{customerid}")
    Customer findCustomerById(@PathVariable Long customerid,@RequestHeader(value = "Authorization") String authorization);
}
