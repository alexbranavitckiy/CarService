package com.netcracker.DTO.basicValidation;

import com.netcracker.DTO.clients.MasterDto;
import com.netcracker.DTO.errs.SaveSearchErrorException;
import com.netcracker.DTO.ord.OrderDto;
import com.netcracker.services.CarServices;
import com.netcracker.services.MasterServices;
import com.netcracker.services.OrderServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


@Slf4j
@Component
public class MasterValid implements ConstraintValidator<ValidMaster, MasterDto> {

 private final MasterServices masterServices;


 @Autowired
 MasterValid(MasterServices masterServices) {
  this.masterServices = masterServices;
 }

 public void initialize(ValidMaster constraint) {
 }

 public boolean isValid(MasterDto person, ConstraintValidatorContext context) {
  try {
   if (person.getPassword() != null)
    masterServices.passwordChek(person.getPassword());
   if (person.getMail() != null)
    masterServices.emailChek(person.getMail());
   if (person.getPhone() != null)
    masterServices.phoneChek(person.getPhone());
   if (person.getLogin() != null)
    masterServices.loginChek(person.getLogin());
  } catch (SaveSearchErrorException e) {
   context.disableDefaultConstraintViolation();
   context.buildConstraintViolationWithTemplate(e.getMessage())
    .addPropertyNode(e.getField())
    .addConstraintViolation();
   return false;
  }
  return true;
 }

}