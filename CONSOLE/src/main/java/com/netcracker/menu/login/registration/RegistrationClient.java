package com.netcracker.menu.login.registration;

import com.netcracker.menu.Menu;
import com.netcracker.menu.login.EnterLogin;

import java.util.Scanner;

public class RegistrationClient implements Menu {

    private final String FILE = "src/main/resources/client.json";
    private boolean flag = true;


    @Override
    public void preMessage(String parentsName) {
        System.out.println("Press 1:" + parentsName);
        System.out.println("Press 2, continue registration");
    }

    @Override
    public void run(Scanner in, String parentsName) {
        this.preMessage(parentsName);
        while (flag) {
            switch (in.next()) {
                case "2": {


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
