package com.netcracker.menu.userMenu;

import com.netcracker.menu.Menu;
import com.netcracker.menu.edit.EditMasterReceiver;
import com.netcracker.menu.order.OrderMenu;
import com.netcracker.menu.order.client.ListClient;
import com.netcracker.menu.registration.RegistrationClientByMaster;
import com.netcracker.menu.registration.RegistrationMaster;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Scanner;

@Slf4j
public class MasterMenu implements Menu {

  @Override
  public void preMessage(String nameMenu) {
    log.info("Enter 1 {}", nameMenu);
    log.info("Enter 2 list of current outfits");

  }

  @Override
  public void run(Scanner in, String parentsName) throws IOException {
    log.info("You are logged in as a master");
    this.preMessage(parentsName);
    label:
    while (true) {
      switch (in.next()) {
        case "1": {
          break label;
        }

        case "2": {
          new ListClient().run(in, "Main menu");
          this.preMessage(parentsName);
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


