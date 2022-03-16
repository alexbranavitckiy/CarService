package com.netcracker.menu.edit;

import com.netcracker.menu.Menu;
import com.netcracker.menu.validator.ValidatorInstruments;
import com.netcracker.menu.validator.ValidatorInstrumentsImpl;
import com.netcracker.outfit.Outfit;
import java.io.IOException;
import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EditOutfit implements Menu {

  private final Outfit outfit;
  private final ValidatorInstruments validator = new ValidatorInstrumentsImpl();

  @Override
  public void preMessage(String parentsName) {
    log.info("Enter 1 {}", parentsName);
  }

  public EditOutfit(Outfit outfit) {
    this.outfit = outfit;
  }

  @Override
  public void run(Scanner in, String parentsName) throws IOException {
    log.info("Descriptions");
    if (validator.edit(this.outfit.getDescriptions(), in)) {
      this.outfit.setDescriptions(validator.validateDescription(in));
    }
    log.info("Enter name outfit");
    if (validator.edit(this.outfit.getName(), in)) {
      this.outfit.setName(validator.validateNameOutfit(in));
    }
    log.info("Enter state outfit");
    if (validator.edit(this.outfit.getStateOutfit().toString(), in)) {
      this.outfit.setStateOutfit(validator.stateOutfit(in));
    }
    log.info("Enter price outfit");
    if (validator.edit(String.valueOf(this.outfit.getPrice()), in)) {
      this.outfit.setPrice(validator.validatePrice(in));
    }
  }

  public Outfit getOutfit() {
    return outfit;
  }
}