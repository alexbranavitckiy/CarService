package com.netcracker.menu.registration;

import com.netcracker.factory.ServicesFactory;
import com.netcracker.menu.Menu;
import com.netcracker.menu.car.CreateCarClient;
import com.netcracker.menu.order.NewOrder;
import com.netcracker.menu.validator.ValidatorInstrumentsImpl;
import com.netcracker.menu.validator.ValidatorInstruments;
import com.netcracker.ClientServices;
import com.netcracker.user.Client;
import com.netcracker.user.RoleUser;

import java.util.ArrayList;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Scanner;
import java.util.UUID;

@Slf4j
public class RegistrationClientByMaster implements Menu {

 private final ClientServices clientServices;
 private final ValidatorInstruments validator = new ValidatorInstrumentsImpl();
 private Client clientLast;
 private final ServicesFactory servicesFactory;

 public RegistrationClientByMaster(ServicesFactory servicesFactory) {
  this.servicesFactory = servicesFactory;
  this.clientServices = servicesFactory.getClientServices();
 }

 @Override
 public void preMessage(String parentsName) {
  log.info("Enter 1 {}", parentsName);
  log.info("Enter 2 create a client");
 }

 @Override
 public void run(Scanner in, String parentsName) throws IOException {
  UUID uuidCNewClient = UUID.randomUUID();
  CreateCarClient carClientMenu = new CreateCarClient(uuidCNewClient);
  this.preMessage(parentsName);
  label:
  while (true) {
   switch (in.next()) {
    case "2": {
     carClientMenu.run(in, "Filling in car details");
     log.info("Filling in customer data");
     if (carClientMenu.getCarClient().isPresent()) {
      Client client = Client.builder()
       .id(uuidCNewClient)
       .description(validator.validateDescription(in))
       .email(validator.validateMail(in))
       .name(validator.validateNameUser(in))
       .roleUser(RoleUser.UNREGISTERED.getId())
       .carClients(new ArrayList<>())
       .login(carClientMenu.getCarClient().get().getMetadataCar())
       .password(carClientMenu.getCarClient().get().getMetadataCar())
       .build();
      if (clientServices.addObjectInClientNotOnline(client)) {
       log.info("User created successfully");
       this.clientLast = client;
       log.info("Enter 3 to Create an order with these customers");
      } else {
       log.info("Invalid data. Repeat registration");
      }
     } else {
      log.info("Try again to enter information");
     }
     this.preMessage(parentsName);
     break;
    }
    case "1": {
     break label;
    }
    case "3": {
     if (clientLast != null) {
      NewOrder newOrder = new NewOrder(clientLast,
       carClientMenu.getCarClient().get().getId(), servicesFactory);
      newOrder.run(in, "Client creation menu");
     }
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

 public Client getClient() {
  return clientLast;
 }
}
