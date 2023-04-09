package com.ecomapp.customer.service;

import com.ecomapp.customer.Dto.CustomerDto;
import com.ecomapp.customer.Dto.CustomerSignUpDto;
import com.ecomapp.customer.Exeption.NotCustomerFound;
import com.ecomapp.customer.entity.Customer;
import com.ecomapp.customer.mappers.CustomerMapper;
import com.ecomapp.customer.repository.CustomerRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerInter{
    private CustomerRepo customerRepo;
    private CustomerMapper mapper;
    @Override
    public List<CustomerDto> allCustomer() {
        return this.customerRepo.findAll().stream().map(customer -> mapper.todto(customer)).collect(Collectors.toList());
    }

    @Override
    public CustomerDto getCustomerById(Long id) {
        return this.customerRepo.findById(id).map(customer -> mapper.todto(customer))
                .orElseThrow(()->new NotCustomerFound("no customer found ") );
    }

    @Override
    public CustomerDto addCustomer(CustomerSignUpDto customerSignUpDto) {
        Customer customer = customerRepo.saveAndFlush(Customer.builder()
                .email(customerSignUpDto.getEmail())
                .name(customerSignUpDto.getName())
                .password(customerSignUpDto.getPassword())
                .role(customerSignUpDto.getRole())
                .build());

        return mapper.todto(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        try {
            this.customerRepo.deleteById(id);
        }catch (RuntimeException exception){
            throw new NotCustomerFound("no cutomer found");
        }
    }
}
