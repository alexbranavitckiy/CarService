package com.netcracker.menu.edit;

import com.netcracker.marka.CarClient;
import com.netcracker.menu.Menu;
import com.netcracker.servisec.UserSession;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.util.Scanner;

@Slf4j
public class EditCar implements Menu {

    private CarClient carClient;
    private final StringBuilder stringNew = new StringBuilder(20);


    @Override
    public void preMessage(String parentsName) {
        log.info("Enter 1 {}" , parentsName);

    }

    public EditCar(String numberCar) {
        this.carClient = UserSession.getClientSession().get().getCarClients().stream().filter(x -> x.getMetadataCar().equalsIgnoreCase(numberCar)).findFirst().get();
    }


    @Override
    public void run(Scanner in, String parentsName) throws IOException {
        log.info("Descriptions");
        if (this.edit(this.carClient.getSummer(), in)) {
            this.carClient.setSummer(stringNew.toString());
        }
        log.info("Enter vehicle mileage");
        if (this.edit(this.carClient.getRun(), in)) {
            this.carClient.setRun(stringNew.toString());
        }
        log.info("Enter year of car");
        if (this.edit(this.carClient.getEar(), in)) {
            this.carClient.setSummer(stringNew.toString());
        }
        log.info("Enter number of the car");
        if (this.edit(this.carClient.getMetadataCar(), in)) {
            this.carClient.setSummer(stringNew.toString());
        }
    }

    public CarClient getCarClient() {
        return carClient;
    }

    public void changeMessage() {
        log.info("Enter 1 to skip");
        log.info("Enter 2 to edit");
    }


    public boolean edit(String fieldName, Scanner in) {
        log.info(fieldName);
        this.changeMessage();
        stringNew.delete(0, stringNew.length());
        if (in.next().equalsIgnoreCase("2")) {
            log.info("Enter values");
            stringNew.append(in.next());
            return true;
        }
        return false;
    }
}