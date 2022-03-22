package com.netcracker.menu.order.outfit;

import com.netcracker.errors.EmptySearchException;
import com.netcracker.menu.Menu;
import com.netcracker.outfit.Outfit;
import com.netcracker.file.services.impl.outfit.OutfitsServicesImpl;
import com.netcracker.OutfitsServices;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class ListOutfit implements Menu {

  private final OutfitsServices outfitsServices = new OutfitsServicesImpl();
  private Outfit outfit;


  @Override
  public void preMessage(String parentsName) {
    log.info("Enter 1: {}", parentsName);
    log.info("Enter 2 Display outfits.");
  }

  @Override
  public void run(Scanner in, String parentsName) throws IOException {
    List<Outfit> outfitList;
    this.preMessage(parentsName);
    label:
    while (true) {
      switch (in.next()) {
        case "2": {
          try {
            outfitList = outfitsServices.getAllOutfits();
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

  public Optional<Outfit> getOutfit() {
    return Optional.of(this.outfit);
  }
}