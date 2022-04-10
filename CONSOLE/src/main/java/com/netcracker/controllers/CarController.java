package com.netcracker.controllers;

import com.netcracker.services.CarServices;
import com.netcracker.services.ClientServices;
import com.netcracker.user.Clients;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class CarController {

 private final CarServices carServices;
 @Autowired
 private ClientServices clientServices;

 @Autowired
 private CarController(CarServices carServices) {
  this.carServices = carServices;
 }

 @GetMapping("/aut")
 public ResponseEntity<String> page() {
  return ResponseEntity.ok("page");
 }

 @GetMapping("/aut/profile")
 public ResponseEntity<String> page2() {
  return ResponseEntity.ok("clientServices.getClientsByName().get()");
 }
 @GetMapping("/aut/profiles")
 public ResponseEntity<String> page2s() {

  return ResponseEntity.ok("/aut/profiles");
 }

}
