package com.netcracker.menu.registration;

import com.netcracker.menu.Menu;
import com.netcracker.menu.car.NewCarClient;
import com.netcracker.menu.validator.ValidatorInstrumentsImpl;
import com.netcracker.menu.validator.ValidatorInstruments;
import com.netcracker.servisec.ClientServices;
import com.netcracker.servisec.Impl.client.ClientServicesImpl;
import com.netcracker.user.Client;
import com.netcracker.user.RoleUser;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.*;

@Slf4j
public class RegistrationClient implements Menu {

  private final ClientServices clientServices = new ClientServicesImpl();
  private final ValidatorInstruments validator = new ValidatorInstrumentsImpl();


  @Override
  public void preMessage(String parentsName) {
    log.info("Enter 1 {}", parentsName);
    log.info("Enter 2 to continue registration");
  }

  @Override
  public void run(Scanner in, String parentsName) throws IOException {
    this.preMessage(parentsName);
    label:
    while (true) {
      switch (in.next()) {
        case "2": {
          NewCarClient carClient = new NewCarClient();
          carClient.run(in, "Enter car information");
          log.info("Enter customer details");
          Client client = Client.builder()
              .id(UUID.randomUUID())
              .login(validator.getLogin(in))
              .password(validator.getPassword(in))
              .name(validator.getNameUser(in))
              .email(validator.getMail(in))
              .description(validator.getDescription(in))
              .phone(validator.getPhone(in))
              .roleuser(RoleUser.REGISTERED)
              .build();
          if (carClient.getCarClient().isPresent()) {
            client.setCarClients(new ArrayList<>());
            client.getCarClients().add((carClient.getCarClient().get()));
          } else {
            log.info("Try again to enter information");
            this.preMessage(parentsName);
            break;
          }
          if (clientServices.addObjectInClient(client)) {
            log.info("User created successfully");
            break label;
          } else {
            log.info("Invalid data. Repeat registration");
            this.preMessage(parentsName);
          }
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
