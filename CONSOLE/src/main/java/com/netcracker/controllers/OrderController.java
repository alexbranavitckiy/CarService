package com.netcracker.controllers;

import com.netcracker.DTO.response.ContactConfirmationResponse;
import com.netcracker.order.Order;
import com.netcracker.services.OrderServices;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController(value = "/aut/masterReceiver/order")
@ApiOperation("API for orders service")
public class OrderController {

 private final OrderServices orderServices;

 @Autowired
 OrderController(OrderServices orderServices) {
  this.orderServices = orderServices;
 }

 @ApiOperation("Creation of an order by the master receiver")
 @PostMapping(value = "/newOrder", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
 public ResponseEntity<ContactConfirmationResponse> addOrder(Order order) {
  ContactConfirmationResponse contactConfirmationResponse = new ContactConfirmationResponse();
  contactConfirmationResponse.setResult(orderServices.addOrder(order));
  return ResponseEntity.ok(contactConfirmationResponse);
 }

 @ApiOperation("Get user order with state")
 @PostMapping(value = "/getAll/{state}", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
 public ResponseEntity<List<Order>> getAllOrderByState(@PathVariable String state, @RequestParam("nameClients") String loginClients) {
  return ResponseEntity.ok(orderServices.getAllOrderClientsWithState(loginClients, state));
 }

 @ApiOperation("Update order")
 @PostMapping(value = "/updateOrder", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
 public ResponseEntity<ContactConfirmationResponse> updateOrder(Order order) {
  ContactConfirmationResponse contactConfirmationResponse = new ContactConfirmationResponse();
  contactConfirmationResponse.setResult(orderServices.updateOrder(order));
  return ResponseEntity.ok(contactConfirmationResponse);
 }


 @ApiOperation("Get an order by car ID")
 @PostMapping(value = "/byIdCar", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
 public ResponseEntity<List<Order>> getOrderByIdCar(@RequestParam("IdCar") UUID IdCar) {
  Optional<Order> order = orderServices.getOrderById(IdCar);
  if (order.isEmpty()) {
   return ResponseEntity.ok(new ArrayList<>());
  }
  return ResponseEntity.ok(List.of(order.get()));
 }
}
