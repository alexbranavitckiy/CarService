package com.netcracker.menu.order.client;

import com.netcracker.errors.EmptySearchException;
import com.netcracker.menu.Menu;
import com.netcracker.menu.edit.EditClient;
import com.netcracker.servisec.ClientServices;
import com.netcracker.servisec.Impl.ClientServicesImpl;
import com.netcracker.servisec.Impl.LoginServicesImpl;
import com.netcracker.servisec.LoginService;
import com.netcracker.user.Client;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


@Slf4j
public class ListClient implements Menu {

    private boolean flag = true;

    private final LoginService loginServices = new LoginServicesImpl();
    private final ClientServices searchServices = new ClientServicesImpl();

    @Override
    public void preMessage(String parentsName) {
        System.out.println("Enter 1:" + parentsName);
        System.out.println("Enter 2 Display a list of clients.");//+
        System.out.println("Enter 3 Search for a client by keyword.");
    }

    @Override
    public void run(Scanner in, String parentsName) throws IOException {
        this.preMessage(parentsName);
        while (flag) {
            switch (in.next()) {
                case "2": {
                    try {
                        List<Client> clientList = searchServices.getAllClient();
                        if (clientList.size() > 0) {
                            for (int x = 1; x < clientList.size() + 1; x++)
                                System.out.println("Id:" + x + " " + clientList.get(x - 1));
                            System.out.println("Edit client? 1-yeas. 2-no");
                            if (in.next().equalsIgnoreCase("1")) {
                                System.out.println("Enter client id");
                                EditClient editClient = new EditClient(clientList.get(in.nextInt() - 1));
                                editClient.run(in, "Edit Client");
                                if (searchServices.updateClient(editClient.getClient())) {
                                    System.out.println("Data entered successfully");
                                } else
                                    System.out.println("An input error occurred while entering data. Retry data change");
                            }
                        }
                    } catch (EmptySearchException e) {
                        System.out.println("The search has not given any results");
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid data entered please try again");
                    }
                    this.preMessage(parentsName);
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