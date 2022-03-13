package com.netcracker.menu.order.outfit;

import com.netcracker.errors.EmptySearchException;
import com.netcracker.menu.Menu;
import com.netcracker.outfit.Outfit;
import com.netcracker.servisec.Impl.outfit.OutfitsServicesImpl;
import com.netcracker.servisec.OutfitsServices;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class ListOutfit implements Menu {

  private final OutfitsServices outfitsServices = new OutfitsServicesImpl();


  @Override
  public void preMessage(String parentsName) {
    log.info("Enter 1: {}", parentsName);
    log.info("Enter 2 Display outfits.");
  }

  @Override
  public void run(Scanner in, String parentsName) throws IOException {
    this.preMessage(parentsName);
    label:
    while (true) {
      switch (in.next()) {
        case "2": {
          try {
            List<Outfit> outfitList = outfitsServices.getAllOutfits();
            for (int x = 0; x < outfitList.size(); x++) {
              log.info(
                  "id[{}]/DateStart: {}/DateEnt: {}/StateOutfit: {}/Descriptions: {}/Name: {}. ",
                  x + 1
                  , outfitList.get(x).getDateStart()
                  , outfitList.get(x).getDateEnt()
                  , outfitList.get(x).getStateOutfit()
                  , outfitList.get(x).getDescriptions()
                  , outfitList.get(x).getName());
            }


          } catch (EmptySearchException e) {
            log.warn("The search has not given any results. {}", e.getMessage());
          } catch (InputMismatchException e) {
            log.warn("Invalid data:{}. Please try again", e.getMessage());
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
}