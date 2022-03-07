package com.netcracker.menu.order;

import com.netcracker.menu.Menu;

import java.io.IOException;
import java.util.Scanner;

public class OrderMenu implements Menu {

    private boolean flag = true;


    @Override
    public void preMessage(String parentsName) {
        System.out.println("You have entered the order menu");
        System.out.println("Press 1:" + parentsName);
        System.out.println("Press 3, to get a list of orders");
        System.out.println("Press 2, to create order");

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
                    System.out.println("Create order");
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
