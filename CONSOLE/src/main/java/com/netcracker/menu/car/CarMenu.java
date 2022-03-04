package com.netcracker.menu.car;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.marka.CarClient;
import com.netcracker.menu.Menu;
import com.netcracker.menu.login.registration.NewCarClient;
import com.netcracker.servisec.ClientServices;
import com.netcracker.servisec.FileService;
import com.netcracker.servisec.Impl.CRUDServicesImpl;
import com.netcracker.servisec.Impl.ClientServicesImpl;
import com.netcracker.servisec.UserSession;
import com.netcracker.user.Client;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;


public class CarMenu implements Menu {

    private boolean flag = true;
    private ClientServices clientServices = new ClientServicesImpl();

    @Override
    public void preMessage(String nameMenu) {
        System.out.println("Enter 1." + nameMenu);
        System.out.println("Enter 2. Display a list of cars");
        System.out.println("Enter 5. Select a car to edit");
    }

    @Override
    public void run(Scanner in, String parentsName) throws IOException {
        this.preMessage(parentsName);
        while (flag) {
            switch (in.next()) {
                case "1": {
                    this.flag = false;
                    break;
                }
                case "2": {
                    UserSession.getClientSession().ifPresent(x -> {
                        if (x.getCarClients() != null) {
                            x.getCarClients().forEach(CarClient::toStringClientWithoutMark);
                            System.out.println(x.getCarClients());
                            System.out.println("Enter 5 to go to a specific car");
                            System.out.println("Enter 1.Close selection and editor menu");
                            System.out.println("Enter 2. Display a list of cars");
                        } else {
                            System.out.println("No car data found");
                            System.out.println("Enter 6 to add car data");
                        }
                    });
                    break;
                }
                case "5": {
                    UserSession.getClientSession().ifPresent(x -> {
                        if (x.getCarClients() != null) {
                            x.getCarClients().forEach(CarClient::toStringClientWithoutMark);
                            System.out.println(x.getCarClients());
                        } else {
                            System.out.println("No car data found");
                        }
                    });
                    System.out.println("Enter car number(metadata car)");
                    String metadataCar = in.next();
                    UserSession.getClientSession().ifPresent(x -> {
                        if (x.getCarClients() != null) {
                            x.getCarClients()
                                    .stream().filter(Objects::nonNull)
                                    .filter(z -> z.getMetadataCar().equalsIgnoreCase(metadataCar)
                                    ).forEach(System.out::println);
                        } else {
                            System.out.println("Vehicle number not found");
                            System.out.println("Enter 2. Display a list of cars");
                            System.out.println("Enter 6 to add car data");
                        }
                    });
                    this.preMessage(parentsName);
                    System.out.println("Edit selected car?");
                    System.out.println("Enter 1 to edit");
                    System.out.println("Enter 2 to exit the editor");
                    if (in.next().equalsIgnoreCase("2")) {
                        this.preMessage(parentsName);
                        break;
                    } else {
                        // add!!!
                    }
                    break;

                }
                case "6": {
                    NewCarClient carClient = new NewCarClient();
                    carClient.run(in, "");
                    if (UserSession.getClientSession().isPresent() && UserSession.getClientSession().get().getCarClients() != null) {
                        UserSession.getClientSession().get().getCarClients().add(carClient.getCarClient().get());
                    } else {
                        UserSession.getClientSession().get().setCarClients(List.of(carClient.getCarClient().get()));
                    }
                    if (clientServices.updateClient(UserSession.getClientSession().get())) {
                        System.out.println("Data added successfully");
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
