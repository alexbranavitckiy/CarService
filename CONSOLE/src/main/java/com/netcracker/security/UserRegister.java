package com.netcracker.security;

import com.netcracker.services.ClientServices;
import com.netcracker.services.MasterReceiverServices;
import com.netcracker.services.MasterServices;
import com.netcracker.user.Clients;
import com.netcracker.user.Master;
import com.netcracker.user.MasterReceiver;
import com.netcracker.user.RoleUser;
import io.swagger.annotations.Tag;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@Hidden
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

 public boolean registerNewUser(Clients clients) {
  if (clientServices.getClientsByLogin(clients.getLogin()).isEmpty()) {
   clients.setPassword(passwordEncoder.encode(clients.getPassword()));
   clients.setId(UUID.randomUUID());
   clients.setRoleUser(RoleUser.REGISTERED);
   clientServices.addObjectInClient(clients);
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
