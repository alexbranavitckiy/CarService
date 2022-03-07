package com.netcracker.menu.order;

import com.netcracker.menu.Menu;

import java.io.IOException;
import java.util.Scanner;

public class OrderMenu implements Menu {

    private boolean flag = true;


    @Override
    public void preMessage(String parentsName) {
        System.out.println("You have entered the order menu");
        System.out.println("Enter 1 " + parentsName);
        System.out.println("Enter 2 to create order");
        System.out.println("Enter 3 to search and modify orders");
    }

    @Override
    public void run(Scanner in, String parentsName) throws IOException {
        this.preMessage(parentsName);
        while (flag) {
            switch (in.next()) {
                case "3": {
                    System.out.println("List of orders");
                    break;
                }
                case "2": {
                    new NewOrder().run(in, "Order menu");

                    this.preMessage(parentsName);
                    break;
                }
                case "1": {
                    this.flag = false;
                    break;
                }
                default: {
                    System.out.println("default");
                    break;
                }
            }
        }

    }
}
