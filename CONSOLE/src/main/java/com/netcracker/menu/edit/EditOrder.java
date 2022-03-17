package com.netcracker.menu.edit;

import com.netcracker.menu.Menu;
import com.netcracker.menu.validator.ValidatorInstruments;
import com.netcracker.menu.validator.ValidatorInstrumentsImpl;
import com.netcracker.order.Order;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EditOrder implements Menu {

  private final Order order;
  private final ValidatorInstruments validator = new ValidatorInstrumentsImpl();

  @Override
  public void preMessage(String parentsName) {
    log.info("Enter 1 {}", parentsName);
  }

  public EditOrder(Order order) {
    this.order = order;
  }

  @Override
  public void run(Scanner in, String parentsName) throws IOException {
    log.info("Descriptions");
    if (validator.edit(this.order.getDescriptions(), in)) {
      this.order.setDescriptions(validator.validateDescription(in));
    }
    log.info("State order");
    if (validator.edit(this.order.getStateOrder().toString(), in)) {
      this.order.setStateOrder(validator.orderState(in));
    }
    log.info("Price sum:");
    if (validator.edit(String.valueOf(this.order.getPriceSum()), in)) {
      this.order.setPriceSum(validator.validatePrice(in));
    }
    this.order.setUpdatedDate(new Date());
  }

  public Order getOrder() {
    return order;
  }
}