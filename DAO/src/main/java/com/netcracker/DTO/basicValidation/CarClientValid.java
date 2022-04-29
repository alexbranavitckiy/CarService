package com.netcracker.DTO.basicValidation;

import com.netcracker.DTO.car.CarClientDto;
import com.netcracker.DTO.errs.SaveSearchErrorException;
import com.netcracker.services.CarServices;
import com.netcracker.services.impl.MarkServicesImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
@Component
public class CarClientValid
 implements ConstraintValidator<ValidCarClient, CarClientDto> {

 private final CarServices carServices;
 private final MarkServicesImpl markServices;

 @Autowired
 CarClientValid(CarServices carServices, MarkServicesImpl markServices) {
  this.markServices = markServices;
  this.carServices = carServices;
 }

 public void initialize(ValidCarClient constraint) {
 }

 public boolean isValid(CarClientDto clientDto, ConstraintValidatorContext context) {
  try {
   if (clientDto.getMetadataCar() != null) {
    carServices.metadataCarChek(clientDto.getMetadataCar());
   }
   if (clientDto.getMark() != null) {
    markServices.metadataMark(clientDto.getMark().getId());
   }
   if (clientDto.getId() != null) {
    carServices.idChek(clientDto.getId());
   }
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