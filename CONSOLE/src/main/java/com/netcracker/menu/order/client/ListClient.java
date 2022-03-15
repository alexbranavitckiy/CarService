package com.netcracker.menu.order.client;

import com.netcracker.errors.EmptySearchException;
import com.netcracker.menu.Menu;
import com.netcracker.menu.edit.EditClient;
import com.netcracker.servisec.ClientServices;
import com.netcracker.servisec.Impl.client.ClientServicesImpl;
import com.netcracker.user.Client;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


@Slf4j
public class ListClient implements Menu {

  private final ClientServices searchServices = new ClientServicesImpl();
  private Client client;

  public ListClient() {
    this.client = null;
  }

  @Override
  public void preMessage(String parentsName) {
    log.info("Enter 1: {}", parentsName);
    log.info("Enter 2 Display a list of clients.");
  }

  @Override
  public void run(Scanner in, String parentsName) throws IOException {
    List<Client> clientList;
    this.preMessage(parentsName);
    label:
    while (true) {
      switch (in.next()) {
        case "2": {
          try {
            clientList = searchServices.getAllClient();
            if (clientList.size() > 0) {
              for (int x = 1; x < clientList.size() + 1; x++) {
                log.info("Id:{} {} ", x, clientList.get(x - 1));
              }
              log.info("Enter client id");
              this.client = clientList.get(in.nextInt() - 1);
              break label;
            }
          } catch (EmptySearchException e) {
            log.warn("The search has not given any results. {}", e.getMessage());
          } catch (InputMismatchException e) {
            log.warn("Invalid data:{}. Please try again", e.getMessage());
          } catch (IndexOutOfBoundsException e) {
            log.info("Selected client not found");
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

  public Optional<Client> getClient() {
    return Optional.of(client);
  }

}