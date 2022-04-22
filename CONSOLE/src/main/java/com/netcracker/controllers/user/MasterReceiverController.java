package com.netcracker.controllers.user;

import com.netcracker.DTO.clients.MasterDto;

import com.netcracker.annotations.SwaggerLabelMasterReceiver;
import com.netcracker.security.UserRegister;
import com.netcracker.services.MasterReceiverServices;
import com.netcracker.user.Master;
import com.netcracker.user.MasterReceiver;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@ApiOperation("API for receiver MasterReceiver")
public class MasterReceiverController {

 private final MasterReceiverServices masterServices;
 private final UserRegister userRegister;

 @Autowired
 MasterReceiverController(UserRegister userRegister, MasterReceiverServices masterServices) {
  this.userRegister = userRegister;
  this.masterServices = masterServices;
 }

 @SwaggerLabelMasterReceiver
 @ApiOperation("MasterReceiver registration")
 @PostMapping(value = "/pivot/details/masterReceiver/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
  MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<Boolean> createMasterReceiver(@RequestBody MasterReceiver master) {
  return ResponseEntity.ok(userRegister.registerNewMasterReceiver(master));
 }

 @SwaggerLabelMasterReceiver
 @ApiOperation("Get the details of the master receiver logged in")
 @GetMapping("/pivot/details/getMasterReceiver/online")
 public ResponseEntity<List<MasterDto>> getMasterById(@ApiIgnore Principal principal) {
  Optional<MasterDto> master = masterServices.getMasterDtoByLogin(principal.getName());
  if (master.isEmpty()) {
   return ResponseEntity.ok(new ArrayList<>());
  }
  return ResponseEntity.ok(List.of(master.get()));
 }


 @GetMapping("/aut/details/getMaster/id")
 @ApiOperation("Get an ID master")
 public ResponseEntity<List<MasterDto>> getMasterById(@RequestParam("Id") UUID Id) {
  Optional<MasterDto> master = masterServices.getMasterReceiverById(Id);
  if (master.isEmpty()) {
   return ResponseEntity.ok(new ArrayList<>());
  }
  return ResponseEntity.ok(List.of(master.get()));
 }



}
