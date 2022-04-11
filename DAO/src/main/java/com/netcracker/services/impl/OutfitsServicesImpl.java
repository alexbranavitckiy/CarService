package com.netcracker.services.impl;


import com.netcracker.outfit.Outfit;
import com.netcracker.repository.OutfitsRepository;
import com.netcracker.services.OutfitsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OutfitsServicesImpl implements  OutfitsServices{

 private final OutfitsRepository outfitsRepository;


 @Autowired
 private OutfitsServicesImpl(OutfitsRepository outfitsRepository) {
  this.outfitsRepository = outfitsRepository;
 }

 @Override
 public List<Outfit> getAllOutfits() {
  return outfitsRepository.findAllBy();
 }

 @Override
 public boolean addObjectInOutfits(Outfit o) {
   outfitsRepository.save(o);
  return true;
 }

 @Override
 public boolean updateOutfit(Outfit outfit) {
  outfitsRepository.save(outfit);
  return true;
 }

 @Override
 public List<Outfit> getAllOutfitsAndSortingByData(Date dateStart) {
  return outfitsRepository.getOutfitsOrderByDateStart(dateStart);
 }

 @Override
 public List<Outfit> getAllOutfitsByData(String start, String end) {
  return null;
 }

 @Override
 public List<Outfit> getAllByMaster(UUID uuidMaster) {
  return null;
 }

 @Override
 public List<Outfit> getAllByMasterAndState(UUID uuidMaster, UUID state) {
  return null;
 }

 @Override
 public List<Outfit> getAllByState(UUID state) {
  return null;
 }
}
