package com.netcracker.controllers;


import com.netcracker.DTO.response.ApiResponse;
import com.netcracker.DTO.response.ContactConfirmationResponse;
import com.netcracker.breakdown.CarBreakdown;
import com.netcracker.breakdown.State;
import com.netcracker.car.CarClient;
import com.netcracker.services.CarBreakdownServices;
import com.netcracker.services.CarServices;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/breakdown")
@ApiOperation("Breakdown Service API")
public class BreakdownController {

 private final CarBreakdownServices carBreakdownServices;
 private final CarServices carServices;

 @Autowired
 private BreakdownController(CarBreakdownServices carBreakdownServices, CarServices carServices) {
  this.carBreakdownServices = carBreakdownServices;
  this.carServices = carServices;
 }

 @ApiOperation("Get all breakdowns by car ID")
 @GetMapping("/clients/getAll/carBreakdown{carUUID}")
 public ResponseEntity<List<CarBreakdown>> getAllBreakdownByIdCar(@PathVariable UUID carUUID) {
  return ResponseEntity.ok(carBreakdownServices.getAllBreakdownByCar(carUUID));
 }

 @ApiOperation("Get all breakdowns by car ID, state")
 @GetMapping("/clients/getAll/carBreakdown{carUUID}/state")
 public ResponseEntity<List<CarBreakdown>> getAllBreakdownByIdCarAndState(@PathVariable UUID carUUID, @RequestParam(value = "state") State state) {
  return ResponseEntity.ok(carBreakdownServices.getAllBreakdownBeCarAndState(carUUID, state));
 }

 @ApiOperation("Adding a break")
 @PostMapping(value = "/clients/add/carBreakdown", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
 public ResponseEntity<ContactConfirmationResponse> addBreakdownByIdCar(CarBreakdown carBreakdown) {
  ContactConfirmationResponse contactConfirmationResponse = new ContactConfirmationResponse();
  contactConfirmationResponse.setResult(carBreakdownServices.addBreakdown(carBreakdown));
  return ResponseEntity.ok(contactConfirmationResponse);
 }

 @ApiOperation("Saving breakage")
 @PostMapping(value = "/clients/save/carBreakdown", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
 public ResponseEntity<ContactConfirmationResponse> updateBreakdown(CarBreakdown carBreakdown) {
  ContactConfirmationResponse contactConfirmationResponse = new ContactConfirmationResponse();
  contactConfirmationResponse.setResult(carBreakdownServices.addBreakdown(carBreakdown));
  return ResponseEntity.ok(contactConfirmationResponse);
 }

 @ExceptionHandler(MissingServletRequestParameterException.class)
 public ResponseEntity<ApiResponse<CarClient>> handleMissingServletRequestParameterException(Exception exception) {
  return new ResponseEntity<>(new ApiResponse<CarClient>(HttpStatus.BAD_REQUEST, "Missing required parameters",
   exception), HttpStatus.BAD_REQUEST);
 }

}
