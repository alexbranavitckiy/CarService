package com.netcracker.menu.registration;

import com.netcracker.dto.model.ClientDto;
import com.netcracker.menu.Menu;
import com.netcracker.menu.car.CreateCarClient;
import com.netcracker.menu.order.NewOrder;
import com.netcracker.menu.validator.ValidatorInstrumentsImpl;
import com.netcracker.menu.validator.ValidatorInstruments;
import com.netcracker.ClientServices;
import com.netcracker.file.services.impl.client.ClientServicesImpl;
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
  private final ValidatorInstruments validator = new ValidatorInstrumentsImpl();
  private ClientDto clientLast;

  @Override
  public void preMessage(String parentsName) {
    log.info("Enter 1 {}", parentsName);
    log.info("Enter 2 create a client");
  }

  @Override
  public void run(Scanner in, String parentsName) throws IOException {
    CreateCarClient carClientMenu = new CreateCarClient();
    this.preMessage(parentsName);
    label:
    while (true) {
      switch (in.next()) {
        case "2": {
          carClientMenu.run(in, "Filling in car details");
          log.info("Filling in customer data");
          if (carClientMenu.getCarClient().isPresent()) {
            ClientDto client = ClientDto.builder()
              .id(UUID.randomUUID())
              .description(validator.validateDescription(in))
              .email(validator.validateMail(in))
              .name(validator.validateNameUser(in))
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
            NewOrder newOrder = new NewOrder(clientLast,
              carClientMenu.getCarClient().get().getId());
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

  public ClientDto getClient() {
    return clientLast;
  }
}
