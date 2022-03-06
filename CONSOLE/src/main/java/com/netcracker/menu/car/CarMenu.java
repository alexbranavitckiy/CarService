package com.netcracker.menu.car;

import com.netcracker.marka.CarClient;
import com.netcracker.menu.Menu;
import com.netcracker.menu.login.registration.EditCar;
import com.netcracker.menu.login.registration.NewCarClient;
import com.netcracker.servisec.ClientServices;
import com.netcracker.servisec.Impl.ClientServicesImpl;
import com.netcracker.servisec.UserSession;
import com.netcracker.user.Client;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;


public class CarMenu implements Menu {

    private boolean flag = true;
    private final ClientServices clientServices = new ClientServicesImpl();

    @Override
    public void preMessage(String nameMenu) {
        System.out.println("Enter 1." + nameMenu);
        System.out.println("Enter 2. Display a list of cars");
        System.out.println("Enter 5. Select a car to edit");
        System.out.println("Enter 6 to add car data");
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
                        if (x.getCarClients().size() > 0) {
                            // x.getCarClients().forEach(CarClient::toStringClientWithoutMark);
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
                    Set<CarClient> carClientSet = UserSession.getClientSession().get().getCarClients();
                    UserSession.getClientSession().ifPresent(x -> {
                        if (x.getCarClients().size()>0) {
                            System.out.println(carClientSet);
                            System.out.println("Enter car number(metadata car)");
                            String metadataCar = in.next();
                            x.getCarClients()
                                    .stream().filter(Objects::nonNull)
                                    .filter(z -> z.getMetadataCar().equalsIgnoreCase(metadataCar)
                                    ).forEach(System.out::println);
                            System.out.println("Edit selected car?");
                            System.out.println("Enter 1 to continue editing");
                            System.out.println("Enter 2 to edit");
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
                                    System.out.println("Data entered successfully");
                                else
                                    System.out.println("An input error occurred while entering data. Retry data change");
                                this.preMessage(parentsName);
                            }
                        } else {
                            System.out.println("No car data found.");
                            System.out.println("Enter 1.Close selection and editor menu");
                            System.out.println("Enter 6 to add car data");
                        }
                    });
                    break;
                }
                case "6": {
                    NewCarClient newCarClient = new NewCarClient();
                    newCarClient.run(in, "");
                    Client client = UserSession.getClientSession().get();
                    if (client.getCarClients() != null) {
                        client.getCarClients().add(newCarClient.getCarClient().get());
                    }
                    if (clientServices.updateClient(client)) {
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
