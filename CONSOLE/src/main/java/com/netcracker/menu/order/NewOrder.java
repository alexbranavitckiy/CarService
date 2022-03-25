package com.netcracker.menu.order;

import com.netcracker.factory.ServicesFactory;
import com.netcracker.menu.Menu;
import com.netcracker.menu.car.CreateOutfit;
import com.netcracker.menu.validator.ValidatorInstrumentsImpl;
import com.netcracker.menu.validator.ValidatorInstruments;
import com.netcracker.order.Order;
import com.netcracker.OrderServices;

import java.util.ArrayList;
import java.util.Date;

import com.netcracker.user.Client;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Scanner;
import java.util.UUID;

@Slf4j
public class NewOrder implements Menu {

 private final ValidatorInstruments validator = new ValidatorInstrumentsImpl();
 private final Client client;
 private final OrderServices orderServices;
 private final UUID idCar;
 private final ServicesFactory servicesFactory;


 public NewOrder(Client client, UUID idCar, ServicesFactory servicesFactory) {
  this.client = client;
  this.idCar = idCar;
  this.servicesFactory = servicesFactory;
  this.orderServices = servicesFactory.getOrderServices();
 }

 @Override
 public void preMessage(String parentsName) {
  log.info("Enter 1 to leave");
  log.info("Enter 2 to create order ");
 }

 @Override
 public void run(Scanner in, String parentsName) throws IOException {
  UUID orderUUID;
  this.preMessage(parentsName);
  label:
  while (true) {
   switch (in.next()) {
    case "1": {
     break label;
    }
    case "2": {
     if (this.client != null && client.getId() != null) {
      log.info("Create an order with a customer?:Login {}, name:{}, phone:{}",
       client.getLogin(), client.getName(), client.getPhone());
      log.info("Enter 1-yes. 2-not ");
      if (in.next().equalsIgnoreCase("1")) {
       orderUUID = UUID.randomUUID();
       Order order = Order.builder()
        .id(orderUUID)
        .clientUUID(this.client.getId())
        .stateOrder(validator.orderState(in))
        .outfits(new ArrayList<>())
        .createdDate(new Date())
        .idCar(idCar)
        .updatedDate(new Date())
        .label(new ArrayList<>())
        .descriptions(validator.validateDescription(in))
        .priceSum(0d)
        .build();
       log.info("Outfit data:");
       CreateOutfit createOutfit = new CreateOutfit(orderUUID, servicesFactory);
       createOutfit.run(in,
        "");
       order.setOutfits(new ArrayList<>());
       order.getOutfits().add(createOutfit.getOrder());
       validator.successfullyMessages(orderServices.addOrder(order));
      }
     }
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