package com.netcracker.services.impl;


import com.netcracker.repository.MasterRepository;
import com.netcracker.services.MasterServices;
import com.netcracker.user.Master;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MasterServicesImpl implements MasterServices {

 private final MasterRepository masterRepository;


 @Autowired
 private MasterServicesImpl(MasterRepository masterRepository) {
  this.masterRepository = masterRepository;
 }

 @Override
 public List<Master> getAllMaster() {
  return null;
 }

 @Override
 public boolean addMaster(Master master) {
  return false;
 }

 @Override
 public List<Master> getMasterByLogin(String name) {
  return null;
 }

 @Override
 public Optional<Master> getMasterById(UUID master) {
  return Optional.empty();
 }

 @Override
 public boolean updateMaster(Master master) {
  return false;
 }
}
