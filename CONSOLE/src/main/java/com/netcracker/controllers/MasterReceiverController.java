package com.netcracker.controllers;

import com.netcracker.DTO.response.ApiResponse;
import com.netcracker.DTO.response.ContactConfirmationResponse;
import com.netcracker.DTO.response.EntityResponse;
import com.netcracker.security.UserRegister;
import com.netcracker.services.MasterServices;
import com.netcracker.user.Master;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.LocalDateTime;
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
 public ResponseEntity<ApiResponse<Master>> getAllMaster() {
  ApiResponse<Master> apiResponse = new ApiResponse<>();
  List<Master> clients = masterServices.getAllMaster();
  apiResponse.setDebugMessage("successful request");
  apiResponse.setMessage("date size:" + clients.size() + "elements");
  apiResponse.setDate(clients);
  apiResponse.setHttpStatus(HttpStatus.OK);
  apiResponse.setLocalDateTime(LocalDateTime.now());
  return ResponseEntity.ok(apiResponse);
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
 public ResponseEntity<EntityResponse<Master>> getMasterById(Principal principal) {
  EntityResponse<Master> masterEntityResponse = new EntityResponse<>();
  Optional<Master> clients = masterServices.getMasterByLogin(principal.getName());
  if (clients.isPresent()) {
   masterEntityResponse.setMessage("date size:1 elements");
   masterEntityResponse.setDate(List.of(clients.get()));
  } else {
   masterEntityResponse.setMessage("date size:0 elements");
   masterEntityResponse.setDate(new ArrayList<>());
  }
  masterEntityResponse.setHttpStatus(HttpStatus.OK);
  masterEntityResponse.setDebugMessage("successful request");
  masterEntityResponse.setLocalDateTime(LocalDateTime.now());
  return ResponseEntity.ok(masterEntityResponse);
 }


 @GetMapping("/getMaster/id")
 @ApiOperation("Get an ID master")
 public ResponseEntity<EntityResponse<Master>> getMasterById(@RequestParam("Id") UUID Id) {
  EntityResponse<Master> masterEntityResponse = new EntityResponse<>();
  Optional<Master> clients = masterServices.getMasterById(Id);
  if (clients.isPresent()) {
   masterEntityResponse.setMessage("date size:1 elements");
   masterEntityResponse.setDate(List.of(clients.get()));
  } else {
   masterEntityResponse.setMessage("date size:0 elements");
   masterEntityResponse.setDate(new ArrayList<>());
  }
  masterEntityResponse.setHttpStatus(HttpStatus.OK);
  masterEntityResponse.setDebugMessage("successful request");
  masterEntityResponse.setLocalDateTime(LocalDateTime.now());
  return ResponseEntity.ok(masterEntityResponse);
 }

}
