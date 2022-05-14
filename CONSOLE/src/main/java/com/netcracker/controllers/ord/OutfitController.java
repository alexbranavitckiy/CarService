package com.netcracker.controllers.ord;


import com.fasterxml.jackson.annotation.JsonView;
import com.netcracker.DTO.car.CarClientDto;
import com.netcracker.DTO.car.ValidateCar;
import com.netcracker.DTO.errs.SaveSearchErrorException;
import com.netcracker.DTO.ord.OutfitDto;
import com.netcracker.DTO.ord.ValidateOrd;
import com.netcracker.DTO.response.ValidationErrorResponse;
import com.netcracker.DTO.response.Violation;
import com.netcracker.DTO.time.TimeDto;
import com.netcracker.outfit.State;
import com.netcracker.services.OutfitsServices;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
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
@ApiOperation("Outfit Service API")
public class OutfitController {

 private final OutfitsServices outfitsServices;

 @Autowired
 OutfitController(OutfitsServices outfitsServices) {
  this.outfitsServices = outfitsServices;
 }

 @JsonView({ValidateOrd.Details.class})
 @ApiOperation("Get breakdowns for this car")
 @GetMapping(value = "/aut/outfit-get/all")
 public ResponseEntity<List<OutfitDto>> getAllMasterOutfitWithStateAndSort(@RequestParam State state,
                                                                           @ApiIgnore Principal principal)
  throws SaveSearchErrorException {
  return ResponseEntity.ok(outfitsServices.getAllMasterOutfitWithStateAndSort(state, principal.getName()));
 }

 @ApiOperation("Start working with the outfit")
 @PutMapping(value = "/aut/outfit-start" ,consumes = MediaType.APPLICATION_JSON_VALUE,
  produces = MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<ValidationErrorResponse> outfitStartMaster(@RequestParam UUID uuidOutfit,
                                                                  @ApiIgnore Principal principal)
  throws SaveSearchErrorException {
  ValidationErrorResponse validationResponse = new ValidationErrorResponse();
  outfitsServices.outfitStartWork(principal.getName(), uuidOutfit);
  validationResponse.setViolations(List.of(new Violation("true", "Request passed successfully")));
  return ResponseEntity.ok(validationResponse);
 }

 @ApiOperation("End working with the outfit")
 @PutMapping(value = "/aut/outfit-end",consumes = MediaType.APPLICATION_JSON_VALUE,
  produces =
   MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<ValidationErrorResponse> outfitEndMaster(@ApiIgnore Principal principal)
  throws SaveSearchErrorException {
  ValidationErrorResponse validationResponse = new ValidationErrorResponse();
  outfitsServices.outfitEndWork(principal.getName());
  validationResponse.setViolations(List.of(new Violation("true", "Request passed successfully")));
  return ResponseEntity.ok(validationResponse);
 }

 @Operation(summary = "Outfit Update", description = "")
 @PostMapping(value = "/aut/outfit-update",consumes = MediaType.APPLICATION_JSON_VALUE,
  produces = MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<ValidationErrorResponse> updateOutfitByMaster(@JsonView({ValidateCar.Edit.class})
                                                                     @Validated(ValidateCar.Edit.class)
                                                                     @RequestBody OutfitDto outfitDto,
                                                                     @ApiIgnore Principal principal)
  throws SaveSearchErrorException {
  ValidationErrorResponse validationResponse = new ValidationErrorResponse();
  outfitsServices.updateOutfitByMaster(outfitDto, principal.getName());
  validationResponse.setViolations(List.of(new Violation("true", "Request passed successfully")));
  return ResponseEntity.ok(validationResponse);
 }


 @ApiOperation(value = "Get all clients")
 @GetMapping(value = "details/time/get-all")
 public ResponseEntity<List<TimeDto>> getAllClient() throws SaveSearchErrorException {
  return ResponseEntity.ok(outfitsServices.getAllOutfitByTime());
 }


 @Operation(summary = "Outfit Update", description = "")
 @PostMapping(value = "/details/outfit-update",consumes = MediaType.APPLICATION_JSON_VALUE,
  produces = MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<ValidationErrorResponse> updateOutfit(@JsonView({ValidateOrd.Details.class})
                                                             @Validated(ValidateOrd.Details.class)
                                                             @RequestBody OutfitDto outfitDto,
                                                             @ApiIgnore Principal principal)
  throws SaveSearchErrorException {
  ValidationErrorResponse validationResponse = new ValidationErrorResponse();
  outfitsServices.updateOutfitByMasterR(outfitDto, principal.getName());
  validationResponse.setViolations(List.of(new Violation("true", "Request passed successfully")));
  return ResponseEntity.ok(validationResponse);
 }


}
