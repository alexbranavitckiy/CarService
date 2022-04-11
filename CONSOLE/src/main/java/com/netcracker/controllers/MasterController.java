package com.netcracker.controllers;


import com.netcracker.DTO.response.EntityResponse;
import com.netcracker.services.MasterServices;
import com.netcracker.user.Master;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController(value = "/aut/master")
@ApiOperation("API for orders service")
public class MasterController {

 private final MasterServices masterServices;

 @Autowired
 public MasterController(MasterServices masterServices) {
  this.masterServices = masterServices;
 }

 @GetMapping("/getMaster")
 @ApiOperation("Get the master logged in")
 public ResponseEntity<EntityResponse<Master>> getMasterByOnline(Principal principal) {
  EntityResponse<Master> masterEntityResponse = new EntityResponse<>();
  Optional<Master> master = masterServices.getMasterByLogin(principal.getName());
  if (master.isEmpty()) {
   masterEntityResponse.setMessage("date size:0 elements");
   masterEntityResponse.setDate(new ArrayList<>());
  } else {
   masterEntityResponse.setMessage("date size:1 elements");
   masterEntityResponse.setDate(List.of(master.get()));
  }
  masterEntityResponse.setHttpStatus(HttpStatus.OK);
  masterEntityResponse.setDebugMessage("successful request");
  masterEntityResponse.setLocalDateTime(LocalDateTime.now());
  return ResponseEntity.ok(masterEntityResponse);
 }


}
