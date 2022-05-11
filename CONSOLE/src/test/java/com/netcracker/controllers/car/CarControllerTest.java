package com.netcracker.controllers.car;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.CarServiceApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;


@Slf4j
@SpringBootTest(classes = CarServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarControllerTest {

 @LocalServerPort
 private int port;

 @Autowired
 @Qualifier("rapidApiRestTemplate")
 private RestTemplate restTemplate;

 @Test
 void getAllCarByIdClients()  {
  HttpHeaders headers = new HttpHeaders();
  String url = "http://localhost:" + port + "/person/garage/get-all";
  HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
  ResponseEntity<String> response = restTemplate.exchange(
   url, HttpMethod.GET, requestEntity, String.class);
  Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
 }

}