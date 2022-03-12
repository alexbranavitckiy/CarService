package com.netcracker.menu.registration;

import com.netcracker.menu.Menu;
import com.netcracker.menu.car.NewCarClient;
import com.netcracker.menu.order.NewOrder;
import com.netcracker.menu.validator.ValidatorInstrumentsImpl;
import com.netcracker.menu.validator.ValidatorInstruments;
import com.netcracker.servisec.ClientServices;
import com.netcracker.servisec.Impl.client.ClientServicesImpl;
import com.netcracker.user.Client;
import com.netcracker.user.RoleUser;
import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.UUID;

@Slf4j
public class RegistrationClientByMaster implements Menu {

  private final ClientServices clientServices = new ClientServicesImpl();

  private Client clientLast;
  private final ValidatorInstruments validator = new ValidatorInstrumentsImpl();

  @Override
  public void preMessage(String parentsName) {
    log.info("Enter 1 {}", parentsName);
    log.info("Enter 2 create a client");
  }

  @Override
  public void run(Scanner in, String parentsName) throws IOException {
    this.preMessage(parentsName);
    label:
    while (true) {
      switch (in.next()) {
        case "2": {
          log.info("Filling in car details");
          NewCarClient carClientMenu = new NewCarClient();
          carClientMenu.run(in, "");
          if (carClientMenu.getCarClient().isPresent()) {
            Client client = Client.builder()
                .id(UUID.randomUUID())
                .description(validator.getDescription(in))
                .email(validator.getMail(in))
                .name(validator.getNameUser(in))
                .roleuser(RoleUser.UNREGISTERED)
                .orders(new HashSet<>())
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
            NewOrder newOrder = new NewOrder(clientLast);
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
