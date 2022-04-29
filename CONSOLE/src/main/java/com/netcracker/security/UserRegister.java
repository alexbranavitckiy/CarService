package com.netcracker.security;

import com.netcracker.DTO.clients.ClientDto;
import com.netcracker.DTO.errs.SaveSearchErrorException;
import com.netcracker.DTO.response.ContactConfirmationPayload;
import com.netcracker.DTO.response.ContactConfirmationResponse;
import com.netcracker.security.jwt.JWTUtil;
import com.netcracker.services.ClientServices;
import com.netcracker.services.MasterReceiverServices;
import com.netcracker.services.MasterServices;
import com.netcracker.user.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class UserRegister {

 private final PasswordEncoder passwordEncoder;
 private final ClientServices clientServices;
 private final MasterServices masterServices;
 private final MasterReceiverServices masterReceiverServices;
 private final JWTUtil jwtUtil;
 private final AuthenticationManager authenticationManager;
 private final MyUserDetailsService myUserDetailsService;

 @Autowired
 public UserRegister(MyUserDetailsService myUserDetailsService, AuthenticationManager authenticationManager, JWTUtil jwtUtil, MasterReceiverServices masterReceiverServices, MasterServices masterServices, PasswordEncoder passwordEncoder, ClientServices clientServices) {
  this.passwordEncoder = passwordEncoder;
  this.myUserDetailsService = myUserDetailsService;
  this.authenticationManager = authenticationManager;
  this.jwtUtil = jwtUtil;
  this.masterServices = masterServices;
  this.clientServices = clientServices;
  this.masterReceiverServices = masterReceiverServices;
 }

 public ContactConfirmationResponse jwtLogin(ContactConfirmationPayload payload) {
  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(payload.getPassword(),
   payload.getLogin()));
  UserDetails userDetails = myUserDetailsService.loadUserByUsername(payload.getPassword());
  String jwtToken = jwtUtil.generateToken(userDetails);
  ContactConfirmationResponse response = new ContactConfirmationResponse();
  response.setResult(jwtToken);
  return response;
 }

 public String newDate(String password) {
  return passwordEncoder.encode(password);
 }

 public boolean registerNewUser(ClientDto client) throws SaveSearchErrorException {
  if (clientServices.getClientDtoByLogin(client.getLogin()).isEmpty()) {
   client.setPassword(passwordEncoder.encode(client.getPassword()));
   client.setId(UUID.randomUUID());
   client.setRoleUser(RoleUser.REGISTERED);
   clientServices.registrationClient(client);
   return true;
  }
  throw new SaveSearchErrorException("The login you entered is in use by other users");
 }

 public boolean registerNewMaster(Master master) {
  if (masterServices.getMasterByLogin(master.getLogin()).isEmpty()) {
   master.setRole(Role.MASTER);
   master.setId(UUID.randomUUID());
   master.setPassword(passwordEncoder.encode(master.getPassword()));
   return masterServices.addMaster(master);
  }
  return false;
 }

 public boolean registerNewMasterReceiver(MasterReceiver master) {
  if (masterReceiverServices.getMasterReceiverByLogin(master.getLogin()).isEmpty()) {
   master.setPassword(passwordEncoder.encode(master.getPassword()));
   master.setId(UUID.randomUUID());
   master.setRole(Role.RECEPTIONIST);
   return masterReceiverServices.addMaster(master);
  }
  return false;
 }


}
