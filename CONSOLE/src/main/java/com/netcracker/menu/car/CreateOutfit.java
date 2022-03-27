package com.netcracker.menu.car;


import com.netcracker.factory.ServicesFactory;
import com.netcracker.menu.Menu;
import com.netcracker.menu.order.master.ListMaster;
import com.netcracker.menu.validator.ValidatorInstrumentsImpl;
import com.netcracker.outfit.Outfit;
import com.netcracker.OutfitsServices;

import java.io.IOException;
import java.util.Scanner;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateOutfit implements Menu {

 private Outfit outfit;
 private final ValidatorInstrumentsImpl validator = new ValidatorInstrumentsImpl();
 private final UUID order;
 private final ServicesFactory servicesFactory;

 public CreateOutfit(UUID order, ServicesFactory servicesFactory) {
  this.servicesFactory = servicesFactory;
  this.order = order;
 }

 @Override
 public void run(Scanner in, String parentsName) throws IOException {
  OutfitsServices outfitsServices = servicesFactory.getOutfitServices();
  this.outfit = Outfit.builder()
   .id(UUID.randomUUID())
   .descriptions(validator.validateDescription(in))
   .stateOutfit(validator.stateOutfit(in))
   .name(validator.validateNameOutfit(in))
   .price(validator.validatePrice(in))
   .order(this.order)
   .build();
  log.info("appoint master.");
  ListMaster listMaster = new ListMaster(servicesFactory);
  listMaster.run(in, "");
  this.outfit.setEmployer(listMaster.getMaster().getId());
  listMaster.getMaster().getOutfits().add(this.outfit.getId());
  log.info("List data:");
  validator.successfullyMessages(servicesFactory.getMasterServices().updateMaster(listMaster.getMaster()));
  log.info("Outfit data:");
  validator.successfullyMessages(outfitsServices.addObjectInOutfits(outfit));
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
  return order;
 }
}
