package com.muhamad.adli.backend;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;

@SpringBootApplication
@OpenAPIDefinition
public class BackendApplication {

    public static void main(String[] args){SpringApplication.run(BackendApplication.class, args);
    }
}
