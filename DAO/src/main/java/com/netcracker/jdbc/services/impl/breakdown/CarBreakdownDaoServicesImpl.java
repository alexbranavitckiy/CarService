package com.netcracker.jdbc.services.impl.breakdown;


import com.netcracker.CarBreakdownServices;
import com.netcracker.breakdown.CarBreakdown;
import com.netcracker.errors.EmptySearchException;
import com.netcracker.errors.PersistException;
import com.netcracker.jdbc.services.CarBreakdownDao;
import com.netcracker.jdbc.services.impl.CarBreakdownDaoImpl;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
public class CarBreakdownDaoServicesImpl implements CarBreakdownServices {

 private final CarBreakdownDao carDao = new CarBreakdownDaoImpl();

 @Override
 public List<CarBreakdown> getAllBreakdown() {
  try {
   return carDao.getAll();
  } catch (PersistException p) {
   log.warn("Error getting data car:{}", p.getMessage());
  }
  return new ArrayList<>();
 }

 @Override
 public boolean addBreakdown(CarBreakdown carBreakdown) {
  try {
   return this.carDao.addObject(carBreakdown);
  } catch (PersistException p) {
   log.warn("Error getting data car:{}", p.getMessage());
  }
  return false;
 }

 @Override
 public Optional<CarBreakdown> getBreakdownById(UUID uuid) {
  try {
   return this.carDao.getById(uuid);
  } catch (PersistException p) {
   log.warn("Error getting data car:{}", p.getMessage());
  }
  return Optional.empty();
 }

 @Override
 public boolean updateBreakdown(CarBreakdown carBreakdown) {
  try {
   return this.carDao.update(carBreakdown);
  } catch (PersistException p) {
   log.warn("Error getting data car:{}", p.getMessage());
  }
  return false;
 }

 @Override
 public List<CarBreakdown> getAllBreakdownByCar(UUID uuid) {
  try {
   return this.carDao.getAllByQuery(carDao.getAllCarBreakdownDaoById_Car(), uuid.toString());
  } catch (PersistException p) {
   log.warn("Error getting data car:{}", p.getMessage());
  }
  return new ArrayList<>();
 }
}
