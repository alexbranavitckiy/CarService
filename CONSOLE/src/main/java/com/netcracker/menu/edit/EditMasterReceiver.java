package com.netcracker.menu.edit;

import com.netcracker.factory.ServicesFactory;
import com.netcracker.menu.Menu;
import com.netcracker.menu.validator.ValidatorInstrumentsImpl;
import com.netcracker.menu.validator.ValidatorInstruments;
import com.netcracker.MasterReceiverServices;
import com.netcracker.session.UserSession;
import com.netcracker.user.MasterReceiver;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Scanner;

@Slf4j
public class EditMasterReceiver implements Menu {

 private final MasterReceiver masterReceiver;
 private final ValidatorInstruments validator = new ValidatorInstrumentsImpl();
 private final ServicesFactory servicesFactory;

 public EditMasterReceiver(ServicesFactory servicesFactory) {
  this.masterReceiver = UserSession.getCloneMasterReceiverSession();
  this.servicesFactory = servicesFactory;
 }

 @Override
 public void preMessage(String parentsName) {
  log.info("Enter 1 {}", parentsName);
 }


 @Override
 public void run(Scanner in, String parentsName) throws IOException {
  MasterReceiverServices masterReceiverServices = servicesFactory.getMasterReceiverServices();
  log.info("Name");
  if (validator.edit(this.masterReceiver.getName(), in)) {
   this.masterReceiver.setName(validator.validateNameUser(in));
  }
  log.info("Descriptions");
  if (validator.edit(this.masterReceiver.getDescription(), in)) {
   this.masterReceiver.setDescription(validator.validateDescription(in));
  }
  log.info("Login");
  if (validator.edit(this.masterReceiver.getLogin(), in)) {
   this.masterReceiver.setLogin(validator.validateLogin(in));
  }
  log.info("Enter a new password");
  this.masterReceiver.setPassword(validator.validatePassword(in));
  log.info("Education");
  if (validator.edit(this.masterReceiver.getEducation(), in)) {
   this.masterReceiver.setPassword(validator.validateEducation(in));
  }
  log.info("HomeAddress");
  if (validator.edit(this.masterReceiver.getHomeAddress(), in)) {
   this.masterReceiver.setHomeAddress(validator.validateHomeAddress(in));
  }
  log.info("Mail");
  if (validator.edit(this.masterReceiver.getMail(), in)) {
   this.masterReceiver.setMail(masterReceiver.getMail());
  }
  log.info("Phone");
  if (validator.edit(this.masterReceiver.getPhone(), in)) {
   this.masterReceiver.setPhone(validator.validatePhone(in));
  }
  if (masterReceiverServices.updateMaster(masterReceiver)) {
   log.info("Data entered successfully");
  } else {
   log.info("An input error occurred while entering data. Retry data change");
  }
 }
}
