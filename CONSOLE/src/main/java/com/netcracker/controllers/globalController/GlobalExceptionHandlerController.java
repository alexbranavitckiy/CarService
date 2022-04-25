package com.netcracker.controllers.globalController;


import com.netcracker.DTO.errs.EmptySearchException;
import com.netcracker.DTO.errs.SaveErrorException;
import com.netcracker.DTO.response.ApiResponse;
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

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandlerController {

 @ExceptionHandler({ConstraintViolationException.class})
 @ResponseStatus(HttpStatus.BAD_REQUEST)
 @ResponseBody
 ValidationErrorResponse onConstraintValidationException(
  ConstraintViolationException e, SaveErrorException s) {
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
 ValidationErrorResponse onMethodArgumentNotValidException(
  MethodArgumentNotValidException e) {
  ValidationErrorResponse error = new ValidationErrorResponse();
  for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
   error.getViolations().add(
    new Violation(fieldError.getField(), fieldError.getDefaultMessage()));
  }
  return error;
 }




}
