package com.netcracker.menu.userMenu;

import com.netcracker.factory.ServicesFactory;
import com.netcracker.menu.Menu;
import com.netcracker.menu.car.ListCarClient;
import com.netcracker.menu.edit.EditMasterReceiver;
import com.netcracker.menu.order.ListOrders;
import com.netcracker.menu.order.NewOrder;
import com.netcracker.menu.order.client.ListClient;
import com.netcracker.menu.order.outfit.ListOutfit;
import com.netcracker.menu.registration.RegistrationClientByMaster;
import com.netcracker.menu.registration.RegistrationMaster;
import com.netcracker.file.services.impl.order.OrderServicesImpl;
import com.netcracker.OrderServices;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.util.Scanner;

@Slf4j
public class MasterReceiverMenu implements Menu {

  private final OrderServices orderServices ;
  private final ServicesFactory servicesFactory;

  public MasterReceiverMenu(ServicesFactory servicesFactory){
    this.orderServices=servicesFactory.getOrderServices();
    this.servicesFactory=servicesFactory;
  }

  @Override
  public void preMessage(String nameMenu) {
    log.info("Enter 1 {}", nameMenu);
    log.info("Enter 2 to show contact information");
    log.info("Enter 3 to create:\n-client\n-order.");
    log.info("Enter 4 to create:\n-Master\n-Master receiver");
    log.info("Enter 5 to edit personal information");
    log.info("Enter 6 to search and modify a client");
    log.info("Enter 7 Go to the list of outfits");
    log.info("Enter 8 Show a list of requests from customers({})",
        this.getOrderSize());
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
          log.info("Create an order with registered users?\n 1-yeas.\n 2-no.");
          if (in.next().equalsIgnoreCase("2")) {
            new RegistrationClientByMaster(servicesFactory).run(in, "Main menu");
          } else {
            ListClient listClient = new ListClient(servicesFactory);
            listClient.run(in, "Main menu");
            if (listClient.getClient().isPresent()) {
              ListCarClient listCarClient = new ListCarClient(listClient.getClient().get().getId());
              listCarClient.run(in, "Main menu");
              if (listClient.getClient().isPresent()) {
                NewOrder newOrder = new NewOrder(listClient.getClient().get(),
                    listCarClient.getClient().get().getId(),servicesFactory);
                newOrder.run(in, "Main menu");
              }
            }
          }
          this.preMessage(parentsName);
          break;
        }
        case "4": {
          new RegistrationMaster(servicesFactory).run(in, "Main menu");
          this.preMessage(parentsName);
          break;
        }
        case "5": {
          new EditMasterReceiver(servicesFactory).run(in, "Main menu");
          this.preMessage(parentsName);
          break;
        }
        case "6": {
          new ListClient(servicesFactory).run(in, "Main menu");
          this.preMessage(parentsName);
          break;
        }
        case "7": {
          ListOutfit listOutfits = new ListOutfit(servicesFactory);
          listOutfits.run(in, "");
          this.preMessage(parentsName);
          break;
        }
        case "8": {
          ListOrders listOrders = new ListOrders(servicesFactory);
          listOrders.run(in, "");
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

  private int getOrderSize() {
    return orderServices.getOrderWithRequestState().size();
  }


}
