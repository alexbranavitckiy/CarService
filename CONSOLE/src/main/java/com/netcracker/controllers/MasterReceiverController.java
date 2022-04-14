package com.netcracker.controllers;

import com.netcracker.DTO.response.ContactConfirmationResponse;
import com.netcracker.security.UserRegister;
import com.netcracker.services.MasterServices;
import com.netcracker.user.Master;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController(value = "/aut/masterReceiver")
@ApiOperation("API for receiver master")
public class MasterReceiverController {

 private final MasterServices masterServices;
 private final UserRegister userRegister;

 @Autowired
 MasterReceiverController(UserRegister userRegister, MasterServices masterServices) {
  this.userRegister = userRegister;
  this.masterServices = masterServices;
 }

 @ApiOperation("Master registration")
 @PostMapping(value = "/MasterRegistration", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
 public ResponseEntity<ContactConfirmationResponse> createMaster(Master master) {
  ContactConfirmationResponse contactConfirmationResponse = new ContactConfirmationResponse();
  contactConfirmationResponse.setResult(userRegister.registerNewMaster(master));
  return ResponseEntity.ok(contactConfirmationResponse);
 }

 @ApiOperation("Master registration")
 @PostMapping(value = "/masterReceiver/create", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
 public ResponseEntity<ContactConfirmationResponse> createMasterReceiver(Master master) {
  ContactConfirmationResponse contactConfirmationResponse = new ContactConfirmationResponse();
  contactConfirmationResponse.setResult(userRegister.registerNewMaster(master));
  return ResponseEntity.ok(contactConfirmationResponse);
 }

 @GetMapping("/getAllMaster")
 @ApiOperation("Get a list of all masters")
 public ResponseEntity<List<Master>> getAllMaster() {
  return ResponseEntity.ok(masterServices.getAllMaster());
 }

 @ApiOperation("Master update")
 @PostMapping(value = "/masterReceiver", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
 public ResponseEntity<ContactConfirmationResponse> updateMaster(Master master) {
  ContactConfirmationResponse contactConfirmationResponse = new ContactConfirmationResponse();
  contactConfirmationResponse.setResult(masterServices.updateMaster(master));
  return ResponseEntity.ok(contactConfirmationResponse);
 }

 @GetMapping("/getMaster/online")
 @ApiOperation("Get an ID master")
 public ResponseEntity<List<Master>> getMasterById(Principal principal) {
  Optional<Master> master =masterServices.getMasterByLogin(principal.getName());
  if (master.isEmpty()) {
   return ResponseEntity.ok(new ArrayList<>());
  }
  return ResponseEntity.ok(List.of(master.get()));
 }

 @GetMapping("/getMaster/id")
 @ApiOperation("Get an ID master")
 public ResponseEntity<List<Master>> getMasterById(@RequestParam("Id") UUID Id) {
  Optional<Master> master =masterServices.getMasterById(Id);
  if (master.isEmpty()) {
   return ResponseEntity.ok(new ArrayList<>());
  }
  return ResponseEntity.ok(List.of(master.get()));
 }

}
