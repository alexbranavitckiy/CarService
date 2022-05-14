package com.netcracker.config;


import com.netcracker.DTO.response.ContactConfirmationPayload;
import com.netcracker.security.jwt.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@TestConfiguration
public class RestTemplateFactory {

 @Autowired
 private JWTUtil jwtUtil;

 @Bean(name = "rapidApiRestTemplate")
 public RestTemplate rapidApiRestTemplate() {
  RestTemplate restTemplate = new RestTemplate();
  List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
  interceptors.add(new HeaderRequestInterceptor("Authorization", "Bearer "
   + jwtUtil.generateToken(new ContactConfirmationPayload("clients", "clients"))));
  restTemplate.setInterceptors(interceptors);
  return restTemplate;
 }


}