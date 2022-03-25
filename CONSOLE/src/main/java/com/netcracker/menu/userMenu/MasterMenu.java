package com.netcracker.menu.userMenu;

import com.netcracker.errors.EmptySearchException;
import com.netcracker.factory.ServicesFactory;
import com.netcracker.menu.Menu;
import com.netcracker.menu.edit.EditOutfit;
import com.netcracker.menu.validator.ValidatorInstruments;
import com.netcracker.menu.validator.ValidatorInstrumentsImpl;
import com.netcracker.outfit.Outfit;
import com.netcracker.OutfitsServices;
import com.netcracker.session.UserSession;

import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Scanner;

@Slf4j
public class MasterMenu implements Menu {

    private final OutfitsServices outfitsServices;
    private final ValidatorInstruments validator = new ValidatorInstrumentsImpl();

    public MasterMenu(ServicesFactory servicesFactory) {
        this.outfitsServices = servicesFactory.getOutfitServices();
    }

    @Override
    public void preMessage(String nameMenu) {
        log.info("Enter 1 {}", nameMenu);
        log.info("Enter 2 list of current outfits");
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
                    try {
                        List<Outfit> outfitList = outfitsServices.getAllOutfits().stream()
                                .filter(x -> {
                                    if (x != null) {
                                        return x.getEmployer().equals(
                                                UserSession.getMasterSession().get().getId());
                                    }
                                    return false;
                                }).collect(Collectors.toList());
                        if (outfitList.size() > 0) {
                            for (int x = 0; x < outfitList.size(); x++) {
                                log.info(
                                        "Id[{}]/DateStart: {}/DateEnt: {}/StateOutfit: {}/Descriptions: {}/Name: {}/Price:{}. ",
                                        x + 1
                                        , outfitList.get(x).getDateStart()
                                        , outfitList.get(x).getDateEnt()
                                        , outfitList.get(x).getStateOutfit()
                                        , outfitList.get(x).getDescriptions()
                                        , outfitList.get(x).getName()
                                        , outfitList.get(x).getPrice());
                            }
                            log.info("Proceed to order? Enter 1-yeas/ 2-no");
                            if (in.next().equalsIgnoreCase("2")) {
                                log.info("Enter Order ID");
                                EditOutfit editOutfit = new EditOutfit(outfitList.get(in.nextInt()));
                                editOutfit.run(in, "");
                                validator.successfullyMessages(
                                        outfitsServices.updateOutfit(editOutfit.getOutfit()));
                            }
                        } else {
                            log.info("No existing outfits");
                            break;
                        }
                    } catch (EmptySearchException e) {
                        log.info("{}", e.getMessage());
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


