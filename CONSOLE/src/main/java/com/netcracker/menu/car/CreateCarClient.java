package com.netcracker.menu.car;

import com.netcracker.marka.CarClient;
import com.netcracker.marka.Mark;
import com.netcracker.menu.Menu;
import com.netcracker.menu.validator.ValidatorInstrumentsImpl;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

@Slf4j
public class CreateCarClient implements Menu {

 private CarClient carClient;
 private final ValidatorInstrumentsImpl validator = new ValidatorInstrumentsImpl();
 private final UUID uuidClient;

 public CreateCarClient(UUID uuidClient) {
  this.uuidClient = uuidClient;
 }

 @Override
 public void run(Scanner in, String parentsName) throws IOException {
  this.preMessage(parentsName);
  this.carClient = CarClient.builder()
   .id(UUID.randomUUID())
   .id_clients(uuidClient)
   .summary(validator.validateSummary(in))
   .metadataCar(validator.validateNumberCar(in))
   .run(validator.validateMileage(in))
   .mark(new Mark())
   .ear(validator.validateYear(in))
   .descriptions(validator.validateDescription(in))
   .build();
 }

 @Override
 public void preMessage(String parentsName) {
  log.info(parentsName);
 }

 public Optional<CarClient> getCarClient() {
  return Optional.of(carClient);
 }

}
