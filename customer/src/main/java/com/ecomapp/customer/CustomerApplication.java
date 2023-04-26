package com.ecomapp.customer;

import com.ecomapp.customer.Dto.CustomerSignUpDto;
import com.ecomapp.customer.entity.Role;
import com.ecomapp.customer.repository.RoleRepo;
import com.ecomapp.customer.service.CustomerInter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class CustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }
    @Bean
    CommandLineRunner start(CustomerInter customerInter , RoleRepo roleRepo){
        return args -> {
            roleRepo.saveAndFlush(Role.builder().rolename("ROLE_USER").build());
            roleRepo.saveAndFlush(Role.builder().rolename("ROLE_ADMIN").build());
            customerInter.addCustomer(new CustomerSignUpDto("mehdi","mehdi@gmail.com","pass123",false));

        };
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
