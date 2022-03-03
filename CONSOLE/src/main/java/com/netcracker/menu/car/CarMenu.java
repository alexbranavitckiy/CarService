package com.netcracker.menu.car;

import com.netcracker.menu.Menu;

import java.util.Scanner;


public class CarMenu implements Menu {

    private boolean flag = true;

    @Override
    public void preMessage(String nameMenu) {

    }

    @Override
    public void run(Scanner in, String parentsName) {
        System.out.println("press 1:" + parentsName);
        while (flag) {
            switch (in.next()) {
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
