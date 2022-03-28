package com.netcracker.menu.userMenu;

import com.netcracker.factory.ServicesFactory;
import com.netcracker.menu.Menu;
import com.netcracker.menu.car.ListCarClient;
import com.netcracker.menu.edit.EditClient;
import com.netcracker.menu.edit.EditMasterReceiver;
import com.netcracker.menu.order.ListOrders;
import com.netcracker.menu.order.NewOrder;
import com.netcracker.menu.order.client.ListClient;
import com.netcracker.menu.order.outfit.ListOutfit;
import com.netcracker.menu.registration.RegistrationClientByMaster;
import com.netcracker.menu.registration.RegistrationMaster;
import com.netcracker.OrderServices;
import com.netcracker.menu.validator.ValidatorInstruments;
import com.netcracker.menu.validator.ValidatorInstrumentsImpl;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Scanner;

@Slf4j
public class MasterReceiverMenu implements Menu {

 private final OrderServices orderServices;
 private final ServicesFactory servicesFactory;
 private final ValidatorInstruments validator = new ValidatorInstrumentsImpl();

 public MasterReceiverMenu(ServicesFactory servicesFactory) {
  this.orderServices = servicesFactory.getFactory().getOrderServices();
  this.servicesFactory = servicesFactory;
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
       ListCarClient listCarClient = new ListCarClient(servicesFactory, listClient.getClient().get().getId());
       listCarClient.run(in, "Main menu");
       if (listClient.getClient().isPresent()) {
        if (listCarClient.getClient().isPresent()) {
         NewOrder newOrder = new NewOrder(listClient.getClient().get(),
          listCarClient.getClient().get().getId(), servicesFactory);
         newOrder.run(in, "Main menu");
        } else {
         System.out.println("This user has no cars");
        }
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
     log.info("Select and edit a client?\n1-yeas\n2-no");
     if (in.next().equalsIgnoreCase("1")) {
      ListClient listClient = new ListClient(servicesFactory);
      listClient.run(in, "Main menu");
      EditClient editClient = new EditClient(listClient.getClient().get());
      editClient.run(in, "Main menu");
      validator.successfullyMessages(servicesFactory.getFactory().getClientServices().updateClientNotSession(editClient.getClient()));
     }
     this.preMessage(parentsName);
     break;
    }
    case "7": {
     ListOutfit listOutfits = new ListOutfit(servicesFactory);
     listOutfits.run(in, "Main menu");
     this.preMessage(parentsName);
     break;
    }
    case "8": {
     ListOrders listOrders = new ListOrders(servicesFactory);
     listOrders.run(in, "Main menu");
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
  if (!orderServices.getOrderWithRequestState().isEmpty())
   return orderServices.getOrderWithRequestState().size();
  else return 0;
 }


}
