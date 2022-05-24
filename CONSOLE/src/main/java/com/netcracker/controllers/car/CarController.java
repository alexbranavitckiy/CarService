package com.netcracker.controllers.car;

import com.fasterxml.jackson.annotation.JsonView;
import com.netcracker.DTO.car.CarClientDto;
import com.netcracker.DTO.car.ValidateCar;
import com.netcracker.DTO.errs.SaveSearchErrorException;
import com.netcracker.DTO.response.ValidationErrorResponse;
import com.netcracker.DTO.response.Violation;
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

 @JsonView({ValidateCar.Details.class})
 @ApiOperation("Get all the car of the logged in user")
 @GetMapping("/person/garage/cars")
 public ResponseEntity<List<CarClientDto>> getAllCarByIdClients(@ApiIgnore Principal principal,
                                                                @RequestParam("offset") Integer offset,
                                                                @RequestParam("limit") Integer limit)
  throws SaveSearchErrorException {
  return ResponseEntity.ok(carServices.getCarByLoginClient(principal.getName(), offset, limit));
 }

 @ApiOperation("Car registration")
 @ApiResponses(value = {
  @io.swagger.annotations.ApiResponse(code = 200, message = "The machine is successfully created",
   response = ValidationErrorResponse.class, responseContainer = "List"),
  @ApiResponse(code = 400, message = "Invalid input", response = ValidationErrorResponse.class,
   responseContainer = "List")})
 @PostMapping(value = "/person/garage-registration", consumes = MediaType.APPLICATION_JSON_VALUE,
  produces =
   MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<UUID> createCar(@Validated({ValidateCar.New.class, ValidateCar.UiCrossFieldChecks.class})
                                       @JsonView(ValidateCar.New.class) @RequestBody CarClientDto carClient,
                                       @ApiIgnore Principal principal) throws SaveSearchErrorException {
  return ResponseEntity.status(HttpStatus.OK).body(carServices.createCarOnClient(carClient, principal.getName()));
 }

 @JsonView({ValidateCar.Details.class})
 @ApiOperation("Get machine of user logged in by id")
 @GetMapping("/person/car")
 public ResponseEntity<List<CarClientDto>> getCarByIdCar(@RequestParam UUID CarUUID, @ApiIgnore Principal principal)
  throws SaveSearchErrorException {
  Optional<CarClientDto> carClient = carServices.getCarByIdCarOnClient(CarUUID, principal.getName());
  if (carClient.isEmpty()) {
   return ResponseEntity.ok(new ArrayList<>());
  }
  return ResponseEntity.ok(List.of(carClient.get()));
 }

 @ApiOperation("Car update")
 @ApiResponses(value = {
  @ApiResponse(code = 200, message = "Data updated successfully", response = ValidationErrorResponse.class,
   responseContainer = "List"),
  @ApiResponse(code = 400, message = "Invalid input", response = ValidationErrorResponse.class,
   responseContainer = "List")})
 @PutMapping(value = "/person/car-update", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
  MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<ValidationErrorResponse> updateCar(@Validated({ValidateCar.Edit.class,
  ValidateCar.UiCrossFieldChecks.class}) @JsonView({ValidateCar.Edit.class})
                                                          @RequestBody CarClientDto carClient,
                                                          @ApiIgnore Principal principal)
  throws SaveSearchErrorException {
  ValidationErrorResponse validationResponse = new ValidationErrorResponse();
  carServices.updateCarClientByLogin(carClient, principal.getName());
  validationResponse.setViolations(List.of(new Violation("true", "Updates successfully committed")));
  return ResponseEntity.ok(validationResponse);
 }

 @ApiOperation("Update car number")
 @ApiResponses(value = {
  @ApiResponse(code = 200, message = "Data updated successfully", response = ValidationErrorResponse.class,
   responseContainer = "List"),
  @ApiResponse(code = 400, message = "Invalid input", response = ValidationErrorResponse.class,
   responseContainer = "List")})
 @PutMapping(value = "/person/car-update/meta", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
  MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<ValidationErrorResponse> updateCarClientByIdWithMachineNumber(
  @Validated({ValidateCar.UiCrossFieldChecks.class, ValidateCar.EditValue.class})
  @JsonView({ValidateCar.EditValue.class})
  @RequestBody CarClientDto carClient,
  @ApiIgnore Principal principal) throws SaveSearchErrorException {
  ValidationErrorResponse validationResponse = new ValidationErrorResponse();
  carServices.updateCarClientByIdWithMachineNumber(carClient, principal.getName());
  validationResponse.setViolations(List.of(new Violation("true", "Updates successfully committed")));
  return ResponseEntity.ok(validationResponse);
 }

 @ApiOperation("Car registration")
 @ApiResponses(value = {
  @io.swagger.annotations.ApiResponse(code = 201, message = "The machine is successfully created",
   response = ValidationErrorResponse.class, responseContainer = "List"),
  @ApiResponse(code = 400, message = "Invalid input", response = ValidationErrorResponse.class,
   responseContainer = "List")})
 @PostMapping(value = "/details/garage-registration", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
  MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<UUID> createCarOnMaster(@Validated({ValidateCar.UiCrossFieldChecks.class})
                                               @JsonView(ValidateCar.NewAdmin.class)
                                               @RequestBody CarClientDto carClient)
  throws SaveSearchErrorException {
  return ResponseEntity.status(HttpStatus.CREATED).body(carServices.createCarOnMaster(carClient));
 }

 @JsonView({ValidateCar.Details.class})
 @ApiOperation("Show all cars")
 @GetMapping("/details/garage/cars")
 public ResponseEntity<List<CarClientDto>> getAllCar(@RequestParam("offset") Integer offset,
                                                     @RequestParam("limit") Integer limit)
  throws SaveSearchErrorException {
  return ResponseEntity.status(HttpStatus.OK).body(carServices.getAllCarOnMaster());
 }

 @JsonView({ValidateCar.Details.class})
 @ApiOperation("Search by car")
 @GetMapping("/details/garage-search")
 public ResponseEntity<List<CarClientDto>> searchCar(@RequestParam("offset") Integer offset,
                                                     @RequestParam("limit") Integer limit,
                                                     @RequestParam String search) throws SaveSearchErrorException {
  return ResponseEntity.ok(carServices.getSearchCarOnMaster(search, offset, limit));
 }

}
