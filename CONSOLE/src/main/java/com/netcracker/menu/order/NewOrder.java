package com.netcracker.menu.order;

import com.netcracker.menu.Menu;
import com.netcracker.servisec.Impl.LoginServicesImpl;
import com.netcracker.servisec.LoginService;

import java.io.IOException;
import java.util.Scanner;

public class NewOrder implements Menu {

    private boolean flag = true;

    private final LoginService loginServices = new LoginServicesImpl();

    @Override
    public void preMessage(String parentsName) {

    }

    @Override
    public void run(Scanner in, String parentsName) throws IOException {
        this.preMessage(parentsName);
        while (flag) {
            switch (in.next()) {
                case "3": {
                    // TODO add here code!!!
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