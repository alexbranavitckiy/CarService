package com.netcracker.menu.userMenu;

import com.netcracker.menu.Menu;
import com.netcracker.menu.edit.EditMasterReceiver;
import com.netcracker.menu.order.client.ListClient;
import com.netcracker.menu.order.OrderMenu;
import com.netcracker.menu.registration.RegistrationClientByMaster;
import com.netcracker.servisec.CRUDServices;
import com.netcracker.servisec.Impl.masterReceiver.CRUDServicesMasterReceiverImpl;

import java.io.IOException;
import java.util.Scanner;

public class MasterReceiverMenu implements Menu {

    private boolean flag = true;

    @Override
    public void preMessage(String nameMenu) {
        System.out.println("Enter 1 " + nameMenu);
        System.out.println("Enter 2 to show contact information");
        System.out.println("Enter 3 to go to the order menu. Search/Modify/Appoint");
        System.out.println("Enter 4 to create a client.");//+
        System.out.println("Enter 5 to create/edit master.");
        System.out.println("Enter 6 to edit personal information");//+
        System.out.println("Enter 7 to add receiver master");
        System.out.println("Enter 8 to search and modify a client");
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
                    new InfoMenu().run(in, "");
                    this.preMessage(parentsName);
                    break;
                }
                case "3": {
                    new OrderMenu().run(in, "Main menu");
                    this.preMessage(parentsName);
                    break;
                }
                case "4": {
                    RegistrationClientByMaster registrationClientByMaster =
                            new RegistrationClientByMaster();
                    registrationClientByMaster.run(in, "Main menu");
                    System.out.println(registrationClientByMaster.getClient());
                    this.preMessage(parentsName);
                    break;
                }

                case "6": {
                    new EditMasterReceiver().run(in, "Main menu");
                    this.preMessage(parentsName);
                    break;
                }
                case "8": {
                    new ListClient().run(in, "Main menu");
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
