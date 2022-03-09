package com.netcracker.menu.login;

import com.netcracker.menu.Menu;
import com.netcracker.menu.registration.RegistrationClient;
import com.netcracker.servisec.Impl.LoginServicesImpl;
import com.netcracker.servisec.LoginService;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Scanner;

@Slf4j
public class LoginMenu implements Menu {

    private boolean flag = true;
    private final String NAME_MENU = "Login menu";

    private final LoginService loginServices = new LoginServicesImpl();

    @Override
    public void preMessage(String parentsName) {
        log.info("Enter 1:{}", parentsName);
        log.info("Enter 2 to login.");
        log.info("Enter 3 to registration.");
    }

    @Override
    public void run(Scanner in, String parentsName) throws IOException {
        this.preMessage(parentsName);
        while (flag) {
            switch (in.next()) {
                case "2": {
                    log.info("Enter login.");
                    String login = in.next();
                    log.info("Enter password");
                    String password = in.next();
                    if (loginServices.searchByUserLoginAndPassword(login, password))
                        new EnterLogin().run(in, NAME_MENU);
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
