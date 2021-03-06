package com.netcracker;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.netcracker")
public class CarServiceApplication {
 public static void main(String[] args) {
  SpringApplication.run(CarServiceApplication.class, args);
 }
}



