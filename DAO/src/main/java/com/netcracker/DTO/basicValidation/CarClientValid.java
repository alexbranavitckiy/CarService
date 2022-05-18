package com.netcracker.DTO.basicValidation;

import com.netcracker.DTO.car.CarClientDto;
import com.netcracker.DTO.errs.SaveSearchErrorException;
import com.netcracker.car.Mark;
import com.netcracker.services.CarServices;
import com.netcracker.services.ClientServices;
import com.netcracker.services.impl.MarkServicesImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.TimeZone;

@Slf4j
@Component
public class CarClientValid
 implements ConstraintValidator<ValidCarClient, CarClientDto> {

 private final MarkServicesImpl markServices;

 @Autowired
 CarClientValid(MarkServicesImpl markServices) {
  this.markServices = markServices;
 }

 public void initialize(ValidCarClient constraint) {
 }

 public boolean isValid(CarClientDto clientDto, ConstraintValidatorContext context) {
  try {
   if (clientDto.getMark().getId() != null) {
    Optional<Mark> mark = markServices.markById(clientDto.getMark().getId());
    if (mark.isPresent() && this.isWithinRange(clientDto.getYear(), mark.get().getYearStart(), mark.get().getYearEnd())) {
     return true;
    } else
     throw new SaveSearchErrorException("Year of manufacture of the brand does not match the year of the car.", "year");
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

 boolean isWithinRange(Date testDate, Date startDate, Date endDate) {
  return !(testDate.before(startDate) || testDate.after(endDate));
 }

}