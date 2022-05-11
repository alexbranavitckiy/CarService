package com.netcracker.config;

import com.netcracker.DTO.response.ContactConfirmationPayload;
import com.netcracker.security.jwt.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class HeaderRequestInterceptor implements ClientHttpRequestInterceptor {

 private final String headerName;

 private final String headerValue;


 public HeaderRequestInterceptor(String headerName, String headerValue) {
  this.headerName = headerName;
  this.headerValue = headerValue;
 }

 @Override
 public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
  request.getHeaders().set(headerName, headerValue);
  return execution.execute(request, body);
 }


}