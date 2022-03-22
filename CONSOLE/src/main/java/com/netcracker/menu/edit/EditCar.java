package com.netcracker.menu.edit;

import com.netcracker.marka.CarClient;
import com.netcracker.menu.Menu;
import com.netcracker.menu.validator.ValidatorInstrumentsImpl;
import com.netcracker.menu.validator.ValidatorInstruments;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.util.Scanner;

@Slf4j
public class EditCar implements Menu {
                                         
  private final CarClient carClient;
  private final ValidatorInstruments validator = new ValidatorInstrumentsImpl();

  @Override
  public void preMessage(String parentsName) {
    log.info("Enter 1 {}", parentsName);
  }

  public EditCar(CarClient client) {
    this.carClient = client;
  }

  @Override
  public void run(Scanner in, String parentsName) throws IOException {
    log.info("Descriptions");
    if (validator.edit(this.carClient.getSummer(), in)) {
      this.carClient.setSummer(validator.validateDescription(in));
    }
    log.info("Enter vehicle mileage");
    if (validator.edit(this.carClient.getRun(), in)) {
      this.carClient.setRun(validator.validateMileage(in));
    }
    log.info("Enter year of car");
    if (validator.edit(this.carClient.getEar(), in)) {
      this.carClient.setEar(validator.validateYear(in));
    }
    log.info("Enter number of the car");
    if (validator.edit(this.carClient.getMetadataCar(), in)) {
      this.carClient.setSummer(validator.validateNumberCar(in));
    }
  }

  public CarClient getCarClient() {
    return carClient;
  }

}