package com.netcracker.menu.registration;

import com.netcracker.menu.Menu;
import com.netcracker.menu.validator.ValidatorInstrumentsImpl;
import com.netcracker.file.services.impl.master.MasterServicesImpl;
import com.netcracker.file.services.impl.masterReceiver.MasterReceiverServicesImpl;
import com.netcracker.MasterReceiverServices;
import com.netcracker.MasterServices;
import com.netcracker.user.*;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

@Slf4j
public class RegistrationMaster implements Menu {

  private final MasterServices masterServices = new MasterServicesImpl();
  private final MasterReceiverServices masterReceiverServices = new MasterReceiverServicesImpl();
  private final ValidatorInstrumentsImpl validator = new ValidatorInstrumentsImpl();

  @Override
  public void preMessage(String parentsName) {
    log.info("Enter 1 {}", parentsName);
    log.info("Enter 2 to continue create");
  }

  @Override
  public void run(Scanner in, String parentsName) throws IOException {
    this.preMessage(parentsName);
    label:
    while (true) {
      switch (in.next()) {
        case "2": {
          info();
          String str = in.next();
          if (str.equalsIgnoreCase("1")) {
            Master master = Master.builder()
              .id(UUID.randomUUID())
              .description(validator.validateDescription(in))
              .homeAddress(validator.validateHomeAddress(in))
              .education(validator.validateEducation(in))
              .name(validator.validateNameUser(in))
              .password(validator.validatePassword(in))
              .mail(validator.validateMail(in))
              .phone(validator.validatePhone(in))
              .login(validator.validateLogin(in))
              .qualificationEnum(validator.qualificationEnum(in))
              .outfits(new ArrayList<>())
              .role(Role.MASTER)
              .build();
            if (this.masterServices.addMaster(master)) {
              log.info("Data saved successfully");
            } else {
              log.info("Data not saved please try again");
            }
            break label;
          }
          if (str.equalsIgnoreCase("2")) {
            MasterReceiver masterReceiver = MasterReceiver.builder()
              .id(UUID.randomUUID())
              .education(validator.validateEducation(in))
              .description(validator.validateDescription(in))
              .homeAddress(validator.validateHomeAddress(in))
              .qualificationEnum(validator.qualificationEnum(in))
              .mail(validator.validateMail(in))
              .login(validator.validateLogin(in))
              .password(validator.validatePassword(in))
              .role(Role.RECEPTIONIST)
              .name(validator.validateNameUser(in))
              .orders(new ArrayList<>())
              .build();
            if (this.masterReceiverServices.addMaster(masterReceiver)) {
              log.info("Data saved successfully");
            } else {
              log.info("Data not saved please try again");
            }
            break label;
          } else {
            this.preMessage(parentsName);
            continue label;
          }
        }
        case "1": {
          break label;
        }

        case "3": {
          this.preMessage(parentsName);
          break label;
        }
        default: {
          this.preMessage(parentsName);
          break;
        }
      }
    }
  }

  private void info() {
    log.info("Enter 1 create MASTER");
    log.info("Enter 2 create RECEPTIONIST");
  }

}