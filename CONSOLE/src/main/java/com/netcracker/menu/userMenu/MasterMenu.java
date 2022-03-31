package com.netcracker.menu.userMenu;

import com.netcracker.CarBreakdownServices;
import com.netcracker.factory.ServicesFactory;
import com.netcracker.menu.Menu;
import com.netcracker.menu.car.CarBreakdownCreate;
import com.netcracker.menu.validator.ValidatorInstruments;
import com.netcracker.menu.validator.ValidatorInstrumentsImpl;
import com.netcracker.order.State;
import com.netcracker.outfit.Outfit;
import com.netcracker.OutfitsServices;
import com.netcracker.session.UserSession;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Scanner;

@Slf4j
public class MasterMenu implements Menu {

 private final OutfitsServices outfitsServices;
 private final ValidatorInstruments validator = new ValidatorInstrumentsImpl();
 private final ServicesFactory servicesFactory;

 public MasterMenu(ServicesFactory servicesFactory) {
  this.servicesFactory = servicesFactory;
  this.outfitsServices = servicesFactory.getFactory().getOutfitServices();
 }

 @Override
 public void preMessage(String nameMenu) {
  log.info("Enter 1 {}", nameMenu);
  log.info("Enter 2 list of current outfits");
  log.info("My outfits({})", outfitsServices.getAllByMasterAndState(UserSession.getMasterSession().get().getId(), State.RECORDED.getId()).size());
 }

 @Override
 public void run(Scanner in, String parentsName) throws IOException {
  log.info("You are logged in as a master");
  this.preMessage(parentsName);
  label:
  while (true) {
   switch (in.next()) {
    case "1": {
     break label;
    }
    case "2": {
     List<Outfit> outfitList = outfitsServices.getAllByMasterAndState(UserSession.getMasterSession().get().getId(), State.RECORDED.getId());
     if (outfitList.size() > 0) {
      for (int x = 0; x < outfitList.size(); x++) {
       log.info(
        "Id[{}] DateStart: {}/DateEnt: {}//Descriptions: {}/Name: {}/Price:{}. ",
        x + 1
        , outfitList.get(x).getDateStart()
        , outfitList.get(x).getDateEnt()
        , outfitList.get(x).getDescriptions()
        , outfitList.get(x).getName()
        , outfitList.get(x).getPrice());
      }
     }
     log.info("Proceed to order? Enter 1-yeas/ 2-no");
     if (in.next().equalsIgnoreCase("1")) {
      log.info("Enter outfit ID");
      try {
       Outfit outfit = outfitList.get(in.nextInt() - 1);
       log.info("Enter information about breakdowns");
       CarBreakdownCreate carBreakdownCreate = new CarBreakdownCreate(servicesFactory
        .getFactory()
        .getOrderServices()
        .getOrderById(outfit.getOrder()).get().getIdCar(), servicesFactory);
       carBreakdownCreate.run(in, "");
       log.info("Descriptions");
       if (validator.edit(outfit.getDescriptions(), in)) {
        outfit.setDescriptions(validator.validateDescription(in));
       }
       outfit.setStateOutfit(com.netcracker.outfit.State.END.getId());
       log.info("Enter price outfit");
       if (validator.edit(String.valueOf(outfit.getPrice()), in)) {
        outfit.setPrice(validator.validatePrice(in));
       }
       log.info("Enter data end");
       if (validator.edit(String.valueOf(outfit.getDateEnt()), in)) {
        outfit.setDateEnt(validator.getDate(in));
       }
       log.info("Enter price");
       if (validator.edit(String.valueOf(outfit.getPrice()), in)) {
        outfit.setPrice(validator.validatePrice(in));
       }
       validator.successfullyMessages(outfitsServices.updateOutfit(outfit));
      } catch (Exception e) {
       log.info("Invalid index entered");
      }
     } else {
      log.info("No existing outfits");
      this.preMessage(parentsName);
      break;
     }
     this.preMessage(parentsName);
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


