package com.netcracker.menu.order;

import com.netcracker.errors.EmptySearchException;
import com.netcracker.menu.Menu;
import com.netcracker.menu.car.CreateOutfit;
import com.netcracker.menu.edit.EditOrder;
import com.netcracker.menu.validator.ValidatorInstruments;
import com.netcracker.menu.validator.ValidatorInstrumentsImpl;
import com.netcracker.order.Order;
import com.netcracker.servisec.Impl.order.OrderServicesImpl;
import com.netcracker.servisec.OrderServices;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ListOrders implements Menu {

  private final OrderServices orderServices = new OrderServicesImpl();
  private Order order;
  private final ValidatorInstruments validator = new ValidatorInstrumentsImpl();

  public ListOrders() {
    this.order = null;
  }

  @Override
  public void preMessage(String parentsName) {
    log.info("Enter 1: {}", parentsName);
    log.info("Enter 2 Display a list of orders.");
    log.info("Enter 3 Display a list of orders. With request type");
  }

  @Override
  public void run(Scanner in, String parentsName) throws IOException {
    this.preMessage(parentsName);
    label:
    while (true) {
      switch (in.next()) {
        case "2": {
          try {
            List<Order> orders = orderServices.getAll();
            if (orders.size() > 0) {
              for (int x = 1; x < orders.size() + 1; x++) {
                log.info("Id:{} {} ", x, orders.get(x - 1));
              }
            }
            log.info("Go to order ID:");
            this.order = orders.get(in.nextInt() - 1);
            this.EditOrder(in);
            break label;
          } catch (EmptySearchException e) {
            log.warn("The search has not given any results. {}", e.getMessage());
          } catch (InputMismatchException e) {
            log.warn("Invalid data:{}. Please try again", e.getMessage());
          } catch (IndexOutOfBoundsException e) {
            log.info("Selected order not found");
          }
          this.preMessage(parentsName);
          break;
        }
        case "3": {
          try {
            List<Order> orders = orderServices.getOrderWithRequestState();
            this.printOrders(orders);
            log.info("Go to order ID:");
            this.order = orders.get(in.nextInt() - 1);
            this.EditOrder(in);
            log.info("Create an outfit? 1-yeas. 2-no");
            if (in.next().equals("1")) {
              log.info("Outfit data:");
              CreateOutfit createOutfit = new CreateOutfit(this.order.getId());
              createOutfit.run(in,
                  "");
              order.setOutfits(new ArrayList<>());
              order.getOutfits().add(createOutfit.getOrder());
              validator.successfullyMessages(orderServices.addOrder(order));
            }
          } catch (InputMismatchException e) {
            log.warn("Invalid data:{}. Please try again", e.getMessage());
          } catch (IndexOutOfBoundsException e) {
            log.info("Selected order not found");
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

  private void printOrders(List<Order> orders) {
    if (orders.size() > 0) {
      for (int x = 1; x < orders.size() + 1; x++) {
        log.info("Id:{} {} ", x, orders.get(x - 1));
      }
    }
  }

  private void EditOrder(Scanner in) throws IOException {
    log.info("Edit Order? 1-yeas. 2-no");
    if (in.next().equals("1")) {
      EditOrder editOrder = new EditOrder(this.order);
      editOrder.run(in, "Main menu");
      validator.successfullyMessages(orderServices.addOrder(editOrder.getOrder()));
      this.order = editOrder.getOrder();
    }
  }

  public Optional<Order> getOrder() {
    return Optional.of(this.order);
  }

}