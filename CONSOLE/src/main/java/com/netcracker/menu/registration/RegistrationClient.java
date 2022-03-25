package com.netcracker.menu.registration;

import com.netcracker.dto.modelDTO.ClientDto;
import com.netcracker.factory.ServicesFactory;
import com.netcracker.menu.Menu;
import com.netcracker.menu.car.CreateCarClient;
import com.netcracker.menu.validator.ValidatorInstrumentsImpl;
import com.netcracker.menu.validator.ValidatorInstruments;
import com.netcracker.ClientServices;
import com.netcracker.user.RoleUser;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.*;

@Slf4j
public class RegistrationClient implements Menu {

  private final ClientServices clientServices;
  private final ValidatorInstruments validator = new ValidatorInstrumentsImpl();

  public RegistrationClient(ServicesFactory servicesFactory){
    this.clientServices=servicesFactory.getClientServices();

  }

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
          CreateCarClient carClient = new CreateCarClient();
          carClient.run(in, "Enter car information");
          log.info("Enter customer details");
          ClientDto client = ClientDto.builder()
            .id(UUID.randomUUID())
            .login(validator.validateLogin(in))
            .password(validator.validatePassword(in))
            .name(validator.validateNameUser(in))
            .email(validator.validateMail(in))
            .description(validator.validateDescription(in))
            .phone(validator.validatePhone(in))
            .roleUser(RoleUser.REGISTERED.getId())
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
