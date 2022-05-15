package com.netcracker.controllers.globalController;

import com.netcracker.DTO.errs.ApiError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.naming.ServiceUnavailableException;
import java.time.LocalDateTime;


@Slf4j
@ControllerAdvice
public class GlobalExceptionHandlerController extends ResponseEntityExceptionHandler {


 @Override
 protected ResponseEntity<Object> handleHttpMessageNotReadable
  (HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
  ApiError response = ApiError.builder()
   .httpStatus(status)
   .messageUser("Invalid JSON")
   .localDateTime(LocalDateTime.now())
   .stack(ex.getMessage())
   .error_code("502")
   .build();
  return new ResponseEntity<>(response, status);
 }

 @ExceptionHandler(MethodArgumentTypeMismatchException.class)
 protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, HttpStatus status,
                                                                   WebRequest request) {
  ApiError apiError = new ApiError(String.format("The parameter '%s' of value '%s' could not be converted to type '%s'",
   ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName()));
  apiError.setLocalDateTime(LocalDateTime.now());
  apiError.setDebugMessage(ex.getMessage());
  return new ResponseEntity<>(apiError, status);
 }

 @Override
 protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
                                                                HttpStatus status, WebRequest request) {
  return new ResponseEntity<>(ApiError.builder()
   .messageUser("No Handler Found")
   .localDateTime(LocalDateTime.now())
   .error_code(ex.getMessage()).build(), status);
 }

 @ResponseBody
 @ResponseStatus(HttpStatus.BAD_REQUEST)
 @ExceptionHandler(Exception.class)
 protected ResponseEntity<Object> handleCustomAPIException(Exception ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
  log.warn(ex.getMessage());
  ApiError response = ApiError.builder()
   .httpStatus(status)
   .localDateTime(LocalDateTime.now())
   .messageUser("Something went wrong")
   .stack(ex.getLocalizedMessage())
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
