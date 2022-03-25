package com.netcracker.menu.order;

import com.netcracker.factory.ServicesFactory;
import com.netcracker.menu.Menu;
import com.netcracker.menu.validator.ValidatorInstruments;
import com.netcracker.menu.validator.ValidatorInstrumentsImpl;
import com.netcracker.order.Order;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Scanner;

@Slf4j
public class OrderMenu implements Menu {

 private final ValidatorInstruments validator = new ValidatorInstrumentsImpl();
 private Order order;
 private final ServicesFactory servicesFactory;

 public OrderMenu(Order order, ServicesFactory servicesFactory) {
  this.servicesFactory = servicesFactory;
  this.order = order;
 }

 @Override
 public void preMessage(String parentsName) {
  log.info("You have entered the order menu");
  log.info("Enter 1 " + parentsName);
  log.info("Enter 2 Display order information");
 }

 @Override
 public void run(Scanner in, String parentsName) throws IOException {
  this.preMessage(parentsName);
  label:
  while (true) {
   switch (in.next()) {
    case "2": {
     log.info("List of orders");
     this.preMessage(parentsName);
     break;
    }
    case "3": {
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
