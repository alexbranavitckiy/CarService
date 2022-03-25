package com.netcracker.menu.car;

import com.netcracker.dto.modelDTO.ClientDto;
import com.netcracker.factory.ServicesFactory;
import com.netcracker.menu.Menu;
import com.netcracker.menu.edit.EditCar;
import com.netcracker.ClientServices;
import com.netcracker.session.UserSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Scanner;

@Slf4j
public class CarMenu implements Menu {

    private final ClientServices clientServices;

    public CarMenu(ServicesFactory servicesFactory) {
        this.clientServices = servicesFactory.getClientServices();
    }

    @Override
    public void preMessage(String nameMenu) {
        log.info("Enter 1.{}", nameMenu);
        log.info("Enter 2. Display a list of cars");
        log.info("Enter 5. Select a car to edit");
        log.info("Enter 6 to add car data");
    }

    @Override
    public void run(Scanner in, String parentsName) throws IOException {
        this.preMessage(parentsName);
        label:
        while (true) {
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
                            log.info("Enter 1.Close selection and editor menu");
                            log.info("Enter 6 to add car data");
                        }
                    });
                    break;
                }
                case "5": {
                    UserSession.getClientSession().ifPresent(x -> {
                        if (x.getCarClients().size() > 0) {
                            for (int z = 0; z < x.getCarClients().size(); z++) {
                                log.info("id:{} Car:{}", z + 1, x.getCarClients());
                            }
                            log.info("Enter car id");
                            int metadataCar = in.nextInt();
                            log.info("Edit selected car?");
                            log.info("Enter 1 to continue editing");
                            log.info("Enter 2 go out");
                            if (!in.next().equalsIgnoreCase("2")) {
                                try {
                                    EditCar editCar = new EditCar(x.getCarClients().get(metadataCar - 1));
                                    editCar.run(in, "Car menu");
                                    if (clientServices.updateClientCar(editCar.getCarClient())) {
                                        log.info("Data entered successfully");
                                    } else {
                                        log.info("An input error occurred while entering data. Retry data change");
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (IndexOutOfBoundsException e) {
                                    log.info("Invalid data entered please try again");
                                }
                            }
                            this.preMessage(parentsName);
                        } else {
                            log.info("No car data found.");
                            log.info("Enter 1.Close selection and editor menu");
                            log.info("Enter 6 to add car data");
                        }
                    });
                    break;
                }
                case "6": {
                    CreateCarClient creatCarClient = new CreateCarClient();
                    creatCarClient.run(in, "");
                    ClientDto client = UserSession.getCloneClientSession();
                    if (client.getCarClients() != null) {
                        client.getCarClients().add(creatCarClient.getCarClient().get());
                    }
                    if (clientServices.updateClient(client)) {
                        log.info("Data added successfully");
                    } else {
                        System.out.println("An error occurred while entering data, please try again");
                    }
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
