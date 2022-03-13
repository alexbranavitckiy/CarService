package com.netcracker.menu.edit;

import com.netcracker.menu.Menu;
import com.netcracker.menu.validator.ValidatorInstrumentsImpl;
import com.netcracker.menu.validator.ValidatorInstruments;
import com.netcracker.servisec.Impl.masterReceiver.MasterReceiverServicesImpl;
import com.netcracker.servisec.MasterReceiverServices;
import com.netcracker.servisec.UserSession;
import com.netcracker.user.MasterReceiver;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.util.Scanner;

@Slf4j
public class EditMasterReceiver implements Menu {

  private final MasterReceiver masterReceiver;
  private final MasterReceiverServices masterReceiverServices = new MasterReceiverServicesImpl();
  private final ValidatorInstruments validator = new ValidatorInstrumentsImpl();

  @Override
  public void preMessage(String parentsName) {
    log.info("Enter 1 {}", parentsName);

  }

  public EditMasterReceiver() {
    this.masterReceiver = UserSession.getCloneMasterReceiverSession();
  }

  @Override
  public void run(Scanner in, String parentsName) throws IOException {
    log.info("Name");
    if (validator.edit(this.masterReceiver.getName(), in)) {
      this.masterReceiver.setName(validator.getNameUser(in));
    }
    log.info("Descriptions");
    if (validator.edit(this.masterReceiver.getDescription(), in)) {
      this.masterReceiver.setDescription(validator.getDescription(in));
    }
    log.info("Login");
    if (validator.edit(this.masterReceiver.getLogin(), in)) {
      this.masterReceiver.setLogin(validator.getLogin(in));
    }
    log.info("Enter a new password");
    this.masterReceiver.setPassword(validator.getPassword(in));
    log.info("Education");
    if (validator.edit(this.masterReceiver.getEducation(), in)) {
      this.masterReceiver.setPassword(validator.getEducation(in));
    }
    log.info("HomeAddress");
    if (validator.edit(this.masterReceiver.getHomeAddress(), in)) {
      this.masterReceiver.setHomeAddress(validator.getHomeAddress(in));
    }
    log.info("Mail");
    if (validator.edit(this.masterReceiver.getMail(), in)) {
      this.masterReceiver.setMail(masterReceiver.getMail());
    }
    log.info("Phone");
    if (validator.edit(this.masterReceiver.getPhone(), in)) {
      this.masterReceiver.setPhone(validator.getPhone(in));
    }
    if (masterReceiverServices.updateMaster(masterReceiver)) {
      log.info("Data entered successfully");
    } else {
      log.info("An input error occurred while entering data. Retry data change");
    }
  }
}
