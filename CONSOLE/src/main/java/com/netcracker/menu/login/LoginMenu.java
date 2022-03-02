package com.netcracker.menu.login;

import com.netcracker.menu.Menu;
import com.netcracker.menu.login.registration.RegistrationClient;
import com.netcracker.servisec.LoginServices;
import com.netcracker.user.Role;

import java.io.IOException;
import java.util.Scanner;


public class LoginMenu implements Menu {

    private final String FILE = "src/main/resources/client.json";
    private boolean flag = true;
    private final String NAME_MENU = "Login menu";

    private final LoginServices loginServices = new LoginServices();

    @Override
    public void preMessage(String parentsName) {
        System.out.println("Press 1:" + parentsName);
        System.out.println("Enter 2 to login :");
        System.out.println("Enter 3 to registration :");
    }

    @Override
    public void run(Scanner in, String parentsName) throws IOException {
        this.preMessage(parentsName);
        while (flag) {
            switch (in.next()) {
                case "2": {
                    System.out.println("Enter login.");
                    String login = in.next();
                    System.out.println("Enter password");
                    String password = in.next();
                    try {
                     loginServices.searchByUserLoginAndPassword(login, password);
                    } catch (Exception e) {
                        System.out.println("User is not found");
                    }
                    new EnterLogin(Role.RECEPTIONIST.toString()).run(in, NAME_MENU);
                    this.preMessage(parentsName);
                    break;
                }
                case "3": {
                    new RegistrationClient().run(in, NAME_MENU);
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
