package com.netcracker.security;

import com.netcracker.PrincipalEntity;
import com.netcracker.services.ClientServices;
import com.netcracker.services.MasterReceiverServices;
import com.netcracker.services.MasterServices;
import com.netcracker.user.Clients;
import com.netcracker.user.Master;
import com.netcracker.user.MasterReceiver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserRegister {

 private final PasswordEncoder passwordEncoder;
 private final ClientServices clientServices;
 private final MasterServices masterServices;
 private final MasterReceiverServices masterReceiverServices;

 @Autowired
 public UserRegister(MasterReceiverServices masterReceiverServices, MasterServices masterServices, PasswordEncoder passwordEncoder, ClientServices clientServices) {
  this.passwordEncoder = passwordEncoder;
  this.masterServices = masterServices;
  this.clientServices = clientServices;
  this.masterReceiverServices = masterReceiverServices;
 }

 public boolean registerNewUser(PrincipalEntity clients) {
  if (clients instanceof Clients && clientServices.getClientsByLogin(clients.getLogin()).isEmpty()) {
   clients.setPassword(passwordEncoder.encode(clients.getPassword()));
   clientServices.addObjectInClient((Clients) clients);
   return true;
  }
  return false;
 }

 public boolean registerNewMaster(Master master) {
  if (masterServices.getMasterByLogin(master.getLogin()).isEmpty()) {
   master.setPassword(passwordEncoder.encode(master.getPassword()));
   return masterServices.addMaster(master);
  }
  return false;
 }

 public boolean registerNewMasterReceiver(MasterReceiver master) {
  if (masterReceiverServices.getMasterReceiverByLogin(master.getLogin()).isEmpty()) {
   master.setPassword(passwordEncoder.encode(master.getPassword()));
   return masterReceiverServices.addMaster(master);
  }
  return false;
 }


}
