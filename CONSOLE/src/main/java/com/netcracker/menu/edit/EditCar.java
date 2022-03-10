package com.netcracker.menu.edit;

import com.netcracker.marka.CarClient;
import com.netcracker.menu.Menu;
import com.netcracker.menu.validator.ValidatorImpl;
import com.netcracker.menu.validator.ValidatorInstruments;
import com.netcracker.servisec.UserSession;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.util.Scanner;

@Slf4j
public class EditCar implements Menu {

    private final CarClient carClient;
    private final ValidatorInstruments validator = new ValidatorImpl();

    @Override
    public void preMessage(String parentsName) {
        log.info("Enter 1 {}" , parentsName);
    }

    public EditCar(String numberCar) {
        this.carClient = UserSession.getCloneClientSession().getCarClients().stream().filter(x -> x.getMetadataCar().equalsIgnoreCase(numberCar)).findFirst().get();
    }

    @Override
    public void run(Scanner in, String parentsName) throws IOException {
        log.info("Descriptions");
        if (validator.edit(this.carClient.getSummer(), in)) {
            this.carClient.setSummer(validator.getDescription(in));
        }
        log.info("Enter vehicle mileage");
        if (validator.edit(this.carClient.getRun(), in)) {
            this.carClient.setRun(validator.getMileage(in));
        }
        log.info("Enter year of car");
        if (validator.edit(this.carClient.getEar(), in)) {
            this.carClient.setEar(validator.getYear(in));
        }
        log.info("Enter number of the car");
        if (validator.edit(this.carClient.getMetadataCar(), in)) {
            this.carClient.setSummer(validator.getNumberCar(in));
        }
    }

    public CarClient getCarClient() {
        return carClient;
    }

}