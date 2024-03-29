package com.ecomapp.customer;

import com.ecomapp.customer.Dto.CustomerSignUpDto;
import com.ecomapp.customer.entity.Role;
import com.ecomapp.customer.repository.RoleRepo;
import com.ecomapp.customer.service.CustomerInter;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.hibernate.cfg.Environment;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@OpenAPIDefinition(info =
@Info(title = "Employee API", version = "1.0", description = "Documentation Employee API v1.0")
)
public class CustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }
    @Bean
    CommandLineRunner start(CustomerInter customerInter , RoleRepo roleRepo){
        return args -> {
            roleRepo.saveAndFlush(Role.builder().rolename("ROLE_USER").build());
            roleRepo.saveAndFlush(Role.builder().rolename("ROLE_ADMIN").build());
            customerInter.addCustomer(new CustomerSignUpDto("mehdi","elmahdi.chiheb@uit.ac.ma","pass123",false));
            customerInter.addCustomer(new CustomerSignUpDto("mehdi2","elmahdichiheb@gmail.com","pass123",false));
            customerInter.addCustomer(new CustomerSignUpDto("test","lika84@gmail.com","pass123",true));
            customerInter.addCustomer(new CustomerSignUpDto("test","hiporca2000@gmail.com","pass123",true));

        };
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }




}
