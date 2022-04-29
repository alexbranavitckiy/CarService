package com.netcracker.controllers.car;


import com.netcracker.DTO.car.CarBreakdownDto;
import com.netcracker.DTO.response.ValidationErrorResponse;
import com.netcracker.annotations.ClientLabel;
import com.netcracker.annotations.SwaggerLabelMaster;
import com.netcracker.breakdown.State;
import com.netcracker.services.CarBreakdownServices;
import com.netcracker.services.CarServices;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@Validated
@ApiOperation("Breakdown Service API")
public class BreakdownController {

 private final CarBreakdownServices carBreakdownServices;
 private final CarServices carServices;

 @Autowired
 public BreakdownController(CarBreakdownServices carBreakdownServices, CarServices carServices) {
  this.carBreakdownServices = carBreakdownServices;
  this.carServices = carServices;
 }

 //--Client--//
 @ClientLabel
 @ApiOperation("Get user car history")
 @ApiResponses(value = {
  @ApiResponse(code = 200, message = "Data received successfully", response = CarBreakdownDto.class, responseContainer = "List"),
  @ApiResponse(code = 400, message = "Data not found", response = ArrayList.class)})
 @GetMapping("/person/breakdown/carBreakdown{carUUID}/sortDate")
 public ResponseEntity<List<CarBreakdownDto>> getAllBreakdownByIdCarClientAndSortByDate(@PathVariable @NotNull UUID carUUID, @ApiIgnore Principal principal) {
  List<CarBreakdownDto> list = carBreakdownServices.getAllBreakdownByCarIdLoginSort(carUUID, principal.getName());
  if (list.size() > 1) return new ResponseEntity<>(list, HttpStatus.OK);
  return ResponseEntity.badRequest()
   .body(new ArrayList<>());
 }

 @ClientLabel
 @ApiOperation("Get user car history")
 @ApiResponses(value = {
  @ApiResponse(code = 200, message = "Data received successfully", response = CarBreakdownDto.class, responseContainer = "List"),
  @ApiResponse(code = 400, message = "Data not found", response = List.class)})
 @GetMapping("/person/breakdown/carBreakdown{carUUID}")
 public ResponseEntity<List<CarBreakdownDto>> getAllBreakdownByIdCarClient(@PathVariable @NotNull UUID carUUID, @ApiIgnore Principal principal) {
  List<CarBreakdownDto> list = carBreakdownServices.getAllBreakdownByCarIdLogin(carUUID, principal.getName());
  if (list.size() > 1) return new ResponseEntity<>(list, HttpStatus.OK);
  return ResponseEntity.badRequest()
   .body(new ArrayList<>());
 }

 @ClientLabel
 @ApiOperation("Get all breakdowns by car ID, state")
 @ApiResponses(value = {
  @ApiResponse(code = 200, message = "Data received successfully", response = CarBreakdownDto.class, responseContainer = "List"),
  @ApiResponse(code = 400, message = "Data not found", response = ArrayList.class)})
 @GetMapping("/person/breakdown/carBreakdown{carUUID}/{state}")
 public ResponseEntity<List<CarBreakdownDto>> getAllBreakdownByIdCarAndState(@PathVariable @NotNull UUID carUUID, @PathVariable(value = "state") State state, @ApiIgnore Principal principal) {
  List<CarBreakdownDto> list = carBreakdownServices.getAllBreakdownByCarAndStateSortDesc(carUUID, state, principal.getName());
  if (list.size() > 1) return new ResponseEntity<>(list, HttpStatus.OK);
  return ResponseEntity.badRequest()
   .body(new ArrayList<>());
 }

 @ClientLabel
 @ApiOperation("Get all breakdowns")
 @ApiResponses(value = {
  @ApiResponse(code = 200, message = "Data received successfully", response = CarBreakdownDto.class, responseContainer = "List"),
  @ApiResponse(code = 400, message = "Data not found", response = ArrayList.class)})
 @GetMapping("/person/breakdown/carBreakdown")
 public ResponseEntity<List<CarBreakdownDto>> getAllBreakdownByIdCarAndState(@ApiIgnore Principal principal) {
  List<CarBreakdownDto> list = carBreakdownServices.getAllBreakdownByCarSortDesc(principal.getName());
  if (list.size() > 1) return new ResponseEntity<>(list, HttpStatus.OK);
  return ResponseEntity.badRequest()
   .body(new ArrayList<>());
 }

 //--Client--//

 //--Master--//

 @SwaggerLabelMaster
 @ApiOperation("Add car breakdown")
 @PostMapping(value = "/aut/add/carBreakdown")
 public ResponseEntity<Boolean> addBreakdownOnMaster(@RequestBody CarBreakdownDto carBreakdownForm) {
  return ResponseEntity.ok(carBreakdownServices.addBreakdownOnMaster(carBreakdownForm));
 }

 @SwaggerLabelMaster
 @ApiOperation("Get all breakdowns be carId")
 @GetMapping(value = "/aut/getAll/carBreakdown{carId}")
 public ResponseEntity<List<CarBreakdownDto>> addBreakdownOnMaster(@PathVariable UUID carId) {
  return ResponseEntity.ok(carBreakdownServices.getAllBreakDownByCarIdOnMaster(carId));
 }

 @SwaggerLabelMaster
 @ApiOperation("Update car breakdown")
 @PostMapping(value = "/aut/update/carBreakdown")
 public ResponseEntity<Boolean> updateBreakdownOnMaster(@RequestBody CarBreakdownDto carBreakdownForm) {
  return ResponseEntity.ok(carBreakdownServices.updateBreakdownOnMaster(carBreakdownForm));
 }

 //--Master--//

}
