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

import static com.netcracker.menu.validator.ValidatorInstrumentsImpl.VALIDATOR_INSTRUMENTS;

@Slf4j
public class CreateCarClient implements Menu {

 private CarClient carClient;
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
   .summary(VALIDATOR_INSTRUMENTS.validateSummary(in))
   .metadataCar(VALIDATOR_INSTRUMENTS.validateNumberCar(in))
   .run(VALIDATOR_INSTRUMENTS.validateMileage(in))
   .mark(new Mark())
   .ear(VALIDATOR_INSTRUMENTS.validateYear(in))
   .description(VALIDATOR_INSTRUMENTS.validateDescription(in))
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
