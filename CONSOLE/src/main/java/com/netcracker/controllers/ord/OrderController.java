package com.netcracker.controllers.ord;

import com.netcracker.DTO.ord.OrderDto;
import com.netcracker.annotations.ClientLabel;
import com.netcracker.annotations.RegistrationLabel;
import com.netcracker.order.State;
import com.netcracker.DTO.response.ContactConfirmationResponse;
import com.netcracker.order.Order;
import com.netcracker.security.jwt.JWTUtil;
import com.netcracker.services.OrderServices;
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

@RestController(value = "/aut/masterReceiver/order")
@ApiOperation("API for orders service")
@RegistrationLabel
public class OrderController {

 private final OrderServices orderServices;
 private final JWTUtil jwtUtil;

 @Autowired
 OrderController(JWTUtil jwtUtil, OrderServices orderServices) {
  this.jwtUtil = jwtUtil;
  this.orderServices = orderServices;
 }

 @ApiOperation("Creation of an order by the master receiver")
 @PostMapping(value = "/newOrder", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
 public ResponseEntity<ContactConfirmationResponse> addOrder(Order order) {
  ContactConfirmationResponse contactConfirmationResponse = new ContactConfirmationResponse();
  contactConfirmationResponse.setResult(String.valueOf(orderServices.addOrder(order)));
  return ResponseEntity.ok(contactConfirmationResponse);
 }

// @ApiOperation("Get user order with state")
// @PostMapping(value = "/getAll/{state}", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
// public ResponseEntity<List<OrderDto>> getAllOrderByState(@PathVariable State state, @RequestParam("nameClients") String loginClients) {
//  return ResponseEntity.ok(orderServices.getAllOrderClientsWithState(loginClients, state));
// }

 @ApiOperation("Update order")
 @PostMapping(value = "/updateOrder", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
 public ResponseEntity<ContactConfirmationResponse> updateOrder(Order order) {
  ContactConfirmationResponse contactConfirmationResponse = new ContactConfirmationResponse();
  contactConfirmationResponse.setResult(String.valueOf(orderServices.updateOrder(order)));
  return ResponseEntity.ok(contactConfirmationResponse);
 }

 @ApiOperation("Get an order by car ID")
 @PostMapping(value = "/byIdCar", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
 public ResponseEntity<List<Order>> getOrderByIdCar(@RequestParam("IdCar") UUID IdCar) {
  Optional<Order> order = orderServices.getOrderById(IdCar);//todo!!!
  if (order.isEmpty()) {
   return ResponseEntity.ok(new ArrayList<>());
  }
  return ResponseEntity.ok(List.of(order.get()));
 }

 @ClientLabel
 @GetMapping("person/request/getMyRequest")
 @ApiOperation("Get all requests of a logged in client")
 public ResponseEntity<List<OrderDto>> getClientsOnline(@ApiIgnore @CookieValue(name = "token", required = false) String token) {
  return ResponseEntity.ok(orderServices.getAllOrderClientsWithState(jwtUtil.extractUsername(token), State.REQUEST));
 }

 @ClientLabel
 @ApiOperation("Repair Request")
 @PostMapping(value = "/person/request/order", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces =
  MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<Boolean> setOrderRequest(@RequestBody OrderDto order, Principal principal) {
  return ResponseEntity.ok(orderServices.repairRequest(order, principal.getName()));
 }

 @ClientLabel
 @ApiOperation("Close request")
 @PostMapping(value = "/person/closeRequest/order", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces =
  MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<String> closeOrderRequest(@RequestBody UUID uuidOrder, Principal principal) {
  return ResponseEntity.ok(orderServices.cancelRequest(uuidOrder, principal.getName()));
 }


}
