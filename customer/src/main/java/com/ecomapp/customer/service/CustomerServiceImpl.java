package com.ecomapp.customer.service;

import com.ecomapp.customer.Dto.CustomerDto;
import com.ecomapp.customer.Dto.CustomerSignUpDto;
import com.ecomapp.customer.Exeption.NotCustomerFound;
import com.ecomapp.customer.Exeption.UserWithEmailExist;
import com.ecomapp.customer.entity.Adresse;
import com.ecomapp.customer.entity.Customer;
import com.ecomapp.customer.entity.Role;
import com.ecomapp.customer.mappers.CustomerMapper;
import com.ecomapp.customer.repository.AdresseRepo;
import com.ecomapp.customer.repository.CustomerRepo;
import com.ecomapp.customer.repository.RoleRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
    private AdresseRepo adresseRepo;
    @Override
    public List<CustomerDto> allCustomer() {
        return this.customerRepo.findAll().stream().map(customer -> mapper.todto(customer)).collect(Collectors.toList());
    }

    @Override
    public List<CustomerDto> allAdmins() {
        return  this.customerRepo.findAll().stream().filter(Customer::isAdmin).map(customer -> mapper.todto(customer)).collect(Collectors.toList());
    }

    @Override
    public CustomerDto getCustomerById(Long id) {
        return this.customerRepo.findById(id).map(customer -> {
                    customer.setAdresse(customer.getAdresse());
                    CustomerDto customerDto1 = new CustomerDto();
                    customerDto1.setId(customer.getId());
                    customerDto1.setAdresse(customer.getAdresse());
                    customerDto1.setRoles((List<Role>) customer.getRoles());
                    customerDto1.setEmail(customer.getEmail());
                    customerDto1.setName(customer.getName());
                    return customerDto1;
                })
                .orElseThrow(()->new NotCustomerFound("no customer found ") );
    }

    @Override
    public CustomerDto addCustomer(CustomerSignUpDto customerSignUpDto) {
        if (customerRepo.existsByEmail(customerSignUpDto.getEmail())){
            throw new UserWithEmailExist("user with this email aleardy exist ");
        }
        Customer customer = new Customer();
        Adresse adresse = new Adresse();

        if (!customerSignUpDto.isAdmin()){
            Role role = roleRepo.findByRolename("ROLE_USER");
            customer.setEmail(customerSignUpDto.getEmail());
            customer.setName(customerSignUpDto.getName());
            customer.setPassword(passwordEncoder.encode(customerSignUpDto.getPassword()));
            customer.setAdmin(false);
            addRoleToCustomer(customer,role);
            adresseRepo.save(adresse);
            customer.setAdresse(adresse);
            customerRepo.saveAndFlush(customer);
            adresse.setCustomer(customer);
            //adresseRepo.save(adresse);

        }else {
            Role role = roleRepo.findByRolename("ROLE_ADMIN");
            customer.setEmail(customerSignUpDto.getEmail());
            customer.setName(customerSignUpDto.getName());
            customer.setPassword(passwordEncoder.encode(customerSignUpDto.getPassword()));
            customer.setAdmin(true);
            addRoleToCustomer(customer,role);
            adresseRepo.save(adresse);
            customer.setAdresse(adresse);
            customerRepo.saveAndFlush(customer);
            adresse.setCustomer(customer);
            //adresseRepo.save( adresse);

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
    @Transactional
    public CustomerDto updateCustomer(CustomerDto customerDto) {
        System.out.println("updating");
        Customer customer = this.customerRepo.findById(customerDto.getId()).orElse(null);
        if (customer != null){
            customer.getAdresse().setCity(customerDto.getAdresse().getCity());
            customer.getAdresse().setStreet(customerDto.getAdresse().getStreet());
            customer.getAdresse().setCountry(customerDto.getAdresse().getCountry());
            customer.setEmail(customerDto.getEmail());
            customer.setName(customerDto.getName());
            Customer save = customerRepo.save(customer);
            CustomerDto customerDto1 = new CustomerDto();
            customerDto1.setId(save.getId());
            customerDto1.setAdresse(save.getAdresse());
            customerDto1.setRoles((List<Role>) save.getRoles());
            customerDto1.setEmail(save.getEmail());
            customerDto1.setName(save.getName());
            return customerDto1;
        }else throw new RuntimeException("user not found");

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
