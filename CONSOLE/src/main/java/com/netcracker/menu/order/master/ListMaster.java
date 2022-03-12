package com.netcracker.menu.order.master;

import com.netcracker.errors.EmptySearchException;
import com.netcracker.menu.Menu;
import com.netcracker.menu.edit.EditClient;
import com.netcracker.servisec.ClientServices;
import com.netcracker.servisec.Impl.client.ClientServicesImpl;
import com.netcracker.servisec.Impl.master.MasterServicesImpl;
import com.netcracker.servisec.MasterServices;
import com.netcracker.user.Client;
import com.netcracker.user.Master;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ListMaster implements Menu {

  private final MasterServices masterServices = new MasterServicesImpl();

  @Override
  public void preMessage(String parentsName) {
    log.info("Enter 1: {}", parentsName);
    log.info("Enter 2 Display a list of master.");
  }

  @Override
  public void run(Scanner in, String parentsName) throws IOException {
    this.preMessage(parentsName);
    label:
    while (true) {
      switch (in.next()) {
        case "1": {
          log.info("");
          break label;
        }
        case "2": {
          List<Master> masters = masterServices.getAllMaster();
          log.info(masters.toString());
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