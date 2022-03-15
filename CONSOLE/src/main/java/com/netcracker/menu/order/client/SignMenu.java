package com.netcracker.menu.order.client;

import com.netcracker.marka.CarClient;
import com.netcracker.menu.Menu;
import com.netcracker.menu.validator.ValidatorInstruments;
import com.netcracker.menu.validator.ValidatorInstrumentsImpl;
import com.netcracker.order.Order;
import com.netcracker.order.State;
import com.netcracker.servisec.Impl.order.OrderServicesImpl;
import com.netcracker.servisec.OrderServices;
import com.netcracker.servisec.UserSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SignMenu implements Menu {

  private final ValidatorInstruments validator = new ValidatorInstrumentsImpl();
  private final OrderServices orderServices = new OrderServicesImpl();

  @Override
  public void preMessage(String nameMenu) {
    log.info("Enter 1 {}", nameMenu);
    log.info("Enter 2 Submit a repair request");
    // log.info("Enter 3 Find out about the status of the order and the machine");//TODO Add functionality!!!
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
          UserSession.getClientSession().ifPresent(x -> {
            if (x.getCarClients().size() > 0) {
              for (int z = 0; z < x.getCarClients().size(); z++) {
                log.info("id:{} Car:{}", z + 1, x.getCarClients());
              }
              try {
                log.info("Enter car id");
                CarClient carClient = UserSession.getClientSession().get().getCarClients()
                    .get(in.nextInt() - 1);
                UUID orderUUID = UUID.randomUUID();
                log.info("Fill in the request information");
                Order order = Order.builder()
                    .id(orderUUID)
                    .clientUUID(UserSession.getClientSession().get().getId())
                    .stateOrder(State.REQUEST)
                    .outfits(new ArrayList<>())
                    .dateCreat(new Date())
                    .idCar(carClient.getId())
                    .dateUpdate(new Date())
                    .entry(new ArrayList<>())
                    .descriptions(validator.getDescription(in))
                    .priceSum(0d)
                    .build();
                validator.successfullyMessages(orderServices.addOrder(order));
                this.preMessage(parentsName);
              } catch (IndexOutOfBoundsException e) {
                log.info("The selected car was not found. Please try again");
              }
            } else {
              log.info("At the moment you have no registered cars please log out or add a car");
              this.preMessage(parentsName);
            }
          });
          break;
        }
        default: {
          this.preMessage(parentsName);
          break;
        }
      }
    }
  }

}
