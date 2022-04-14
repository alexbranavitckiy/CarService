package com.netcracker.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import java.util.Collections;

@Configuration
public class SpringFoxConfig {

 @Bean
 public Docket docket() {
  return new Docket(DocumentationType.SWAGGER_2)
   .select()
   .apis(RequestHandlerSelectors.any())
   .paths(PathSelectors.any())
   .build()
   .apiInfo(apiInfo());
 }


 private ApiInfo apiInfo() {
  return new ApiInfo(
   "Car-Service API",
   "API for automating the work of a car service and distributed work of personnel",
   "1.0",
   "Terms of service",
   new Contact("Alex", "www.example.com", "myeaddress@company.com"),
   "License of API", "API license URL", Collections.emptyList());
 }


}