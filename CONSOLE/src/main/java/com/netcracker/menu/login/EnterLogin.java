package com.netcracker.menu.login;

import com.netcracker.menu.Menu;
import com.netcracker.menu.userMenu.ClientMenu;
import com.netcracker.menu.userMenu.MasterMenu;
import com.netcracker.menu.userMenu.MasterReceiverMenu;
import com.netcracker.user.Role;

import java.io.IOException;
import java.util.Scanner;

public class EnterLogin implements Menu {

    private boolean flag = true;
    private String role;

     EnterLogin(String role){
         this.role=role;
     }


    @Override
    public void preMessage(String parentsName) {
        System.out.println("EnterLogin");
    }

    @Override
    public void run(Scanner in, String parentsName) throws IOException {
        this.preMessage(parentsName);
        while (flag) {
            switch (role) {
                case "MASTER": {
                    new MasterMenu().run(in, "");
                    flag = false;
                    break;
                }
                case "RECEPTIONIST": {
                    new MasterReceiverMenu().run(in, "");
                    System.out.println("RECEPTIONIST");
                    flag = false;
                    break;
                }
                case "REGISTERED": {
                    new ClientMenu().run(in, "");
                    System.out.println("Client");
                    flag = false;
                    break;
                }
                default: {
                    System.out.println("Login failed");
                    flag = false;
                    break;
                }
            }
        }
    }
}
