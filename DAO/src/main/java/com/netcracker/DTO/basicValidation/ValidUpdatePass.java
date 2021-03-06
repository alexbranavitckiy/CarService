package com.netcracker.DTO.basicValidation;

import com.netcracker.DTO.response.ContactConfirmationPayload;
import com.netcracker.services.ClientServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
@Component
public class ValidUpdatePass
 implements ConstraintValidator<ValidPasswordLogin, ContactConfirmationPayload> {

 private final ClientServices clientServices;

 @Autowired
 ValidUpdatePass(ClientServices clientServices) {
  this.clientServices = clientServices;
 }

 public void initialize(ValidPasswordLogin constraint) {
 }

 public boolean isValid(ContactConfirmationPayload person, ConstraintValidatorContext context) {


  return true;
 }

}