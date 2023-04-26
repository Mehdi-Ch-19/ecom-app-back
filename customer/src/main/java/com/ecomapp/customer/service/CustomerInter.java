package com.ecomapp.customer.service;

import com.ecomapp.customer.Dto.CustomerDto;
import com.ecomapp.customer.Dto.CustomerSignUpDto;
import com.ecomapp.customer.entity.Customer;
import com.ecomapp.customer.entity.Role;
import org.springframework.http.HttpMethod;

import java.util.List;
import java.util.UUID;

public interface CustomerInter {
    List<CustomerDto> allCustomer();
    CustomerDto getCustomerById(Long id);
    CustomerDto addCustomer(CustomerSignUpDto customerSignUpDto);
    CustomerDto addAdmin(CustomerSignUpDto customerSignUpDto);
    void addRoleToCustomer(Customer customer, Role role);
    Customer findByEmail(String email);
    Customer findByEmalAndPassord(String email , String password);
    void deleteCustomer(Long id);
    boolean checkifauthenticationrequired(String path , HttpMethod httpMethod);

}
