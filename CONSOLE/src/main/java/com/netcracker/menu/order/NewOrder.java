package com.netcracker.menu.order;

import com.netcracker.menu.Menu;
import com.netcracker.order.Order;
import com.netcracker.servisec.UserSession;
import com.netcracker.user.Client;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Scanner;
import java.util.UUID;

@Slf4j
public class NewOrder implements Menu {

    public NewOrder() {
    }

    public NewOrder(Client client) {
        System.out.println(client);
    }

    @Override
    public void preMessage(String parentsName) {
        log.info("Enter 1 to leave");
        log.info("Enter 2 to create order ");
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
                    Order order = new Order();
                    order.setId(UUID.randomUUID());
                    System.out.println("Descriptions:");

                    order.setDescriptions(in.next());
                    //  order.se
                    // TODO add here code!!!

                    // add save and clone;
                    UserSession.getCloneMasterReceiverSession().getOrders().add(order);
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