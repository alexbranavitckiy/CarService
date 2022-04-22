package com.netcracker.controllers.car;


import com.netcracker.DTO.car.CarBreakdownDto;
import com.netcracker.DTO.car.CarBreakdownForm;
import com.netcracker.annotations.ClientLabel;
import com.netcracker.annotations.SwaggerLabelMaster;
import com.netcracker.breakdown.State;
import com.netcracker.services.CarBreakdownServices;
import com.netcracker.services.CarServices;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping()
@ApiOperation("Breakdown Service API")
public class BreakdownController {

 private final CarBreakdownServices carBreakdownServices;
 private final CarServices carServices;

 @Autowired
 private BreakdownController(CarBreakdownServices carBreakdownServices, CarServices carServices) {
  this.carBreakdownServices = carBreakdownServices;
  this.carServices = carServices;
 }

 //--Client--//
 @ClientLabel
 @ApiOperation("Get user car history")
 @GetMapping("/person/breakdown/carBreakdown{carUUID}/sortDate")
 public ResponseEntity<List<CarBreakdownDto>> getAllBreakdownByIdCarClientAndSortByDate(@PathVariable UUID carUUID, @ApiIgnore Principal principal) {
  return ResponseEntity.ok(carBreakdownServices.getAllBreakdownByCarIdLoginSort(carUUID, principal.getName()));
 }

 @ClientLabel
 @ApiOperation("Get user car history")
 @GetMapping("/person/breakdown/carBreakdown{carUUID}")
 public ResponseEntity<List<CarBreakdownDto>> getAllBreakdownByIdCarClient(@PathVariable UUID carUUID, @ApiIgnore Principal principal) {
  return ResponseEntity.ok(carBreakdownServices.getAllBreakdownByCarIdLogin(carUUID, principal.getName()));
 }

 @ClientLabel
 @ApiOperation("Get all breakdowns by car ID, state")
 @GetMapping("/person/breakdown/carBreakdown{carUUID}/{state}")
 public ResponseEntity<List<CarBreakdownDto>> getAllBreakdownByIdCarAndState(@PathVariable UUID carUUID, @PathVariable(value = "state") State state, @ApiIgnore Principal principal) {
  return ResponseEntity.ok(carBreakdownServices.getAllBreakdownByCarAndStateSortDesc(carUUID, state, principal.getName()));
 }

 @ClientLabel
 @ApiOperation("Get all breakdowns")
 @GetMapping("/person/breakdown/carBreakdown")
 public ResponseEntity<List<CarBreakdownDto>> getAllBreakdownByIdCarAndState(@ApiIgnore Principal principal) {
  return ResponseEntity.ok(carBreakdownServices.getAllBreakdownByCarSortDesc(principal.getName()));
 }
 //--Client--//

 //--Master--//

 @SwaggerLabelMaster
 @ApiOperation("Add car breakdown")
 @PostMapping(value = "/aut/add/carBreakdown")
 public ResponseEntity<Boolean> addBreakdownOnMaster(@RequestBody CarBreakdownForm carBreakdownForm) {
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
 public ResponseEntity<Boolean> updateBreakdownOnMaster(@RequestBody CarBreakdownForm carBreakdownForm) {
  return ResponseEntity.ok(carBreakdownServices.updateBreakdownOnMaster(carBreakdownForm));
 }

 //--Master--//

}
