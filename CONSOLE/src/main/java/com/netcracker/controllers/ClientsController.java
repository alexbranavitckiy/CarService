package com.netcracker.controllers;


import com.netcracker.DTO.ApiResponse;
import com.netcracker.services.ClientServices;
import com.netcracker.user.Clients;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/aut")
public class ClientsController {

 private final ClientServices clientServices;

 @Autowired
 ClientsController(ClientServices clientServices) {
  this.clientServices = clientServices;
 }

 @GetMapping("/clients/getAll")
 @ApiOperation("Users list getting operation")
 public ResponseEntity<List<Clients>> getAllClients() {
  return ResponseEntity.ok(clientServices.getAllClient());
 }

 @GetMapping("/clients/byName")
 @ApiOperation("Getting a user by name")
 public ResponseEntity<ApiResponse<Clients>> getClientsByName(@RequestParam("name") String name) {
  ApiResponse<Clients> apiResponse = new ApiResponse<>();
  Optional<Clients> clients = clientServices.getClientsByName(name);
  apiResponse.setDebugMessage("successful request");
  if (clients.isPresent()) {
   apiResponse.setMessage("date size:1 elements");
   apiResponse.setDate(List.of(clients.get()));
  } else {
   apiResponse.setMessage("date size:0 elements");
   apiResponse.setDate(new ArrayList<>());
  }
  apiResponse.setHttpStatus(HttpStatus.OK);
  apiResponse.setLocalDateTime(LocalDateTime.now());
  return ResponseEntity.ok(apiResponse);
 }

}
