package com.netcracker.config;

import com.netcracker.annotations.ClientLabel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Slf4j
@Configuration
public class SpringFoxConfig {



 @Bean
 public Docket client() {
  return new Docket(DocumentationType.SWAGGER_2)
   .groupName("Client")
   .select()
   .apis(RequestHandlerSelectors.withMethodAnnotation(ClientLabel.class))
   .build()
   .apiInfo(apiInfo())
   .securityContexts(Arrays.asList(securityContexts()))
   .securitySchemes(Arrays.asList(securitySchemes()));
 }

 private SecurityScheme securitySchemes() {
  return new ApiKey("Authorization", "token", "header");
 }

 private SecurityContext securityContexts() {
  return SecurityContext.builder()
   .securityReferences(defaultAuth())
   .forPaths(PathSelectors.any())
   .build();
 }
 private List<SecurityReference> defaultAuth() {
  AuthorizationScope authorizationScope = new AuthorizationScope("xxx", "");
  AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
  authorizationScopes[0] = authorizationScope;
  return Arrays.asList(new SecurityReference("Authorization", authorizationScopes));
 }

 @Bean
 public Docket master() {
  return new Docket(DocumentationType.SWAGGER_2)
   .groupName("Master")
   .select()
   .paths(PathSelectors.ant("/aut/**"))
   .build()
   .apiInfo(apiInfo());
 }

 @Bean
 public Docket registration() {
  return new Docket(DocumentationType.SWAGGER_2)
   .groupName("Registration")
   .select()
   .paths(PathSelectors.ant("/registration/**"))
   .build()
   .apiInfo(apiInfo());
 }

 @Bean
 public Docket masterReceiver() {
  return new Docket(DocumentationType.SWAGGER_2)
   .groupName("MasterReceiver")
   .select()
   .paths(PathSelectors.ant("/pivot/**"))
   .build()
   .apiInfo(apiInfo());
 }

 private ApiInfo apiInfo() {
  return new ApiInfo(
   "Car-Service API",
   "API for automating the work of a car service and distributed work of personnel",
   "1.0",
   "Terms of service",
   new Contact("", "www.example.com", "myeaddress@company.com"),
   "License of API", "API license URL", Collections.emptyList());
 }

}
