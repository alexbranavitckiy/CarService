package com.netcracker.controllers.car;

import com.fasterxml.jackson.annotation.JsonView;
import com.netcracker.DTO.Validate;
import com.netcracker.DTO.car.CarClientDto;
import com.netcracker.DTO.errs.SaveErrorException;
import com.netcracker.DTO.response.ValidationErrorResponse;
import com.netcracker.DTO.response.Violation;
import com.netcracker.annotations.ClientLabel;
import com.netcracker.DTO.response.ContactConfirmationResponse;
import com.netcracker.car.CarClient;
import com.netcracker.services.CarServices;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Slf4j
@RestController
@RequestMapping
@ApiOperation("API for customer cars")
public class CarController {

 private final CarServices carServices;

 @Autowired
 public CarController(CarServices carServices) {
  this.carServices = carServices;
 }

 @ClientLabel
 @ApiOperation("Get all the car of the logged in user")
 @GetMapping("/person/garage/getAll")
 public ResponseEntity<List<CarClientDto>> getAllCarByIdClients(@ApiIgnore Principal principal) {
  return ResponseEntity.ok(carServices.getCarByLoginClient(principal.getName()));
 }

 @ClientLabel
 @ApiOperation("Car registration")
 @ApiResponses(value = {
  @io.swagger.annotations.ApiResponse(code = 200, message = "The machine is successfully created", response = ValidationErrorResponse.class, responseContainer = "List"),
  @ApiResponse(code = 400, message = "Invalid input", response = ValidationErrorResponse.class, responseContainer = "List")})
 @PostMapping(value = "/person/garage/registration")
 public ResponseEntity<ValidationErrorResponse> createCar(@Validated({Validate.New.class,Validate.UiCrossFieldChecks.class}) @JsonView(Validate.Edit.class) @RequestBody CarClientDto carClient, @ApiIgnore Principal principal) {
  ValidationErrorResponse validationResponse = new ValidationErrorResponse();
  try {
   carServices.createCarOnClient(carClient, principal.getName());
   validationResponse.setViolations(List.of(new Violation("true", "Updates successfully committed")));
  } catch (SaveErrorException s) {
   validationResponse.setViolations(List.of(new Violation("false", s.getMessage())));
  }
  return ResponseEntity.ok(validationResponse);
 }

 @ClientLabel
 @ApiOperation("Get all car of a user")
 @GetMapping("/person/getAllCar/Car{ClientUUID}")
 public ResponseEntity<List<CarClientDto>> getAllCarByLoginClients(@PathVariable UUID ClientUUID, @ApiIgnore Principal principal) {
  return ResponseEntity.ok(carServices.getCarByIdClientOnClient(ClientUUID, principal.getName()));
 }

 @ClientLabel
 @ApiOperation("Get machine of user logged in by id")
 @GetMapping("/person/Car{CarUUID}")
 public ResponseEntity<List<CarClientDto>> getCarByIdCar(@PathVariable UUID CarUUID, Principal principal) {
  Optional<CarClientDto> carClient = carServices.getCarByIdCarOnClient(CarUUID, principal.getName());
  if (carClient.isEmpty()) {
   return ResponseEntity.ok(new ArrayList<>());
  }
  return ResponseEntity.ok(List.of(carClient.get()));
 }

 @ClientLabel
 @ApiOperation("Car update")
 @PostMapping(value = "/person/car/update", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
 public ResponseEntity<ContactConfirmationResponse> updateCar(CarClientDto carClient, Principal principal) {
  ContactConfirmationResponse contactConfirmationResponse = new ContactConfirmationResponse();
  contactConfirmationResponse.setResult(String.valueOf(carServices.updateCarClientByLogin(carClient, principal.getName())));
  return ResponseEntity.ok(contactConfirmationResponse);
 }

}
