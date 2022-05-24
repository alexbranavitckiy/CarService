package com.netcracker.controllers.car;


import com.fasterxml.jackson.annotation.JsonView;
import com.netcracker.DTO.car.CarBreakdownDto;
import com.netcracker.DTO.car.ValidateBreakdown;
import com.netcracker.DTO.errs.SaveSearchErrorException;
import com.netcracker.breakdown.State;
import com.netcracker.services.CarBreakdownServices;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

 @Autowired
 public BreakdownController(CarBreakdownServices carBreakdownServices) {
  this.carBreakdownServices = carBreakdownServices;
 }

 //--Client--//
 @ApiOperation("Get user car history")
 @ApiResponses(value = {
  @ApiResponse(code = 200, message = "Data received successfully", response = UUID.class,
   responseContainer = "List"),
  @ApiResponse(code = 400, message = "Data not found", response = ArrayList.class)})
 @GetMapping("/person/breakdown{carUUID}/sort-date")
 public ResponseEntity<List<CarBreakdownDto>> getAllBreakdownByIdCarClientAndSortByDate(
  @PathVariable @NotNull UUID carUUID, @ApiIgnore Principal principal)
  throws SaveSearchErrorException {
  return ResponseEntity.ok(carBreakdownServices.getAllBreakdownByCarIdLoginSort(carUUID, principal.getName()));
 }

 @ApiOperation("Get user car history")
 @ApiResponses(value = {
  @ApiResponse(code = 200, message = "Data received successfully", response = CarBreakdownDto.class,
   responseContainer = "List"),
  @ApiResponse(code = 400, message = "Data not found", response = List.class)})
 @GetMapping("/person/breakdown/{carUUID}")
 public ResponseEntity<List<CarBreakdownDto>> getAllBreakdownByIdCarClient(@PathVariable @NotNull UUID carUUID,
                                                                           @ApiIgnore Principal principal)
  throws SaveSearchErrorException {
  return ResponseEntity.ok(carBreakdownServices.getAllBreakdownByCarIdLogin(carUUID, principal.getName()));
 }

 @ApiOperation("Get all breakdowns by car ID, state")
 @ApiResponses(value = {
  @ApiResponse(code = 200, message = "Data received successfully", response = CarBreakdownDto.class,
   responseContainer = "List"),
  @ApiResponse(code = 400, message = "Data not found", response = ArrayList.class)})
 @GetMapping("/person/breakdown/carBreakdown{carUUID}/{state}")
 public ResponseEntity<List<CarBreakdownDto>> getAllBreakdownByIdCarAndState(@PathVariable @NotNull UUID carUUID,
                                                                             @PathVariable(value = "state") State state,
                                                                             @ApiIgnore Principal principal,
                                                                             HttpRequest httpRequest)
  throws SaveSearchErrorException {
  return ResponseEntity.ok(carBreakdownServices.getAllBreakdownByCarAndStateSortDesc(carUUID, state,
   principal.getName()));
 }

 @ApiOperation("Get all breakdowns")
 @ApiResponses(value = {
  @ApiResponse(code = 200, message = "Data received successfully", response = CarBreakdownDto.class,
   responseContainer = "List"),
  @ApiResponse(code = 400, message = "Data not found", response = ArrayList.class)})
 @GetMapping("/person/breakdowns")
 public ResponseEntity<List<CarBreakdownDto>> getAllBreakdownByIdCarAndState(@RequestParam("offset") int offset,
                                                                             @RequestParam("limit") int limit,
                                                                             @ApiIgnore Principal principal)
  throws SaveSearchErrorException {
  return ResponseEntity.ok(carBreakdownServices.getAllBreakdownByCarSortDesc(offset, limit, principal.getName()));
 }

 //--Client--//
 //--Master--//

 @ApiOperation("Add car breakdown")
 @PostMapping(value = "/details/add/breakdown/{idOrders}")
 public ResponseEntity<UUID> addBreakdownOnMaster(@PathVariable UUID idOrders,
                                                  @Validated({ValidateBreakdown.New.class})
                                                  @JsonView({ValidateBreakdown.New.class})
                                                  @RequestBody CarBreakdownDto carBreakdownDto,
                                                  @ApiIgnore Principal principal) throws SaveSearchErrorException {
  return ResponseEntity.status(HttpStatus.CREATED)
   .body(carBreakdownServices.addBreakdownOnMaster(carBreakdownDto, idOrders, principal.getName()));
 }

 @ApiOperation("Update car breakdown")
 @PutMapping(value = {"/details/update-breakdown"}, consumes = MediaType.APPLICATION_JSON_VALUE, produces =
  MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<UUID> updateBreakdownOnMasterR(@Validated({ValidateBreakdown.DetailAdmin.class})
                                                      @JsonView({ValidateBreakdown.DetailAdmin.class})
                                                      @RequestBody CarBreakdownDto carBreakdownForm,
                                                      @ApiIgnore Principal principal)
  throws SaveSearchErrorException {
  return ResponseEntity.status(HttpStatus.OK)
   .body(carBreakdownServices.updateBreakdownOnMasterR(carBreakdownForm, principal.getName()));
 }

 @ApiOperation("Update car breakdown")
 @PatchMapping(value = {"/details/update-breakdown"}, consumes = MediaType.APPLICATION_JSON_VALUE, produces =
  MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<UUID> updatePartBreakdownOnMasterR(@Validated({ValidateBreakdown.DetailAdmin.class})
                                                          @JsonView({ValidateBreakdown.DetailAdmin.class})
                                                          @RequestBody CarBreakdownDto carBreakdownForm,
                                                          @ApiIgnore Principal principal)
  throws SaveSearchErrorException {
  return ResponseEntity.status(HttpStatus.OK)
   .body(carBreakdownServices.updatePartBreakdownOnMasterR(carBreakdownForm, principal.getName()));
 }

 @ApiOperation("Get all breakdowns")
 @GetMapping(value = "/details/breakdowns")
 public ResponseEntity<List<CarBreakdownDto>> getBreakdownOnMasterR(@Validated({ValidateBreakdown.Details.class})
                                                                    @JsonView({ValidateBreakdown.Details.class})
                                                                    @ApiIgnore Principal principal,
                                                                    @RequestParam("offset") int offset,
                                                                    @RequestParam("limit") int limit)
  throws SaveSearchErrorException {
  return ResponseEntity.ok(carBreakdownServices.getAllBreakDownBOnMasterR(principal.getName(), offset, limit));
 }


 @ApiOperation("Get breakdown in the context of an outfit")
 @GetMapping(value = "/aut/breakdown/{id}")
 public ResponseEntity<List<CarBreakdownDto>> getBreakdownOnMasterById(@Validated({ValidateBreakdown.Details.class})
                                                                       @JsonView({ValidateBreakdown.Details.class})
                                                                       @PathVariable UUID id,
                                                                       @ApiIgnore Principal principal)
  throws SaveSearchErrorException {
  return ResponseEntity.ok(carBreakdownServices.getAllBreakDownBOnMaster(principal.getName(), id));
 }

 @ApiOperation("Get all breakdowns in the context of an outfit")
 @GetMapping(value = "/aut/breakdowns")
 public ResponseEntity<List<CarBreakdownDto>> getBreakdownOnMaster(@Validated({ValidateBreakdown.Details.class})
                                                                   @JsonView({ValidateBreakdown.Details.class})
                                                                   @ApiIgnore Principal principal)
  throws SaveSearchErrorException {
  return ResponseEntity.ok(carBreakdownServices.getAllBreakDownBOnMaster(principal.getName()));
 }

 @ApiOperation("Update car breakdown")
 @PutMapping(value = {"/aut/update-breakdown"}, consumes = MediaType.APPLICATION_JSON_VALUE, produces =
  MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<Boolean> updateBreakdownOnMaster(@Validated({ValidateBreakdown.Edit.class})
                                                        @JsonView({ValidateBreakdown.Edit.class})
                                                        @RequestBody CarBreakdownDto carBreakdownForm,
                                                        @ApiIgnore Principal principal)
  throws SaveSearchErrorException {
  return ResponseEntity.status(HttpStatus.OK)
   .body(carBreakdownServices.updateBreakdownOnMaster(carBreakdownForm, principal.getName()));
 }


 @ApiOperation("Get car repair history")
 @GetMapping(value = {"/aut/car-history/{id}", "/details/car-history/{id}"})
 public ResponseEntity<List<CarBreakdownDto>> getCarHistory(@PathVariable UUID id,
                                                            @Validated({ValidateBreakdown.Details.class})
                                                            @JsonView({ValidateBreakdown.Details.class})
                                                            @ApiIgnore Principal principal)
  throws SaveSearchErrorException {
  return ResponseEntity.ok(carBreakdownServices.getAllBreakDownOnCar(id));
 }

// --Master--//
}
