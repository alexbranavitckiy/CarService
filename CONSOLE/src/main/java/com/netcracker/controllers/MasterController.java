package com.netcracker.controllers;

import com.netcracker.services.MasterServices;
import com.netcracker.user.Master;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
 public ResponseEntity<List<Master>> getMasterByOnline(Principal principal) {
  Optional<Master> master=masterServices.getMasterByLogin(principal.getName());
  if (master.isEmpty()) {
   return ResponseEntity.ok(new ArrayList<>());
  }
  return ResponseEntity.ok(List.of(master.get()));
 }


}
