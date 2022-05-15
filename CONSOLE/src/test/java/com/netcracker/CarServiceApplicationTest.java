package com.netcracker;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(classes = CarServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarServiceApplicationTest {

 @Autowired
 private CarServiceApplication controller;

 @Test
 public void contextLoads() {
  assertThat(controller).isNotNull();
 }

}