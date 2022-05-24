package com.netcracker.DTO.basicValidation;

import com.netcracker.DTO.errs.SaveSearchErrorException;
import com.netcracker.DTO.ord.OrderDto;
import com.netcracker.DTO.ord.OutfitDto;
import com.netcracker.services.CarServices;
import com.netcracker.services.OrderServices;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;


@Slf4j
@Component
public class OutfitValid implements ConstraintValidator<ValidOutfit, OutfitDto> {

 public void initialize(ValidOrder constraint) {
 }

 @SneakyThrows
 public boolean isValid(OutfitDto orderDto, ConstraintValidatorContext context) {
  if (orderDto.getDateStart() != null && orderDto.getDateEnd() != null) {
   if (orderDto.getDateEnd().before(Date.from(new Date().toInstant())))
    throw new SaveSearchErrorException("Invalid data entered", "Start date");
   if (orderDto.getDateStart().before(Date.from(new Date().toInstant()))|| !orderDto.getDateStart()
    .before(orderDto.getDateEnd())) throw new SaveSearchErrorException("Invalid data entered", "End date");
  }
  return true;
 }

}