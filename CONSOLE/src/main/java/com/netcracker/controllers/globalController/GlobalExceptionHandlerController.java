package com.netcracker.controllers.globalController;

import com.netcracker.DTO.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.naming.ServiceUnavailableException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;


@Slf4j
@ControllerAdvice
public class GlobalExceptionHandlerController extends ResponseEntityExceptionHandler {


 @Override
 protected ResponseEntity<Object> handleHttpMessageNotReadable
  (HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
  ApiResponse response = ApiResponse.builder()
   .httpStatus(status)
   .messageUser("Invalid JSON")
   .localDateTime(LocalDateTime.now())
   .stackTrace(ex.getMessage())
   .error_code("502")
   .build();
  return new ResponseEntity<>(response, status);
 }

 @ExceptionHandler(MethodArgumentTypeMismatchException.class)
 protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, HttpStatus status,
                                                                   WebRequest request) {
  ApiResponse apiError = new ApiResponse();
  apiError.setLocalDateTime(LocalDateTime.now());
  apiError.setMessageUser(String.format("The parameter '%s' of value '%s' could not be converted to type '%s'",
   ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName()));
  apiError.setDebugMessage(ex.getMessage());
  return new ResponseEntity<>(apiError, status);
 }

 @Override
 protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
                                                                HttpStatus status, WebRequest request) {
  return new ResponseEntity<>(ApiResponse.builder()
   .messageUser("No Handler Found")
   .localDateTime(LocalDateTime.now())
   .error_code(ex.getMessage()).build(), status);
 }

 @ResponseBody
 @ResponseStatus(HttpStatus.BAD_REQUEST)
 @ExceptionHandler(Exception.class)
 protected ResponseEntity<Object> handleCustomAPIException(Exception ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
  log.warn(ex.getMessage());
  ApiResponse response = ApiResponse.builder()
   .httpStatus(status)
   .localDateTime(LocalDateTime.now())
   .messageUser("Something went wrong")
   .stackTrace(ex.getLocalizedMessage())
   .error_code("502")
   .build();
  return new ResponseEntity<>(response, response.getHttpStatus());
 }

 @ResponseStatus(
  value = HttpStatus.GATEWAY_TIMEOUT,
  reason = "Upstream Service Not Responding, Try Again")
 @ExceptionHandler(ServiceUnavailableException.class)
 public void handleException(ServiceUnavailableException e) {
  log.warn(e.getMessage());
 }

}
