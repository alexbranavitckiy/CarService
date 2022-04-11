package com.netcracker.controllers;


import com.netcracker.DTO.clients.ClientsDto;
import com.netcracker.DTO.response.ApiResponse;
import com.netcracker.DTO.response.ContactConfirmationResponse;
import com.netcracker.order.Orders;
import com.netcracker.security.UserRegister;
import com.netcracker.services.ClientServices;
import com.netcracker.services.OrderServices;
import com.netcracker.user.Clients;
import com.netcracker.user.RoleUser;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.Model;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.service.Representation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController(value = "/clients")
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
 @PostMapping(value = "/request/order", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
 public ResponseEntity<ContactConfirmationResponse> setOrderRequest(Orders order, Principal principal) {
  ContactConfirmationResponse contactConfirmationResponse = new ContactConfirmationResponse();
  orderServices.repairRequest(order, principal.getName());
  return ResponseEntity.ok(contactConfirmationResponse);
 }

 @GetMapping("/aut/getAll")
 @ApiOperation("Users list getting operation")
 public ResponseEntity<List<ClientsDto>> getAllClients() {
  return ResponseEntity.ok(clientServices.getAllClient());
 }

 @GetMapping("/aut/byName")
 @ApiOperation("Getting a user by name")
 public ResponseEntity<ApiResponse<ClientsDto>> getClientsByName(@RequestParam("name") String name) {
  ApiResponse<ClientsDto> apiResponse = new ApiResponse<>();
  Optional<ClientsDto> clients = clientServices.getClientsByName(name);
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

 @ApiOperation("Clients registration")
 @PostMapping(value = "/registration", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
 public ResponseEntity<ContactConfirmationResponse> createUser(Clients clients) {
  ContactConfirmationResponse contactConfirmationResponse = new ContactConfirmationResponse();
  contactConfirmationResponse.setResult(userRegister.registerNewUser(clients));
  return ResponseEntity.ok(contactConfirmationResponse);
 }

 @ApiOperation("Clients registration")
 @PostMapping(value = "/update", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
 public ResponseEntity<ContactConfirmationResponse> updateUser(Clients clients) {
  ContactConfirmationResponse contactConfirmationResponse = new ContactConfirmationResponse();
  contactConfirmationResponse.setResult(userRegister.registerNewUser(clients));
  return ResponseEntity.ok(contactConfirmationResponse);
 }

}
