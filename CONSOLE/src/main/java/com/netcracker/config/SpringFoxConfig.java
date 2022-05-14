package com.netcracker.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Slf4j
@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig {

 @Bean
 public Docket client() {
  return new Docket(DocumentationType.OAS_30)
   .groupName("Client")
   .select()
   .paths(PathSelectors.ant("/person/**"))
   .build()
   .apiInfo(apiInfo())
   .securityContexts(Arrays.asList(securityContexts()))
   .securitySchemes(Arrays.asList(securitySchemes()));
 }

 private SecurityScheme securitySchemes() {
  return new ApiKey("Authorization",
   "token", "header");
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
  return new Docket(DocumentationType.OAS_30)
   .groupName("Master")
   .select()
   .paths(PathSelectors.ant("/aut/**"))
   .build()
   .apiInfo(apiInfo())
   .securityContexts(Arrays.asList(securityContexts()))
   .securitySchemes(Arrays.asList(securitySchemes()));
 }

 @Bean
 public Docket registration() {
  return new Docket(DocumentationType.OAS_30)
   .groupName("Registration")
   .select()
   .paths(PathSelectors.ant("/registration/**"))
   .build()
   .apiInfo(apiInfo());
 }

 @Bean
 public Docket masterReceiver() {
  return new Docket(DocumentationType.OAS_30)
   .groupName("MasterReceiver")
   .select()
   .paths(PathSelectors.ant("/details/**"))
   .build()
   .apiInfo(apiInfo())
   .securityContexts(Arrays.asList(securityContexts()))
   .securitySchemes(Arrays.asList(securitySchemes()));
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
