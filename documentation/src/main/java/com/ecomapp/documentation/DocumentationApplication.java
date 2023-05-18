package com.ecomapp.documentation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableScheduling
@SpringBootApplication
public class DocumentationApplication {
    public static void main(String[] args) {
        SpringApplication.run(DocumentationApplication.class, args);
    }

}
