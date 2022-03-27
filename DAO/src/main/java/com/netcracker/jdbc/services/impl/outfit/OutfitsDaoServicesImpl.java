package com.netcracker.jdbc.services.impl.outfit;

import com.netcracker.OutfitsServices;
import com.netcracker.errors.PersistException;
import com.netcracker.jdbc.services.CrudDao;
import com.netcracker.jdbc.services.impl.OutfitsDaoImpl;
import com.netcracker.outfit.Outfit;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
public class OutfitsDaoServicesImpl implements OutfitsServices {

 private final CrudDao<Outfit, UUID> crudServices = new OutfitsDaoImpl();

 @Override
 public List<Outfit> getAllOutfits() {
  try {
   return crudServices.getAll();
  } catch (PersistException p) {
   log.warn("Outfit error{}", p.getMessage());
  }
  return new ArrayList<>();
 }

 @Override
 public boolean addObjectInOutfits(Outfit o) {
  try {
   return this.crudServices.addObject(o);
  } catch (PersistException p) {
   log.warn("Outfit error{}", p.getMessage());
  }
  return false;
 }

 @Override
 public boolean updateOutfit(Outfit outfit) {
  try {
   return crudServices.update(outfit);
  } catch (PersistException p) {
   log.warn("Outfit error{}", p.getMessage());
  }
  return false;
 }

}
