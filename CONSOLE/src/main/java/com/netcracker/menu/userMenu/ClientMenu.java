package com.netcracker.menu.userMenu;

import com.netcracker.menu.Menu;
import com.netcracker.menu.car.CarMenu;
import com.netcracker.menu.edit.EditClient;
import com.netcracker.servisec.ClientServices;
import com.netcracker.servisec.Impl.ClientServicesImpl;

import java.io.IOException;
import java.util.Scanner;

public class ClientMenu implements Menu {

    private boolean flag = true;
    private final ClientServices clientServices = new ClientServicesImpl();

    @Override
    public void preMessage(String nameMenu) {
        System.out.println("Enter 1 " + nameMenu);
        System.out.println("Enter 2 for contact information");
        System.out.println("Enter 3 to open recording menu/Sign up for repairs./View machine status."); // TODO add here code!!!
        System.out.println("Enter 4 to get information about the car/View the list of cars./Edit information on car");
        System.out.println("Enter 5 to edit information about the client");
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
                    new InfoMenu().run(in, "Main menu");
                    this.preMessage(parentsName);
                    break;
                }
                case "4": {
                    new CarMenu().run(in, "Main menu");
                    this.preMessage(parentsName);
                    break;
                }
                case "5": {
                    EditClient editClient = new EditClient();
                    editClient.run(in, "Main menu");
                    if (clientServices.updateClient(editClient.getClient()))
                        System.out.println("Data entered successfully");
                    else
                        System.out.println("An input error occurred while entering data. Retry data change");
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
