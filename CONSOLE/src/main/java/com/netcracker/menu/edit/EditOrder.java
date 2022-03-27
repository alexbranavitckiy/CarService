package com.netcracker.menu.edit;

import com.netcracker.menu.Menu;
import com.netcracker.menu.validator.ValidatorInstruments;
import com.netcracker.menu.validator.ValidatorInstrumentsImpl;
import com.netcracker.order.Order;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.netcracker.order.State;
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
  Optional<State> state = List.of(State.values()).stream().filter(x -> x.getId().equals(this.order.getStateOrder())).findFirst();
  log.info("State order");
  if (state.isPresent()) {
   if (validator.edit(state.get().toString(), in)) {
    this.order.setStateOrder(validator.orderState(in));
   }
  } else {
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