package com.netcracker.controllers;

import com.netcracker.DTO.clients.ClientsDto;
import com.netcracker.DTO.response.ApiResponse;
import com.netcracker.DTO.response.ContactConfirmationResponse;
import com.netcracker.order.Orders;
import com.netcracker.services.OrderServices;
import com.netcracker.user.Clients;
import com.netcracker.user.Master;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.Order;
import java.time.LocalDateTime;
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
 public ResponseEntity<ContactConfirmationResponse> addOrder(Orders order) {
  ContactConfirmationResponse contactConfirmationResponse = new ContactConfirmationResponse();
  contactConfirmationResponse.setResult(orderServices.addOrder(order));
  return ResponseEntity.ok(contactConfirmationResponse);
 }

 @ApiOperation("Get user order with state")
 @PostMapping(value = "/getAll/{state}", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
 public  ResponseEntity<ApiResponse<Order>> getAllOrderByState(@PathVariable String state, @RequestParam("nameClients") String loginClients) {
  ApiResponse<Order> apiResponse = new ApiResponse<>();
  List<Order> orders = orderServices.getAllOrderClientsWithState(loginClients, state);
  apiResponse.setDebugMessage("successful request");
  apiResponse.setMessage("date size:" + orders.size() + " elements");
  apiResponse.setDate(orders);
  apiResponse.setHttpStatus(HttpStatus.OK);
  apiResponse.setLocalDateTime(LocalDateTime.now());
  return ResponseEntity.ok(apiResponse);
 }

 @ApiOperation("Update order")
 @PostMapping(value = "/updateOrder", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
 public ResponseEntity<ContactConfirmationResponse> updateOrder(Orders order) {
  ContactConfirmationResponse contactConfirmationResponse = new ContactConfirmationResponse();
  contactConfirmationResponse.setResult(orderServices.updateOrder(order));
  return ResponseEntity.ok(contactConfirmationResponse);
 }


 @ApiOperation("Get an order by car ID")
 @PostMapping(value = "/byIdCar", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
 public ResponseEntity<ApiResponse<Orders>>  getOrderByIdCar(@RequestParam("IdCar") UUID IdCar) {
  ApiResponse<Orders> apiResponse = new ApiResponse<>();
  Optional<Orders> clients = orderServices.getOrderById(IdCar);
  if (clients.isPresent()) {
   apiResponse.setMessage("date size:1 elements");
   apiResponse.setDate(List.of(clients.get()));
  } else {
   apiResponse.setMessage("date size:0 elements");
   apiResponse.setDate(new ArrayList<>());
  }
  apiResponse.setDebugMessage("successful request");
  apiResponse.setHttpStatus(HttpStatus.OK);
  apiResponse.setLocalDateTime(LocalDateTime.now());
  return ResponseEntity.ok(apiResponse);
 }
}
