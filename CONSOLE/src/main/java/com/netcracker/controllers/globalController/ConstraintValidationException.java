package com.netcracker.controllers.globalController;


import com.netcracker.DTO.errs.ApiError;
import com.netcracker.DTO.errs.SaveSearchErrorException;
import com.netcracker.DTO.response.ValidationErrorResponse;
import com.netcracker.DTO.response.Violation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;

@Slf4j
@ControllerAdvice
public class ConstraintValidationException {

 @ExceptionHandler({ConstraintViolationException.class})
 @ResponseStatus(HttpStatus.BAD_REQUEST)
 @ResponseBody
 ValidationErrorResponse onConstraintValidationException(ConstraintViolationException e, SaveSearchErrorException s) {
  ValidationErrorResponse error = new ValidationErrorResponse();
  for (ConstraintViolation violation : e.getConstraintViolations()) {
   error.getViolations().add(
    new Violation(violation.getPropertyPath().toString(), violation.getMessage()));
  }
  return error;
 }



 @ExceptionHandler(MethodArgumentNotValidException.class)
 @ResponseStatus(HttpStatus.BAD_REQUEST)
 @ResponseBody
 ValidationErrorResponse onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
  ValidationErrorResponse error = new ValidationErrorResponse();
  for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
   error.getViolations().add(
    new Violation(fieldError.getField(), fieldError.getDefaultMessage()));
  }
  return error;
 }

 @ResponseBody
 @ResponseStatus(HttpStatus.BAD_REQUEST)
 @ExceptionHandler({SaveSearchErrorException.class})
 ResponseEntity<ValidationErrorResponse> onConstraintValidationException(SaveSearchErrorException s) {
  ValidationErrorResponse error = new ValidationErrorResponse();
  error.getViolations().add(new Violation(s.getField(), s.getMessage()));
  return ResponseEntity.ok(error);
 }

 @ResponseBody
 @ResponseStatus(HttpStatus.BAD_REQUEST)
 @ExceptionHandler({ApiError.class})
 ResponseEntity<ApiError> onApiException(ApiError s) {
  s.setLocalDateTime(LocalDateTime.now());
  return ResponseEntity.ok(s);
 }

}
