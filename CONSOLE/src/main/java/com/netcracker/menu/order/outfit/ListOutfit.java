package com.netcracker.menu.order.outfit;

import com.netcracker.errors.EmptySearchException;
import com.netcracker.factory.ServicesFactory;
import com.netcracker.menu.Menu;
import com.netcracker.menu.edit.EditOutfit;
import com.netcracker.menu.validator.ValidatorInstruments;
import com.netcracker.menu.validator.ValidatorInstrumentsImpl;
import com.netcracker.outfit.Outfit;
import com.netcracker.OutfitsServices;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class ListOutfit implements Menu {

 private final ServicesFactory servicesFactory;
 private Outfit outfit;
 private final ValidatorInstruments validator = new ValidatorInstrumentsImpl();


 public ListOutfit(ServicesFactory servicesFactory) {
  this.servicesFactory = servicesFactory;
 }

 @Override
 public void preMessage(String parentsName) {
  log.info("Enter 1: {}", parentsName);
  log.info("Enter 2 Show all outfits.");
  log.info("Enter 3 Show outfit by time.");
  log.info("Enter 4 Select and edit.");
 }

 @Override
 public void run(Scanner in, String parentsName) throws IOException {
  OutfitsServices outfitsServices = servicesFactory.getFactory().getOutfitServices();
  this.preMessage(parentsName);
  label:
  while (true) {
   switch (in.next()) {
    case "2": {
     try {
      this.print(outfitsServices.getAllOutfits(), in);
     } catch (EmptySearchException e) {
      log.warn("The search has not given any results. {}", e.getMessage());
     } catch (InputMismatchException e) {
      log.warn("Invalid data:{}. Please try again", e.getMessage());
     } catch (IndexOutOfBoundsException e) {
      log.info("Selected  not found:{}", e.getMessage());
     }
     this.preMessage(parentsName);
     break;
    }
    case "3": {
     try {
      log.info("Enter the time interval");
      this.print(outfitsServices.getAllOutfitsByData(validator.getDate(in).toString(), validator.getDate(in).toString()), in);
     } catch (InputMismatchException e) {
      log.warn("Invalid data:{}. Please try again", e.getMessage());
     } catch (IndexOutOfBoundsException e) {
      log.info("Selected  not found:{}", e.getMessage());
     }
     this.preMessage(parentsName);
     break;
    }
    case "1": {
     break label;
    }
    case "4": {
     try {
      this.print(outfitsServices.getAllOutfits(), in);
      EditOutfit editOutfit = new EditOutfit(this.outfit);
      editOutfit.run(in, "Main menu");
      log.info("Enter dateState");
      if (validator.edit(String.valueOf(this.outfit.getDateStart()), in)) {
       this.outfit.setDateStart(validator.getDate(in));
      }
      log.info("Enter dateEnd");
      if (validator.edit(String.valueOf(this.outfit.getDateEnt()), in)) {
       this.outfit.setDateEnt(validator.getDate(in));
      }
      validator.successfullyMessages(outfitsServices.updateOutfit(this.outfit));
     } catch (EmptySearchException e) {
      log.warn("The search has not given any results. {}", e.getMessage());
     } catch (InputMismatchException e) {
      log.warn("Invalid data:{}. Please try again", e.getMessage());
     } catch (IndexOutOfBoundsException e) {
      log.info("Selected  not found:{}", e.getMessage());
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

 private void print(List<Outfit> outfitList, Scanner in) {
  if (outfitList.size() > 0) {
   for (int x = 0; x < outfitList.size(); x++) {
    log.info(
     "Id[{}] DateStart: {} DateEnt: {} StateOutfit: {} Descriptions: {} Name: {}. ",
     x + 1, outfitList.get(x).getDateStart(), outfitList.get(x).getDateEnt(),
     outfitList.get(x).getStateOutfit(), outfitList.get(x).getDescriptions(),
     outfitList.get(x).getName());
   }
   log.info("Enter outfit id");
   this.outfit = outfitList.get(in.nextInt() - 1);
  } else {
   log.info("Outfit that doesn't exist");
  }
 }

 public Optional<Outfit> getOutfit() {
  return Optional.of(this.outfit);
 }
}