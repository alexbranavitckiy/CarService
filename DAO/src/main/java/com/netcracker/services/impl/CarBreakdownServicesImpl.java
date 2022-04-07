package com.netcracker.services.impl;

import com.netcracker.breakdown.CarBreakdown;
import com.netcracker.repository.CarBreakdownRepository;
import com.netcracker.services.CarBreakdownServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class CarBreakdownServicesImpl implements CarBreakdownServices {

 private final CarBreakdownRepository breakdownRepository;

 @Autowired
 private CarBreakdownServicesImpl(CarBreakdownRepository breakdownRepository) {
  this.breakdownRepository = breakdownRepository;
 }

 @Override
 public List<CarBreakdown> getAllBreakdown() {
  return null;
 }

 @Override
 public boolean addBreakdown(CarBreakdown carBreakdown) {
  return false;
 }

 @Override
 public Optional<CarBreakdown> getBreakdownById(UUID uuid) {
  return Optional.empty();
 }

 @Override
 public boolean updateBreakdown(CarBreakdown carBreakdown) {
  return false;
 }

 @Override
 public List<CarBreakdown> getAllBreakdownByCar(UUID uuid) {
  return null;
 }
}
