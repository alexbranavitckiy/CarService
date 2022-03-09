package com.netcracker.menu.registration;

import com.netcracker.menu.Menu;
import com.netcracker.menu.car.NewCarClient;
import com.netcracker.menu.order.NewOrder;
import com.netcracker.servisec.ClientServices;
import com.netcracker.servisec.Impl.ClientServicesImpl;
import com.netcracker.user.Client;
import com.netcracker.user.RoleUser;

import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.UUID;

public class RegistrationClientByMaster implements Menu {

    private boolean flag = true;
    private final ClientServices clientServices = new ClientServicesImpl();
    private Client client;

    @Override
    public void preMessage(String parentsName) {
        System.out.println("Enter 1 " + parentsName);
        System.out.println("Enter 2 create a client");
    }

    @Override
    public void run(Scanner in, String parentsName) throws IOException {
        this.preMessage(parentsName);
        while (flag) {
            switch (in.next()) {
                case "2": {
                    Client client = new Client();
                    System.out.println("Filling in car details");
                    NewCarClient carClient = new NewCarClient();
                    carClient.run(in, "");
                    if (carClient.getCarClient().isPresent()) {
                        client.setCarClients(new HashSet<>());
                        client.getCarClients().add((carClient.getCarClient().get()));
                    } else {
                        System.out.println("Try again to enter information");
                        this.preMessage(parentsName);
                        break;
                    } // if the user is created by the master, the password and login is written as the car number
                    client.setLogin(carClient.getCarClient().get().getMetadataCar());
                    client.setPassword(carClient.getCarClient().get().getMetadataCar());
                    System.out.println("Enter phone");
                    client.setPhone(in.next());
                    client.setId(UUID.randomUUID());
                    client.setRoleuser(RoleUser.UNREGISTERED);
                    if (clientServices.addObjectInClientNotOnline(client)) {
                        System.out.println("User created successfully");
                        this.client = client;
                        this.flag = false;
                    } else {
                        System.out.println("Invalid data. Repeat registration");
                        this.preMessage(parentsName);
                        break;
                    }
                    System.out.println("Enter 3 to Create an order with these customers");
                    System.out.println("Enter 1 " + parentsName);
                    if (in.next().equalsIgnoreCase("3")) {
                        NewOrder newOrder = new NewOrder(client);
                        newOrder.run(in, "");
                    } else {
                        this.flag = false;
                        break;
                    }
                }
                case "1": {
                    this.flag = false;
                    break;
                }
                case "3": {

                    break;
                }
                default: {
                    this.preMessage(parentsName);
                    break;
                }
            }
        }
    }

    public Client getClient() {
        return client;
    }
}
