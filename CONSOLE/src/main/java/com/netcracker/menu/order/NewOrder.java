package com.netcracker.menu.order;

import com.netcracker.menu.Menu;
import com.netcracker.order.Order;
import com.netcracker.servisec.Impl.LoginServicesImpl;
import com.netcracker.servisec.LoginService;
import com.netcracker.user.Client;

import java.io.IOException;
import java.util.Scanner;
import java.util.UUID;

public class NewOrder implements Menu {

    private boolean flag = true;

    public NewOrder() {
    }

    public NewOrder(Client client) {
        System.out.println(client);
    }

    @Override
    public void preMessage(String parentsName) {
        System.out.println("Enter 1 to leave");
        System.out.println("Enter 2 to create order ");
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
                    Order order = new Order();
                    order.setId(UUID.randomUUID());
                    System.out.println("");
                    order.setClient(null);
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