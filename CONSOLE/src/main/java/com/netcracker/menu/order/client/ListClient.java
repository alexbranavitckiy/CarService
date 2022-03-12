package com.netcracker.menu.order.client;

import com.netcracker.errors.EmptySearchException;
import com.netcracker.menu.Menu;
import com.netcracker.menu.edit.EditClient;
import com.netcracker.servisec.ClientServices;
import com.netcracker.servisec.Impl.client.ClientServicesImpl;
import com.netcracker.servisec.Impl.LoginServicesImpl;
import com.netcracker.servisec.LoginService;
import com.netcracker.user.Client;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


@Slf4j
public class ListClient implements Menu {

  private final ClientServices searchServices = new ClientServicesImpl();

  @Override
  public void preMessage(String parentsName) {
    log.info("Enter 1: {}", parentsName);
    log.info("Enter 2 Display a list of clients.");
    log.info("Enter 3 Search for a client by keyword.");
  }

  @Override
  public void run(Scanner in, String parentsName) throws IOException {
    this.preMessage(parentsName);
    label:
    while (true) {
      switch (in.next()) {
        case "2": {
          try {
            List<Client> clientList = searchServices.getAllClient();
            if (clientList.size() > 0) {
              for (int x = 1; x < clientList.size() + 1; x++) {
                log.info("Id:{} {} ", x, clientList.get(x - 1));
              }
              log.info("Edit client? 1-yeas. 2-no");
              if (in.next().equalsIgnoreCase("1")) {
                log.info("Enter client id");
                EditClient editClient = new EditClient(clientList.get(in.nextInt() - 1));
                editClient.run(in, "Edit Client");
                if (searchServices.updateClient(editClient.getClient())) {
                  log.warn("Data entered successfully");
                } else {
                  log.warn("An input error occurred while entering data. Retry data change");
                }
              }
            }
          } catch (EmptySearchException e) {
            log.warn("The search has not given any results. {}", e.getMessage());
          } catch (InputMismatchException e) {
            log.warn("Invalid data:{}. Please try again", e.getMessage());
          }
          this.preMessage(parentsName);
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