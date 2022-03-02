package com.netcracker.menu.login;

import com.netcracker.menu.Menu;

import java.util.Scanner;

public class EnterLogin implements Menu {

    private boolean flag = true;

    @Override
    public void preMessage(String parentsName) {
        System.out.println("EnterLogin");
    }

    @Override
    public void run(Scanner in, String parentsName) {
        this.preMessage(parentsName);
        while (flag) {
            switch (in.next()) {
                case "1": {
                    System.out.println("MASTER");
                    break;
                }
                case "2": {
                    System.out.println("RECEPTIONIST");
                    break;
                }
                case "3": {
                    System.out.println("ADMIN");
                    break;
                }
                default: {
                    flag = false;
                    break;
                }
            }
        }
    }
}
