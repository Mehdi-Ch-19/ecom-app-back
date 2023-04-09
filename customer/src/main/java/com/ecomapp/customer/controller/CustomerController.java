package com.ecomapp.customer.controller;

import com.ecomapp.customer.Dto.CustomerDto;
import com.ecomapp.customer.Dto.CustomerSignUpDto;
import com.ecomapp.customer.Exeption.NotCustomerFound;
import com.ecomapp.customer.service.CustomerInter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {

    private final CustomerInter service;

    public CustomerController(CustomerInter service) {
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<CustomerDto>  createcustomer(@RequestBody CustomerSignUpDto customerSignUpDto){
        CustomerDto customerDto = service.addCustomer(customerSignUpDto);
        return new ResponseEntity<>(customerDto, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getById(@PathVariable Long id){
        try {
            CustomerDto customerDto = service.getCustomerById(id);
            return new ResponseEntity<>(customerDto,HttpStatus.OK);
        }catch (NotCustomerFound notCustomerFound){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletebyId(@PathVariable Long id){
        try {
            service.deleteCustomer(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (NotCustomerFound notCustomerFound){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
