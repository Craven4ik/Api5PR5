package com.example.PR5;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Contact App", version = "1.0.0", contact = @Contact(name = "Glushko Nikita", email = "polosatay_zebra@mail.ru", url = "https://github.com/HukoJlauII")))
public class PR5Application {

    public static void main(String[] args) {
        SpringApplication.run(PR5Application.class, args);
    }

}
