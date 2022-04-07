package com.netcracker.services.impl;


import com.netcracker.repository.MasterReceiverRepository;
import com.netcracker.services.MasterReceiverServices;
import com.netcracker.user.MasterReceiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MasterReceiverServicesImpl implements MasterReceiverServices {

 private final MasterReceiverRepository masterReceiverRepository;

 @Autowired
 private MasterReceiverServicesImpl(MasterReceiverRepository masterReceiverRepository) {
  this.masterReceiverRepository = masterReceiverRepository;
 }

 @Override
 public boolean updateMaster(MasterReceiver masterReceiver) {
  return false;
 }

 @Override
 public boolean updateMasterAndSession(MasterReceiver masterReceiver) {
  return false;
 }

 @Override
 public boolean addMaster(MasterReceiver masterReceiver) {
  return false;
 }

 @Override
 public List<MasterReceiver> getAllMasterReceiver() {
  return null;
 }
}
