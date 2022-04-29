package com.netcracker.controllers.ord;


import com.fasterxml.jackson.annotation.JsonView;
import com.netcracker.DTO.car.ValidateCar;
import com.netcracker.DTO.errs.SaveSearchErrorException;
import com.netcracker.DTO.ord.OutfitDto;
import com.netcracker.DTO.ord.ValidateOrd;
import com.netcracker.DTO.response.ValidationErrorResponse;
import com.netcracker.DTO.response.Violation;
import com.netcracker.annotations.ClientLabel;
import com.netcracker.annotations.SwaggerLabelMaster;
import com.netcracker.outfit.Outfit;
import com.netcracker.outfit.State;
import com.netcracker.services.OutfitsServices;
import io.swagger.annotations.ApiOperation;
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

 //--Master--//
 @JsonView({ValidateOrd.Details.class})
 @SwaggerLabelMaster
 @ApiOperation("Get all master's outfits sorted by date")
 @GetMapping(value = "/aut/outfit-get/All{state}")
 public ResponseEntity<List<OutfitDto>> getAllMasterOutfitWithStateAndSort(@PathVariable State state, @ApiIgnore Principal principal) throws SaveSearchErrorException {
  return ResponseEntity.ok(outfitsServices.getAllMasterOutfitWithStateAndSort(state, principal.getName()));
 }

 @SwaggerLabelMaster
 @ApiOperation("Start working with the outfit")
 @PutMapping(value = "/aut/outfit-start")
 public ResponseEntity<ValidationErrorResponse> outfitStartMaster(@RequestParam UUID uuidOutfit, @ApiIgnore Principal principal) throws SaveSearchErrorException {
  ValidationErrorResponse validationResponse = new ValidationErrorResponse();
  outfitsServices.outfitStartWork(principal.getName(), uuidOutfit);
  validationResponse.setViolations(List.of(new Violation("true", "Request passed successfully")));
  return ResponseEntity.ok(validationResponse);
 }

 @SwaggerLabelMaster
 @ApiOperation("End working with the outfit")
 @PutMapping(value = "/aut/outfit-end")
 public ResponseEntity<ValidationErrorResponse> outfitEndMaster(@RequestParam UUID uuidOutfit, @ApiIgnore Principal principal) throws SaveSearchErrorException {
  ValidationErrorResponse validationResponse = new ValidationErrorResponse();
  outfitsServices.outfitEndWork(principal.getName(), uuidOutfit);
  validationResponse.setViolations(List.of(new Violation("true", "Request passed successfully")));
  return ResponseEntity.ok(validationResponse);
 }

 @SwaggerLabelMaster
 @Operation(
  summary = "Outfit Update",
  description = "The outfit is available for updating only if the master has started work (State.WORK).")
 @PostMapping(value = "/aut/outfit-update")
 public ResponseEntity<ValidationErrorResponse> updateOutfitByMaster(@JsonView({ValidateCar.Edit.class}) @Validated @RequestBody OutfitDto outfitDto, @ApiIgnore Principal principal) throws SaveSearchErrorException {
  ValidationErrorResponse validationResponse = new ValidationErrorResponse();
  outfitsServices.updateOutfitByMaster(outfitDto, principal.getName());
  validationResponse.setViolations(List.of(new Violation("true", "Request passed successfully")));
  return ResponseEntity.ok(validationResponse);
 }


//--Master--//


}
