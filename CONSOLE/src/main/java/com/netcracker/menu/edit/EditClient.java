package com.netcracker.menu.edit;

import com.netcracker.menu.Menu;
import com.netcracker.menu.validator.ValidatorInstrumentsImpl;
import com.netcracker.menu.validator.ValidatorInstruments;
import com.netcracker.servisec.UserSession;
import com.netcracker.user.Client;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Scanner;


@Slf4j
public class EditClient implements Menu {

  private final Client client;
  private final ValidatorInstruments validator = new ValidatorInstrumentsImpl();

  @Override
  public void preMessage(String parentsName) {
    log.info("Enter 1 {}", parentsName);

  }

  public EditClient() {
    this.client = UserSession.getCloneClientSession();
  }

  public EditClient(Client client) {
    this.client = UserSession.getCloneClient(client);
  }


  @Override
  public void run(Scanner in, String parentsName) throws IOException {
    log.info("Descriptions");
    if (validator.edit(this.client.getDescription(), in)) {
      this.client.setDescription(validator.getDescription(in));
    }
    log.info("Name");
    if (validator.edit(this.client.getName(), in)) {
      this.client.setName(validator.getNameUser(in));
    }
    log.info("Login");
    if (validator.edit(this.client.getLogin(), in)) {
      this.client.setLogin(validator.getLogin(in));
    }
    log.info("Password");
    if (validator.edit(this.client.getPassword(), in)) {
      this.client.setPassword(validator.getPassword(in));
    }
    log.info("Phone");
    if (validator.edit(this.client.getPhone(), in)) {
      this.client.setPhone(validator.getPhone(in));
    }
    log.info("Email");
    if (validator.edit(this.client.getEmail(), in)) {
      this.client.setPassword(validator.getPassword(in));
    }
  }

  public Client getClient() {
    return client;
  }

}