package com.netcracker.menu.car;

import com.netcracker.menu.Menu;

import java.util.Scanner;


public class CarMenu implements Menu {

    private final String FILE = "src/main/resources/order.json";
    private boolean flag = true;

    @Override
    public void preMessage(String nameMenu) {

    }

    @Override
    public void run(Scanner in, String parentsName) {
        System.out.println("press 1:" + parentsName);
        while (flag) {
            switch (in.next()) {
                case "2": {
                    this.update();
                    break;
                }
                case "3": {
                    this.createOrder();
                    System.out.println(2);
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

    public void createOrder() {
        System.out.println("createOrder");
    }

    public void update() {
        System.out.println("update");
    }

    public void output() {
        System.out.println("output");
    }


    public void updateStatus() {
        System.out.println("updateStatus");
    }


}
