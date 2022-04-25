package com.netcracker.DTO.basicValidation;

import com.netcracker.DTO.clients.ClientDto;
import com.netcracker.DTO.errs.SaveErrorException;
import com.netcracker.services.ClientServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
@Component
public class Clients
 implements ConstraintValidator<ValidClients, ClientDto> {

 private final ClientServices clientServices;

 @Autowired
 Clients(ClientServices clientServices) {
  this.clientServices = clientServices;
 }

 public void initialize(ValidClients constraint) {
 }

 public boolean isValid(ClientDto person, ConstraintValidatorContext context) {
  try {
   clientServices.passwordChek(person.getPassword());
   clientServices.emailChek(person.getEmail());
   clientServices.phoneChek(person.getPhone());
   clientServices.loginChek(person.getLogin());
  } catch (SaveErrorException e) {
   context.disableDefaultConstraintViolation();
   context.buildConstraintViolationWithTemplate(e.getMessage())
    .addPropertyNode(e.getField())
    .addConstraintViolation();
   return false;
  }
  return true;
 }

}