package com.netcracker.menu.order;

import com.netcracker.menu.Menu;
import com.netcracker.menu.car.CreateOutfit;
import com.netcracker.menu.validator.ValidatorInstrumentsImpl;
import com.netcracker.menu.validator.ValidatorInstruments;
import com.netcracker.order.Order;
import com.netcracker.servisec.Impl.order.OrderServicesImpl;
import com.netcracker.servisec.OrderServices;
import com.netcracker.user.Client;
import java.util.ArrayList;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Scanner;
import java.util.UUID;

@Slf4j
public class NewOrder implements Menu {

  private final ValidatorInstruments validator = new ValidatorInstrumentsImpl();
  private final Client client;
  private final OrderServices orderServices = new OrderServicesImpl();
  private UUID orderUUID;

  public NewOrder(Client client, UUID idCar) {
    this.orderUUID = null;
    this.client = client;
  }

  @Override
  public void preMessage(String parentsName) {
    log.info("Enter 1 to leave");
    log.info("Enter 2 to create order ");
  }

  @Override
  public void run(Scanner in, String parentsName) throws IOException {
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
                  .dateCreat(new Date())
                  .dateUpdate(new Date())
                  .entry(new ArrayList<>())
                  .descriptions(validator.getDescription(in))
                  .priceSum(0d)
                  .build();
              log.info("Outfit data:");
              CreateOutfit createOutfit = new CreateOutfit(orderUUID);
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