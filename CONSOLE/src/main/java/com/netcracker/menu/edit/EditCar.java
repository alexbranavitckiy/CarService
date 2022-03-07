package com.netcracker.menu.edit;

import com.netcracker.marka.CarClient;
import com.netcracker.menu.Menu;
import com.netcracker.servisec.UserSession;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

public class EditCar implements Menu {

    private CarClient carClient;
    private final StringBuilder stringNew = new StringBuilder(20);


    @Override
    public void preMessage(String parentsName) {
        System.out.println("Enter 1 " + parentsName);

    }

    public EditCar(String numberCar) {
        this.carClient = UserSession.getClientSession().get().getCarClients().stream().filter(x -> x.getMetadataCar().equalsIgnoreCase(numberCar)).findFirst().get();
    }


    @Override
    public void run(Scanner in, String parentsName) throws IOException {
        System.out.println("Descriptions");
        if (this.edit(this.carClient.getSummer(), in)) {
            this.carClient.setSummer(stringNew.toString());
        }
        System.out.println("Enter vehicle mileage");
        if (this.edit(this.carClient.getRun(), in)) {
            this.carClient.setRun(stringNew.toString());
        }
        System.out.println("Enter year of car");
        if (this.edit(this.carClient.getEar(), in)) {
            this.carClient.setSummer(stringNew.toString());
        }
        System.out.println("Enter number of the car");
        if (this.edit(this.carClient.getMetadataCar(), in)) {
            this.carClient.setSummer(stringNew.toString());
        }
    }

    public CarClient getCarClient() {
        return carClient;
    }

    public void changeMessage() {
        System.out.println("Enter 1 to skip");
        System.out.println("Enter 2 to edit");
    }


    public boolean edit(String fieldName, Scanner in) {
        System.out.println(fieldName);
        this.changeMessage();
        stringNew.delete(0, stringNew.length());
        if (in.next().equalsIgnoreCase("2")) {
            System.out.println("Enter values");
            stringNew.append(in.next());
            return true;
        }
        return false;
    }
}