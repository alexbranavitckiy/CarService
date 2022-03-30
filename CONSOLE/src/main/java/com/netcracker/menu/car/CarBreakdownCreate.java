package com.netcracker.menu.car;

import com.netcracker.CarBreakdownServices;
import com.netcracker.breakdown.CarBreakdown;
import com.netcracker.factory.ServicesFactory;
import com.netcracker.menu.Menu;
import com.netcracker.menu.validator.ValidatorInstrumentsImpl;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Scanner;
import java.util.UUID;

@Slf4j
public class CarBreakdownCreate implements Menu {

 private CarBreakdown carBreakdown;
 private final ValidatorInstrumentsImpl validator = new ValidatorInstrumentsImpl();
 private final UUID carUUID;
 private final ServicesFactory servicesFactory;
 private final CarBreakdownServices carBreakdownServices;

 public CarBreakdownCreate(UUID carUUID, ServicesFactory servicesFactory) {
  this.servicesFactory = servicesFactory;
  this.carBreakdownServices = servicesFactory.getFactory().getCarBreakdownServices();
  this.carUUID = carUUID;
 }

 @Override
 public void run(Scanner in, String parentsName) throws IOException {
  this.carBreakdown = CarBreakdown.builder()
   .id(UUID.randomUUID())
   .descriptions(validator.validateDescription(in))
   .state(validator.orderStateCarBr(in))
   .carClient(carUUID)
   .location(validator.validateDescription(in))
   .runCarSize(validator.validateMileage(in))
   .build();
  validator.successfullyMessages(carBreakdownServices.addBreakdown(carBreakdown));
 }


 @Override
 public void preMessage(String parentsName) {
  log.info(parentsName);
 }

 public CarBreakdown getCarBreakdown() {
  return carBreakdown;
 }

 public void setCarBreakdown(CarBreakdown carBreakdown) {
  this.carBreakdown = carBreakdown;
 }

}
