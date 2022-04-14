package com.netcracker.controllers;


import com.netcracker.DTO.clients.ClientDto;
import com.netcracker.DTO.response.ContactConfirmationResponse;
import com.netcracker.order.Order;
import com.netcracker.security.UserRegister;
import com.netcracker.services.ClientServices;
import com.netcracker.services.OrderServices;
import com.netcracker.user.Client;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@ApiOperation("API for customer service")
public class ClientsController {

 private final ClientServices clientServices;
 private final UserRegister userRegister;
 private final OrderServices orderServices;

 @Autowired
 ClientsController(OrderServices orderServices, UserRegister userRegister, ClientServices clientServices) {
  this.userRegister = userRegister;
  this.orderServices = orderServices;
  this.clientServices = clientServices;
 }

 @ApiOperation("Repair Request")
 @PostMapping(value = "/clients/request/order", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
 public ResponseEntity<ContactConfirmationResponse> setOrderRequest(Order order, Principal principal) {
  ContactConfirmationResponse contactConfirmationResponse = new ContactConfirmationResponse();
  orderServices.repairRequest(order, principal.getName());
  return ResponseEntity.ok(contactConfirmationResponse);
 }

 @GetMapping("/aut/getAll")
 @ApiOperation("Users list getting operation")
 public ResponseEntity<List<ClientDto>> getAllClients() {
  return ResponseEntity.ok(clientServices.getAllClient());
 }

 @GetMapping("/aut/byName")
 @ApiOperation("Getting a user by name")
 public ResponseEntity<List<ClientDto>> getClientsByName(@RequestParam("name") String name) {
  Optional<ClientDto> client = clientServices.getClientsByName(name);
  if (client.isEmpty()) {
   return ResponseEntity.ok(new ArrayList<>());
  }
  return ResponseEntity.ok(List.of(client.get()));
 }

 @ApiOperation("Clients registration")
 @PostMapping(value = "/registration", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
 public ResponseEntity<ContactConfirmationResponse> createUser(Client clients) {
  ContactConfirmationResponse contactConfirmationResponse = new ContactConfirmationResponse();
  contactConfirmationResponse.setResult(userRegister.registerNewUser(clients));
  return ResponseEntity.ok(contactConfirmationResponse);
 }

 @ApiOperation("Updating a logged in client")
 @PostMapping(value = "/clients/update", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
 public ResponseEntity<ContactConfirmationResponse> updateUser(Client client,Principal principal) {
  ContactConfirmationResponse contactConfirmationResponse = new ContactConfirmationResponse();
  contactConfirmationResponse.setResult(clientServices.updateClientByLogin(client,principal.getName()));
  return ResponseEntity.ok(contactConfirmationResponse);
 }


 @GetMapping("/clients/getClients")
 @ApiOperation("Get the client logged in")
 public ResponseEntity<List<ClientDto>> getClientsOnline(Principal principal) {
  if (principal != null) {
   Optional<ClientDto> master = clientServices.getClientDtoByLogin(principal.getName());
   if (master.isPresent()) {
    return ResponseEntity.ok(List.of(master.get()));
   }
  }
  return ResponseEntity.ok(new ArrayList<>());
 }


}
