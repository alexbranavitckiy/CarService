package com.netcracker.menu.login;

import com.netcracker.menu.Menu;
import com.netcracker.menu.registration.RegistrationClient;
import com.netcracker.servisec.FileService;
import com.netcracker.servisec.Impl.LoginServicesImpl;
import com.netcracker.servisec.LoginService;
import com.netcracker.servisec.UserSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Scanner;

@Slf4j
public class LoginMenu implements Menu {

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
    label:
    while (true) {
      switch (in.next()) {
        case "2": {
          UserSession.closeSession();
          log.info("Enter login.");
          String login = in.next();
          log.info("Enter password");
          String password = in.next();
          if (
            loginServices.searchByUserLoginAndPassword(login, password)) {
            new EnterLogin().run(in, NAME_MENU);
          } else {
            System.out.println(FileService.NOT_FOUND);
          }
          this.preMessage(parentsName);
          break;
        }
        case "3": {
          new RegistrationClient().run(in, NAME_MENU);
          this.preMessage(parentsName);
          break;
        }
        case "1": {
          break label;
        }
        default: {
          this.preMessage(parentsName);
          break;
        }
      }
    }
  }

}
