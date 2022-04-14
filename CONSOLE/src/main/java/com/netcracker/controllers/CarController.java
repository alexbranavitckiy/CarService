package com.netcracker.controllers;

import com.netcracker.DTO.response.ApiResponse;
import com.netcracker.DTO.response.ContactConfirmationResponse;
import com.netcracker.car.CarClient;
import com.netcracker.services.CarServices;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Slf4j
@RestController
@RequestMapping("/clients")
public class CarController {

 private final CarServices carServices;

 @Autowired
 private CarController(CarServices carServices) {
  this.carServices = carServices;
 }

 @ApiOperation("Get all the car of the logged in user")
 @GetMapping("/clients/getAllCar/car")
 public ResponseEntity<List<CarClient>> getAllCarByIdClients(Principal principal) {
  return ResponseEntity.ok(carServices.getCarByLoginClient(principal.getName()));
 }

 @ApiOperation("Get all car of a user")
 @GetMapping("/aut/masterReceiver/getAllCar/Car{ClientUUID}")
 public ResponseEntity<List<CarClient>> getAllCarByLoginClients(@PathVariable  UUID ClientUUID) {
  return ResponseEntity.ok(carServices.getCarByIdClient(ClientUUID));
 }

 @ApiOperation("Get a car by car ID")
 @GetMapping("/aut/masterReceiver/get/Car{CarUUID}")
 public ResponseEntity<List<CarClient>> getCarByIdCar(@PathVariable  UUID CarUUID) {
  Optional<CarClient> carClient = carServices.getCarByIdCar(CarUUID);
  if (carClient.isEmpty()) {
   return ResponseEntity.ok(new ArrayList<>());
  }
  return ResponseEntity.ok(List.of(carClient.get()));
 }

 @ApiOperation("Car registration")
 @PostMapping(value = "/clients/car/registration", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
 public ResponseEntity<ContactConfirmationResponse> createCar(CarClient carClient, Principal principal) {
  ContactConfirmationResponse contactConfirmationResponse = new ContactConfirmationResponse();
  contactConfirmationResponse.setResult(carServices.addCar(carClient, principal.getName()));
  return ResponseEntity.ok(contactConfirmationResponse);
 }

 @ApiOperation("Car registration")
 @PostMapping(value = "/clients/car/update", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
 public ResponseEntity<ContactConfirmationResponse> updateCar(CarClient carClient, Principal principal) {
  ContactConfirmationResponse contactConfirmationResponse = new ContactConfirmationResponse();
  contactConfirmationResponse.setResult(carServices.updateCarClient(carClient, principal.getName()));
  return ResponseEntity.ok(contactConfirmationResponse);
 }

 @ExceptionHandler(MissingServletRequestParameterException.class)
 public ResponseEntity<ApiResponse<CarClient>> handleMissingServletRequestParameterException(Exception exception) {
  return new ResponseEntity<>(new ApiResponse<CarClient>(HttpStatus.BAD_REQUEST, "Missing required parameters",
   exception), HttpStatus.BAD_REQUEST);
 }


}
