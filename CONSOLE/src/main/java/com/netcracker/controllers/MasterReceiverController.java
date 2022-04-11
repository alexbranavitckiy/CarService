package com.netcracker.controllers;

import com.netcracker.DTO.response.ContactConfirmationResponse;
import com.netcracker.security.UserRegister;
import com.netcracker.services.ClientServices;
import com.netcracker.services.MasterServices;
import com.netcracker.services.OrderServices;
import com.netcracker.user.Clients;
import com.netcracker.user.Master;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/aut/masterReceiver")
@ApiOperation("API for receiver master")
public class MasterReceiverController {

 private final ClientServices clientServices;
 private final OrderServices orderServices;
 private final MasterServices masterServices;
 private final UserRegister userRegister;

 @Autowired
 MasterReceiverController(UserRegister userRegister,MasterServices masterServices, ClientServices clientServices, OrderServices orderServices) {
  this.clientServices = clientServices;
  this.userRegister=userRegister;
  this.masterServices = masterServices;
  this.orderServices = orderServices;
 }

 @ApiOperation("Master registration")
 @PostMapping(value = "MasterRegistration", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
 public ResponseEntity<ContactConfirmationResponse> createMaster(Master master) {
  ContactConfirmationResponse contactConfirmationResponse = new ContactConfirmationResponse();
 contactConfirmationResponse.setResult(userRegister.registerNewMaster(master));
  return ResponseEntity.ok(contactConfirmationResponse);
 }

 @ApiOperation("Master registration")
 @PostMapping(value = "masterReceiver", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
 public ResponseEntity<ContactConfirmationResponse> createMasterReceiver(Master master) {
  ContactConfirmationResponse contactConfirmationResponse = new ContactConfirmationResponse();
  contactConfirmationResponse.setResult(userRegister.registerNewMaster(master));
  return ResponseEntity.ok(contactConfirmationResponse);
 }


}
