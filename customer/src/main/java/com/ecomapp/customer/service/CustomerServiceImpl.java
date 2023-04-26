package com.ecomapp.customer.service;

import com.ecomapp.customer.Dto.CustomerDto;
import com.ecomapp.customer.Dto.CustomerSignUpDto;
import com.ecomapp.customer.Exeption.NotCustomerFound;
import com.ecomapp.customer.Exeption.UserWithEmailExist;
import com.ecomapp.customer.entity.Customer;
import com.ecomapp.customer.entity.Role;
import com.ecomapp.customer.mappers.CustomerMapper;
import com.ecomapp.customer.repository.CustomerRepo;
import com.ecomapp.customer.repository.RoleRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Transactional
@Service
@AllArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerInter{
    private CustomerRepo customerRepo;
    private CustomerMapper mapper;
    private final RoleRepo roleRepo;
    private PasswordEncoder passwordEncoder;
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
        if (customerRepo.existsByEmail(customerSignUpDto.getEmail())){
            throw new UserWithEmailExist("user with this email aleardy exist ");
        }
        Customer customer = new Customer();
        if (!customerSignUpDto.isAdmin()){
            Role role = roleRepo.findByRolename("ROLE_USER");
            customer.setEmail(customerSignUpDto.getEmail());
            customer.setName(customerSignUpDto.getName());
            customer.setPassword(passwordEncoder.encode(customerSignUpDto.getPassword()));
            customer.setAdmin(false);
            addRoleToCustomer(customer,role);
            customerRepo.save(customer);
        }else {
            Role role = roleRepo.findByRolename("ROLE_ADMIN");
            customer.setEmail(customerSignUpDto.getEmail());
            customer.setName(customerSignUpDto.getName());
            customer.setPassword(passwordEncoder.encode(customerSignUpDto.getPassword()));
            customer.setAdmin(true);
            addRoleToCustomer(customer,role);
            customerRepo.save(customer);
        }
        return mapper.todto(customer);
    }
    @Override
    public void addRoleToCustomer(Customer customer, Role role) {
        customer.getRoles().add(role);
    }

    @Override
    public CustomerDto addAdmin(CustomerSignUpDto customerSignUpDto) {
        return null;
    }

    @Override
    public Customer findByEmail(String email) {
        Customer customer = customerRepo.findByEmail(email);
        if (customer == null){
            throw new NotCustomerFound("customer not found");
        }
        return customer;

    }

    @Override
    public Customer findByEmalAndPassord(String email, String password) {
        return customerRepo.findByEmailAndPassword(email,password);
    }

    @Override
    public void deleteCustomer(Long id) {
        try {
            this.customerRepo.deleteById(id);
        }catch (RuntimeException exception){
            throw new NotCustomerFound("no cutomer found");
        }
    }

    @Override
    public boolean checkifauthenticationrequired(String path, HttpMethod httpMethod) {
        Map<String,HttpMethod> notautheicatedRessource = new HashMap<>();
        notautheicatedRessource.put("/CUSTOMER-SERVICE/api/v1/customer/register",HttpMethod.POST);
        int i = 0;
        for (String p : notautheicatedRessource.keySet()) {
            if (Objects.equals(p, path) && notautheicatedRessource.get(p) == httpMethod){
                i++;
            }
        }
        return i != 0;
    }
}
