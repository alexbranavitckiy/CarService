package com.netcracker.menu.registration;

import com.netcracker.menu.Menu;
import com.netcracker.menu.validator.ValidatorInstrumentsImpl;
import com.netcracker.servisec.Impl.master.MasterServicesImpl;
import com.netcracker.servisec.Impl.masterReceiver.MasterReceiverServicesImpl;
import com.netcracker.servisec.MasterReceiverServices;
import com.netcracker.servisec.MasterServices;
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
    log.info("Enter 3 to correct");
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
                .description(validator.getDescription(in))
                .homeAddress(validator.getHomeAddress(in))
                .education(validator.getEducation(in))
                .name(validator.getNameUser(in))
                .password(validator.getPassword(in))
                .mail(validator.getMail(in))
                .phone(validator.getPhone(in))
                .login(validator.getLogin(in))
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
                .education(validator.getEducation(in))
                .description(validator.getDescription(in))
                .homeAddress(validator.getHomeAddress(in))
                .qualificationEnum(validator.qualificationEnum(in))
                .mail(validator.getMail(in))
                .login(validator.getLogin(in))
                .password(validator.getPassword(in))
                .role(Role.RECEPTIONIST)
                .name(validator.getNameUser(in))
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