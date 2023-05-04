package com.ecomapp.feign.customer;

 import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
 import org.springframework.web.bind.annotation.*;

@FeignClient(name = "customer",url = "${clients.customer.url}")
@Service
public interface CustomerRest {

    @PostMapping("/api/v1/customer/find")
    CustomerDto findByEmail(@RequestParam(value = "email")  String email);
    @PostMapping("/api/v1/customer/register")
    CustomerDto register(@RequestBody CustomerDto customerDto);

    @GetMapping("/api/v1/customer/login")
     CustomerDto login(@RequestBody CustomerRequest customerRequest);
    @GetMapping(value = "/api/v1/customer/{customerid}")
    Customer findCustomerById(@PathVariable("customerid") Long customerid, @RequestHeader(value = "Authorization") String authorization);


}
