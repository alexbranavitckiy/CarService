package com.netcracker.security.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.DTO.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class ExceptionHandlerFilter extends OncePerRequestFilter {

 @Override
 public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
  try {
   filterChain.doFilter(request, response);
  } catch (RuntimeException e) {
   log.warn(e.getMessage());
   ApiResponse errorResponse = new ApiResponse();
   errorResponse.setMessageUser(errorResponse.getMessageUser());
   response.setStatus(HttpStatus.OK.value());
  }
  // filterChain.doFilter(request, response);
 }
}
