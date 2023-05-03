package com.ecomapp.customer.controller;

import com.ecomapp.customer.Dto.CustomerDto;
import com.ecomapp.customer.Dto.CustomerRequest;
import com.ecomapp.customer.Dto.CustomerSignUpDto;
import com.ecomapp.customer.Exeption.NotCustomerFound;
import com.ecomapp.customer.entity.Customer;
import com.ecomapp.customer.entity.Role;
import com.ecomapp.customer.repository.RoleRepo;
import com.ecomapp.customer.service.CustomerInter;
import com.ecomapp.customer.util.SecureWithToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {

    private final CustomerInter service;
    private final RoleRepo roleRepo;
    public CustomerController(CustomerInter service, RoleRepo roleRepo) {
        this.service = service;
        this.roleRepo = roleRepo;
    }
    @PostMapping("/register")
    public ResponseEntity<CustomerDto>  createcustomer(@RequestBody CustomerSignUpDto customerSignUpDto){
        CustomerDto customerDto = service.addCustomer(customerSignUpDto);
        return new ResponseEntity<>(customerDto, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    @SecureWithToken
    public ResponseEntity<CustomerDto> getById(@PathVariable Long id){
        try {
            CustomerDto customerDto = service.getCustomerById(id);
            return new ResponseEntity<>(customerDto,HttpStatus.OK);
        }catch (NotCustomerFound notCustomerFound){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("/login")
    public boolean login(@RequestBody CustomerRequest customerRequest){
        Customer customer = service.findByEmalAndPassord(customerRequest.getEmail(), customerRequest.getPassword());
        if (customer != null){
            return true;
        }else return false;
    }
    @PostMapping("/find")
    public ResponseEntity<Customer> findByEmail(@RequestParam(value = "email",required = false) String email){
        try {
            Customer customerbyEmail = service.findByEmail(email);
            return new ResponseEntity<>(customerbyEmail,HttpStatus.OK);
        }catch (NotCustomerFound notCustomerFound){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @SecureWithToken
    public ResponseEntity<?> deletebyId(@PathVariable Long id){
        try {
            service.deleteCustomer(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (NotCustomerFound notCustomerFound){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
