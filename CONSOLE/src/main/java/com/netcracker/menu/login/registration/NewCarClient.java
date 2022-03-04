package com.netcracker.menu.login.registration;

import com.netcracker.marka.CarClient;
import com.netcracker.menu.Menu;
import com.netcracker.servisec.Impl.CRUDServicesImpl;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

public class NewCarClient implements Menu {

    private Optional<CarClient> carClient;

    @Override
    public void run(Scanner in, String parentsName) throws IOException {
        CarClient carClient = new CarClient();
        System.out.println("Enter descriptions or skip");
        carClient.setSummer(in.next());
        carClient.setId(UUID.randomUUID());
        System.out.println("Enter vehicle mileage");
        carClient.setRun(in.nextDouble());
        System.out.println("Enter year of car");
        carClient.setEar(in.next());
        System.out.println("Enter number of the car");
        carClient.setMetadataCar(in.next());
        this.carClient = Optional.of(carClient);
    }

    @Override
    public void preMessage(String parentsName) {

    }

    public Optional<CarClient> getCarClient() {
        return carClient;
    }

    public void setCarClient(CarClient carClient) {
        this.carClient = Optional.of(carClient);
    }
}
