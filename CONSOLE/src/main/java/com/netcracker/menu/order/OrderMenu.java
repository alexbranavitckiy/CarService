package com.netcracker.menu.order;

import com.netcracker.menu.Menu;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.util.Scanner;

@Slf4j
public class OrderMenu implements Menu {


    @Override
    public void preMessage(String parentsName) {
        log.info("You have entered the order menu");
        log.info("Enter 1 " + parentsName);
        log.info("Enter 2 to create order");
        log.info("Enter 3 to search and modify orders");
    }

    @Override
    public void run(Scanner in, String parentsName) throws IOException {
        this.preMessage(parentsName);
        label: while (true) {
            switch (in.next()) {
                case "3": {
                    log.info("List of orders");
                    break;
                }
                case "2": {
                    new NewOrder().run(in, "Order menu");
                    this.preMessage(parentsName);
                    break;
                }
                case "1": {
                    break label;
                }
                default: {
                    log.info("default");
                    break;
                }
            }
        }

    }
}
