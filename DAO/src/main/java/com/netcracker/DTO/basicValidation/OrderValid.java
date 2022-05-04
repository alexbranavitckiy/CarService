package com.netcracker.DTO.basicValidation;


import com.netcracker.DTO.errs.SaveSearchErrorException;
import com.netcracker.DTO.ord.OrderDto;
import com.netcracker.services.CarServices;
import com.netcracker.services.OrderServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


@Slf4j
@Component
public class OrderValid implements ConstraintValidator<ValidOrder, OrderDto> {

 private final OrderServices orderServices;
 private final CarServices carServices;


 @Autowired
 OrderValid(CarServices carServices, OrderServices orderServices) {
  this.carServices = carServices;
  this.orderServices = orderServices;
 }

 public void initialize(ValidOrder constraint) {
 }

 public boolean isValid(OrderDto orderDto, ConstraintValidatorContext context) {
  try {
   if (orderDto.getCarClient() != null) {
    carServices.idChek(orderDto.getCarClient());
   }
   if (orderDto.getId() != null) {
    orderServices.idChek(orderDto.getId());
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