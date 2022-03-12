package com.netcracker.menu.userMenu;

import com.netcracker.menu.Menu;
import com.netcracker.menu.edit.EditMasterReceiver;
import com.netcracker.menu.order.client.ListClient;
import com.netcracker.menu.order.OrderMenu;
import com.netcracker.menu.registration.RegistrationClientByMaster;
import com.netcracker.menu.registration.RegistrationMaster;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Scanner;

@Slf4j
public class MasterReceiverMenu implements Menu {


  @Override
  public void preMessage(String nameMenu) {
    log.info("Enter 1 {}", nameMenu);
    log.info("Enter 2 to show contact information");//++
    log.info("Enter 3 to go to the order menu. Search/Modify/Appoint");
    log.info("Enter 4 to create a client.");//++
    log.info("Enter 5 to create/edit/Master/Master Receiver");
    log.info("Enter 6 to edit personal information");//++
    log.info("Enter 7 to search and modify a client");//++
  }

  @Override
  public void run(Scanner in, String parentsName) throws IOException {
    log.info("You are logged in as a master receiver");
    this.preMessage(parentsName);
    label:
    while (true) {
      switch (in.next()) {
        case "1": {
          break label;
        }
        case "2": {
          new InfoMenu().run(in, "");
          this.preMessage(parentsName);
          break;
        }
        case "3": {
          new OrderMenu().run(in, "Main menu");
          this.preMessage(parentsName);
          break;
        }
        case "4": {
          new RegistrationClientByMaster().run(in, "Main menu");
          this.preMessage(parentsName);
          break;
        }
        case "5": {
          new RegistrationMaster().run(in, "Main menu");
          this.preMessage(parentsName);
          break;
        }
        case "6": {
          new EditMasterReceiver().run(in, "Main menu");
          this.preMessage(parentsName);
          break;
        }
        case "7": {
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
