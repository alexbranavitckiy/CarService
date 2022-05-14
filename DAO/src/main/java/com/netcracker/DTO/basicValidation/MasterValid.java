package com.netcracker.DTO.basicValidation;

import com.netcracker.DTO.user.MasterDto;
import com.netcracker.DTO.errs.SaveSearchErrorException;
import com.netcracker.services.ClientServices;
import com.netcracker.services.MasterServices;
import com.netcracker.user.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;


@Slf4j
@Component
public class MasterValid implements ConstraintValidator<ValidMaster, MasterDto> {

 private final MasterServices masterServices;
 private final ClientServices clientServices;

 @Autowired
 MasterValid(ClientServices clientServices, MasterServices masterServices) {
  this.clientServices = clientServices;
  this.masterServices = masterServices;
 }

 public void initialize(ValidMaster constraint) {
 }

 public boolean isValid(MasterDto person, ConstraintValidatorContext context) {
  try {
   if (person.getLogin() != null)
    clientServices.loginChek(person.getLogin());
   if (person.getRole() != null && Arrays.stream(Role.values()).filter(x ->
    x.equals(person.getRole())).findFirst().isEmpty())
    throw new SaveSearchErrorException("Introduced non-existent role", "Role");
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