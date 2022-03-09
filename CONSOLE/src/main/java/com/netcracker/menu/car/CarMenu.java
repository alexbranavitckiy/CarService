package com.netcracker.menu.car;

import com.netcracker.marka.CarClient;
import com.netcracker.menu.Menu;
import com.netcracker.menu.edit.EditCar;
import com.netcracker.servisec.ClientServices;
import com.netcracker.servisec.Impl.client.ClientServicesImpl;
import com.netcracker.servisec.UserSession;
import com.netcracker.user.Client;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

@Slf4j
public class CarMenu implements Menu {

    private final ClientServices clientServices = new ClientServicesImpl();

    @Override
    public void preMessage(String nameMenu) {
        log.info("Enter 1.{}" , nameMenu);
        log.info("Enter 2. Display a list of cars");
        log.info("Enter 5. Select a car to edit");
        log.info("Enter 6 to add car data");
    }

    @Override
    public void run(Scanner in, String parentsName) throws IOException {
        this.preMessage(parentsName);
        label:   while (true) {
            switch (in.next()) {
                case "1": {
                    break label;
                }
                case "2": {
                    UserSession.getClientSession().ifPresent(x -> {
                        if (x.getCarClients().size() > 0) {
                            log.info(x.getCarClients().toString());
                            log.info("Enter 1.Close selection and editor menu");
                            log.info("Enter 2. Display a list of cars");
                            log.info("Enter 5 to go to a specific car");
                        } else {
                            log.info("No car data found");
                            log.info("Enter 6 to add car data");
                        }
                    });
                    break;
                }
                case "5": {
                    Set<CarClient> carClientSet = UserSession.getCloneClientSession().getCarClients();
                    UserSession.getClientSession().ifPresent(x -> {
                        if (x.getCarClients().size() > 0) {
                            log.info(carClientSet.toString());
                            log.info("Enter car number(metadata car)");
                            String metadataCar = in.next();
                            x.getCarClients()
                                    .stream().filter(Objects::nonNull)
                                    .filter(z -> z.getMetadataCar().equalsIgnoreCase(metadataCar)
                                    ).forEach(System.out::println);
                            log.info("Edit selected car?");
                            log.info("Enter 1 to continue editing");
                            log.info("Enter 2 to edit");
                            if (in.next().equalsIgnoreCase("2")) {
                                this.preMessage(parentsName);
                            } else {
                                EditCar editCar = new EditCar(metadataCar);
                                try {
                                    editCar.run(in, "Car menu");
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                if (clientServices.updateClientCar(editCar.getCarClient()))
                                    log.info("Data entered successfully");
                                else
                                    log.info("An input error occurred while entering data. Retry data change");
                                this.preMessage(parentsName);
                            }
                        } else {
                            log.info("No car data found.");
                            log.info("Enter 1.Close selection and editor menu");
                            log.info("Enter 6 to add car data");
                        }
                    });
                    break;
                }
                case "6": {
                    NewCarClient newCarClient = new NewCarClient();
                    newCarClient.run(in, "");
                    Client client = UserSession.getCloneClientSession();
                    if (client.getCarClients() != null) {
                        client.getCarClients().add(newCarClient.getCarClient().get());
                    }
                    if (clientServices.updateClient(client)) {
                        log.info(client.toString());
                        log.info("Data added successfully");
                    } else System.out.println("An error occurred while entering data, please try again");
                    this.preMessage(parentsName);
                    break;
                }
                default: {
                    this.preMessage(parentsName);
                    break;
                }
            }
        }
    }


}
