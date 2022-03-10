package com.netcracker.menu.car;

import com.netcracker.marka.CarClient;
import com.netcracker.menu.Menu;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

@Slf4j
public class NewCarClient implements Menu {

    private CarClient carClient;
    private final StringBuilder stringBuilder = new StringBuilder();


    @Override
    public void run(Scanner in, String parentsName) throws IOException {
        CarClient carClient = new CarClient();
        log.info("Enter descriptions or skip");
        carClient.setSummer(in.next());
        carClient.setId(UUID.randomUUID());
        log.info("Enter vehicle mileage");
        carClient.setRun(in.next());
        log.info("Enter year of car");
        carClient.setEar(in.next());
        log.info("Enter number of the car");
        carClient.setMetadataCar(in.next());
        this.carClient = carClient;
    }

    @Override
    public void preMessage(String parentsName) {

    }

    public Optional<CarClient> getCarClient() {
        return Optional.of(carClient);
    }

    private void checkStr(Scanner in, int minStr, int maxStr) {
        stringBuilder.append(in.next());
        if (stringBuilder.length() < maxStr && stringBuilder.length() > minStr) {
            System.out.println("Car number from 4 to 40 characters");
        }
    }
}
