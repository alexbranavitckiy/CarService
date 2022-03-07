package com.netcracker.menu.order.client;

import com.netcracker.errors.EmptySearchException;
import com.netcracker.menu.Menu;
import com.netcracker.servisec.ClientServices;
import com.netcracker.servisec.Impl.ClientServicesImpl;
import com.netcracker.servisec.Impl.LoginServicesImpl;
import com.netcracker.servisec.LoginService;

import java.io.IOException;
import java.util.Scanner;

public class ListClient implements Menu {

    private boolean flag = true;

    private final LoginService loginServices = new LoginServicesImpl();
    private final ClientServices searchServices = new ClientServicesImpl();

    @Override
    public void preMessage(String parentsName) {
        System.out.println("Enter 1:" + parentsName);
        System.out.println("Enter 2 Display a list of clients.");
        System.out.println("Enter 3 Search for a client by keyword.");
    }

    @Override
    public void run(Scanner in, String parentsName) throws IOException {


        this.preMessage(parentsName);
        while (flag) {
            switch (in.next()) {
                case "2": {
                    try {
                        searchServices.getAllClient().forEach(System.out::println);
                    } catch (EmptySearchException e) {
                        System.out.println(e);
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