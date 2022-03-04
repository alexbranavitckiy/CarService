package com.netcracker.menu.login.registration;

import com.netcracker.menu.Menu;
import com.netcracker.servisec.ClientServices;
import com.netcracker.servisec.Impl.ClientServicesImpl;
import com.netcracker.user.Client;
import com.netcracker.user.RoleUser;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class RegistrationClient implements Menu {

    private boolean flag = true;
    private final ClientServices clientServices = new ClientServicesImpl();

    @Override
    public void preMessage(String parentsName) {
        System.out.println("Enter 1 " + parentsName);
        System.out.println("Enter 2 to continue registration");
    }

    @Override
    public void run(Scanner in, String parentsName) throws IOException {
        this.preMessage(parentsName);
        while (flag) {
            switch (in.next()) {
                case "2": {
                    Client client = new Client();
                    System.out.println("Enter login");
                    client.setLogin(in.next());
                    System.out.println("Enter password");
                    client.setPassword(in.next());
                    System.out.println("Enter phone");
                    client.setPhone(in.next());
                    System.out.println("Filling in car details");
                    NewCarClient carClient = new NewCarClient();
                    carClient.run(in, "");
                    if (carClient.getCarClient().isPresent()) {
                        client.setCarClients(List.of(carClient.getCarClient().get()));
                    } else {
                        System.out.println("Try again to enter information");
                        this.preMessage(parentsName);
                        break;
                    }
                    client.setRoleuser(RoleUser.REGISTERED);
                    if (clientServices.addObjectInClient(client)) {
                        System.out.println("User created successfully");
                        this.flag = false;
                    } else {
                        System.out.println("Invalid data. Repeat registration");
                        this.preMessage(parentsName);
                    }
                    break;
                }
                case "1": {
                    this.flag = false;
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
