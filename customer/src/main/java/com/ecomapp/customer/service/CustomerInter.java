package com.ecomapp.customer.service;

import com.ecomapp.customer.Dto.CustomerDto;
import com.ecomapp.customer.Dto.CustomerSignUpDto;

import java.util.List;
import java.util.UUID;

public interface CustomerInter {
    List<CustomerDto> allCustomer();
    CustomerDto getCustomerById(Long id);
    CustomerDto addCustomer(CustomerSignUpDto customerSignUpDto);
    void deleteCustomer(Long id);

}
