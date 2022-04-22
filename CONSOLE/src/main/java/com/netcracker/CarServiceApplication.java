package com.netcracker;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.netcracker")
@SecurityScheme(name = "techgeeknext-api", scheme = "basic", type = SecuritySchemeType.DEFAULT, in = SecuritySchemeIn.COOKIE)
@OpenAPIDefinition(info = @Info(title = "User API", version = "2.0", description = "User Details"))
public class CarServiceApplication {
 public static void main(String[] args) {
  SpringApplication.run(CarServiceApplication.class, args);
 }
}

