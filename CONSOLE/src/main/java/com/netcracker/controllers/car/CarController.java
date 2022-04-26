package com.netcracker.controllers.car;

import com.fasterxml.jackson.annotation.JsonView;
import com.netcracker.DTO.Validate;
import com.netcracker.DTO.car.CarClientDto;
import com.netcracker.DTO.errs.SaveErrorException;
import com.netcracker.DTO.response.ValidationErrorResponse;
import com.netcracker.DTO.response.Violation;
import com.netcracker.annotations.ClientLabel;
import com.netcracker.services.CarServices;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

 @JsonView({Validate.Details.class})
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
 @PostMapping(value = "/person/garage-registration", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
  MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<ValidationErrorResponse> createCar(@Validated({Validate.Edit.class, Validate.UiCrossFieldChecks.class}) @JsonView(Validate.Edit.class) @RequestBody CarClientDto carClient, @ApiIgnore Principal principal) {
  ValidationErrorResponse validationResponse = new ValidationErrorResponse();
  try {
   carServices.createCarOnClient(carClient, principal.getName());
   validationResponse.setViolations(List.of(new Violation("true", "Updates successfully committed")));
  } catch (SaveErrorException s) {
   validationResponse.setViolations(List.of(new Violation("false", s.getMessage())));
  }
  return ResponseEntity.ok(validationResponse);
 }


 @JsonView({Validate.Details.class})
 @ClientLabel
 @ApiOperation("Get machine of user logged in by id")
 @GetMapping("/person/Car{CarUUID}")
 public ResponseEntity<List<CarClientDto>> getCarByIdCar(@PathVariable UUID CarUUID, @ApiIgnore Principal principal) {
  Optional<CarClientDto> carClient = carServices.getCarByIdCarOnClient(CarUUID, principal.getName());
  if (carClient.isEmpty()) {
   return ResponseEntity.ok(new ArrayList<>());
  }
  return ResponseEntity.ok(List.of(carClient.get()));
 }

 @ClientLabel
 @ApiOperation("Car update")
 @ApiResponses(value = {
  @ApiResponse(code = 200, message = "Data updated successfully", response = ValidationErrorResponse.class, responseContainer = "List"),
  @ApiResponse(code = 400, message = "Invalid input", response = ValidationErrorResponse.class, responseContainer = "List")})
 @PostMapping(value = "/person/car-update", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
  MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<ValidationErrorResponse> updateCar(@Validated({Validate.Edit.class, Validate.UiCrossFieldChecks.class}) @JsonView({Validate.New.class}) @RequestBody CarClientDto carClient, @ApiIgnore Principal principal) {
  ValidationErrorResponse validationResponse = new ValidationErrorResponse();
  try {
   if (carServices.updateCarClientByLogin(carClient, principal.getName())) {
    validationResponse.setViolations(List.of(new Violation("true", "Updates successfully committed")));
   } else {
    validationResponse.setViolations(List.of(new Violation("false", "Invalid data entered")));
   }
  } catch (SaveErrorException s) {
   validationResponse.setViolations(List.of(new Violation("false", s.getMessage())));
  }
  return ResponseEntity.ok(validationResponse);
 }

 @ClientLabel
 @ApiOperation("Update car number")
 @ApiResponses(value = {
  @ApiResponse(code = 200, message = "Data updated successfully", response = ValidationErrorResponse.class, responseContainer = "List"),
  @ApiResponse(code = 400, message = "Invalid input", response = ValidationErrorResponse.class, responseContainer = "List")})
 @PostMapping(value = "/person/car-update/meta", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
  MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<ValidationErrorResponse> updateCarClientByIdWithMachineNumber(@Validated({Validate.UiCrossFieldChecks.class, Validate.UiCrossFieldChecks.class}) @JsonView({Validate.UiCrossFieldChecks.class}) @RequestBody CarClientDto carClient, @ApiIgnore Principal principal) {
  ValidationErrorResponse validationResponse = new ValidationErrorResponse();
  try {
   if (carServices.updateCarClientByIdWithMachineNumber(carClient, principal.getName())) {
    validationResponse.setViolations(List.of(new Violation("true", "Updates successfully committed")));
   } else {
    validationResponse.setViolations(List.of(new Violation("false", "Invalid data entered")));
   }
  } catch (SaveErrorException s) {
   validationResponse.setViolations(List.of(new Violation("false", s.getMessage())));
  }
  return ResponseEntity.ok(validationResponse);
 }

}
