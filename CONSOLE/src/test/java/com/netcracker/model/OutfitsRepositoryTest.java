package com.netcracker.model;

import com.netcracker.CarServiceApplication;
import com.netcracker.repository.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CarServiceApplication.class)
@Transactional
public class OutfitsRepositoryTest {

 private final OutfitsRepository outfitsRepository;

 @Autowired
 OutfitsRepositoryTest(OutfitsRepository outfitsRepository) {
  this.outfitsRepository = outfitsRepository;
 }


 @Test
 void getAllByStateOutfitAndMasterLogin() {
  Assertions.assertNotNull(outfitsRepository.getAllByStateOutfitAndMasterLogin("", ""));
 }

 @Test
 void startWorkMaster() {
  outfitsRepository.startWorkMaster("", UUID.randomUUID(), "");
 }

 @Test
 void checkingOutfit() {
  outfitsRepository.checkingOutfit("");
 }

 @Test
 void endWorkMaster() {
  outfitsRepository.endWorkMaster("", "");
 }

 @Test
 void updateWorkMaster() {
  outfitsRepository.updateWorkMaster(new Date(), new Date(), "", "", "", "");
 }

}
