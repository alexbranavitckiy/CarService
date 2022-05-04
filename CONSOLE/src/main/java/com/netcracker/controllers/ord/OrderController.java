package com.netcracker.controllers.ord;

import com.fasterxml.jackson.annotation.JsonView;
import com.netcracker.DTO.errs.SaveSearchErrorException;
import com.netcracker.DTO.ord.OrderDto;
import com.netcracker.DTO.ord.OrderForm;
import com.netcracker.DTO.ord.ValidateOrd;
import com.netcracker.DTO.response.ValidationErrorResponse;
import com.netcracker.DTO.response.Violation;
import com.netcracker.annotations.ClientLabel;
import com.netcracker.annotations.RegistrationLabel;
import com.netcracker.order.State;
import com.netcracker.services.OrderServices;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@ApiOperation("API for orders service")
@RegistrationLabel
public class OrderController {

 private final OrderServices orderServices;

 @Autowired
 OrderController(OrderServices orderServices) {
  this.orderServices = orderServices;
 }

 @ClientLabel
 @GetMapping("person/order-request/getAll")
 @JsonView(ValidateOrd.Details.class)
 @ApiOperation("Get all requests of a logged in client")
 public ResponseEntity<List<OrderDto>> getOrderOnClient(@Validated @ApiIgnore Principal principal) throws SaveSearchErrorException {
  return ResponseEntity.ok(orderServices.getAllOrderClientsWithState(principal.getName(), State.REQUEST));
 }

 @ClientLabel
 @ApiOperation("Create an order for a customer")
 @PostMapping(value = "person/order-request/newOrder", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
  MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<ValidationErrorResponse> addOrder(@Validated({ValidateOrd.New.class}) @RequestBody @JsonView(ValidateOrd.New.class)
                                                          OrderDto orderDto, @ApiIgnore Principal principal)
  throws SaveSearchErrorException {
  ValidationErrorResponse validationResponse = new ValidationErrorResponse();
  orderServices.addOrderOnClient(orderDto, principal.getName());
  validationResponse.setViolations(List.of(new Violation("true", "Request sent successfully")));
  return ResponseEntity.ok(validationResponse);
 }

 @ClientLabel
 @ApiOperation("Close request")
 @PutMapping(value = "/person/order-close", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
  MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<ValidationErrorResponse> closeOrderRequest(@Validated({ValidateOrd.EditValue.class, ValidateOrd.EditValue.class}) @RequestBody @JsonView(ValidateOrd.EditValue.class)
                                                                   OrderDto orderDto, @ApiIgnore Principal principal) throws SaveSearchErrorException {
  ValidationErrorResponse validationResponse = new ValidationErrorResponse();
  orderServices.cancelRequest(orderDto.getId(), principal.getName());
  validationResponse.setViolations(List.of(new Violation("true", "Request canceled successfully")));
  return ResponseEntity.ok(validationResponse);
 }


 @ApiOperation("Create of an order by the master receiver")
 @PostMapping(value = "/details/new-order", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
  MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<UUID> addOrderOnMaster(@Validated({ValidateOrd.NewAdmin.class}) @RequestBody @JsonView(ValidateOrd.NewAdmin.class)
                                                OrderForm orderDto, @ApiIgnore Principal principal)
  throws SaveSearchErrorException {
  return ResponseEntity.ok(orderServices.addOrderOnMaster(orderDto, principal.getName()));
 }


 @GetMapping("/details/order-request/getAll")
 @JsonView(ValidateOrd.Details.class)
 @ApiOperation("Get a list of all created orders")
 public ResponseEntity<List<OrderDto>> getOrderOnMasterStateByCreated(@RequestParam State state , @Validated @ApiIgnore Principal principal) throws SaveSearchErrorException {
  return ResponseEntity.ok(orderServices.getAllOrderWithStateOnMaster(principal.getName(), state));
 }


// @ApiOperation("Get user order with state")
// @PostMapping(value = "/getAll/{state}", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
// public ResponseEntity<List<OrderDto>> getAllOrderByState(@PathVariable State state, @RequestParam("nameClients") String loginClients) {
//  return ResponseEntity.ok(orderServices.getAllOrderClientsWithState(loginClients, state));
// }

 // @ApiOperation("Update order")
 // @PostMapping(value = "/updateOrder", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
 // public ResponseEntity<ContactConfirmationResponse> updateOrder(Order order) {
 //  ContactConfirmationResponse contactConfirmationResponse = new ContactConfirmationResponse();
 //  contactConfirmationResponse.setResult(String.valueOf(orderServices.updateOrder(order)));
 //  return ResponseEntity.ok(contactConfirmationResponse);
 // }

 // @ApiOperation("Get an order by car ID")
 // @PostMapping(value = "/byIdCar", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
 // public ResponseEntity<List<Order>> getOrderByIdCar(@RequestParam("IdCar") UUID IdCar) {
 //  Optional<Order> order = orderServices.getOrderById(IdCar);//todo!!!
 //  if (order.isEmpty()) {
 //   return ResponseEntity.ok(new ArrayList<>());
 //  }
 //  return ResponseEntity.ok(List.of(order.get()));
 // }


}
