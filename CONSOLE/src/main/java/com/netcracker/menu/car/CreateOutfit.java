package com.netcracker.menu.car;


import com.netcracker.factory.ServicesFactory;
import com.netcracker.menu.Menu;
import com.netcracker.menu.order.master.ListMaster;
import com.netcracker.menu.validator.ValidatorInstrumentsImpl;
import com.netcracker.outfit.Outfit;

import java.io.IOException;
import java.util.Scanner;
import java.util.UUID;

import com.netcracker.outfit.State;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateOutfit implements Menu {

 private Outfit outfit;
 private final ValidatorInstrumentsImpl validator = new ValidatorInstrumentsImpl();
 private final UUID orderUUID;
 private final ServicesFactory servicesFactory;

 public CreateOutfit(UUID orderUUID, ServicesFactory servicesFactory) {
  this.servicesFactory = servicesFactory;
  this.orderUUID = orderUUID;
 }

 @Override
 public void run(Scanner in, String parentsName) throws IOException {
  this.outfit = Outfit.builder()
   .id(UUID.randomUUID())
   .descriptions(validator.validateDescription(in))
   .stateOutfit(State.RECORDED.getId())
   .name(validator.validateNameOutfit(in))
   .price(validator.validatePrice(in))
   .order(this.orderUUID)
   .dateEnt(validator.getDate(in))
   .dateStart(validator.getDate(in))
   .build();
  log.info("appoint master.");
  ListMaster listMaster = new ListMaster(servicesFactory);
  listMaster.run(in, "");
  this.outfit.setEmployer(listMaster.getMaster().getId());
  log.info("Outfit data:");
 }

 private void dateEditor() {
  log.info("Outfit recording menu");
  log.info("Enter 1. Pull out all the outfits");
  log.info("Enter 2. List order by date");
 }


 @Override
 public void preMessage(String parentsName) {
  log.info(parentsName);
 }

 public Outfit getOutfit() {
  return outfit;
 }

 public void setOutfit(Outfit outfit) {
  this.outfit = outfit;
 }

 public UUID getOrder() {
  return orderUUID;
 }
}
