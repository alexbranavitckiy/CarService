package com.netcracker.controllers.ord;

import com.fasterxml.jackson.annotation.JsonView;
import com.netcracker.DTO.car.ValidateCar;
import com.netcracker.DTO.errs.SaveSearchErrorException;
import com.netcracker.DTO.ord.OutfitDto;
import com.netcracker.DTO.ord.ValidateOrd;
import com.netcracker.DTO.response.ValidationErrorResponse;
import com.netcracker.DTO.response.Violation;
import com.netcracker.DTO.time.TimeDto;
import com.netcracker.DTO.time.ValidateTime;
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

 @JsonView({ValidateOrd.Details.class})
 @ApiOperation("Get breakdowns for this car")
 @GetMapping(value = "/aut/outfits")
 public ResponseEntity<List<OutfitDto>> getAllMasterOutfitWithStateAndSort(@RequestParam State state,
                                                                           @ApiIgnore Principal principal)
  throws SaveSearchErrorException {
  return ResponseEntity.ok(outfitsServices.getAllMasterOutfitWithStateAndSort(state, principal.getName()));
 }

 @ApiOperation("Start working with the outfit")
 @PatchMapping(value = "/aut/outfit-start")
 public ResponseEntity<UUID> outfitStartMaster(@RequestParam UUID uuidOutfit,
                                               @ApiIgnore Principal principal)
  throws SaveSearchErrorException {
  return ResponseEntity.ok(outfitsServices.outfitStartWork(principal.getName(), uuidOutfit));
 }

 @ApiOperation("End working with the outfit")
 @PatchMapping(value = "/aut/outfit-end")
 public ResponseEntity<ValidationErrorResponse> outfitEndMaster(@ApiIgnore Principal principal)
  throws SaveSearchErrorException {
  ValidationErrorResponse validationResponse = new ValidationErrorResponse();
  outfitsServices.outfitEndWork(principal.getName());
  validationResponse.setViolations(List.of(new Violation("true", "Request passed successfully")));
  return ResponseEntity.ok(validationResponse);
 }

 @Operation(summary = "Outfit Update", description = "")
 @PostMapping(value = "/aut/outfit-update", consumes = MediaType.APPLICATION_JSON_VALUE,
  produces = MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<UUID> updateOutfitByMaster(@JsonView({ValidateCar.Edit.class})
                                                                     @Validated(ValidateCar.Edit.class)
                                                                     @RequestBody OutfitDto outfitDto,
                                                                     @ApiIgnore Principal principal)
  throws SaveSearchErrorException {
  return ResponseEntity.ok(outfitsServices.updateOutfitByMaster(outfitDto, principal.getName()));
 }


 @ApiOperation(value = "Get all")
 @GetMapping(value = "details/outfits")
 public ResponseEntity<List<TimeDto>> getAllClient() throws SaveSearchErrorException {
  return ResponseEntity.ok(outfitsServices.getAllOutfitByTime());
 }


 @Operation(summary = "Outfit Update", description = "")
 @PatchMapping(value = "/details/outfit-update", consumes = MediaType.APPLICATION_JSON_VALUE,
  produces = MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<UUID> updateOutfit(@JsonView({ValidateCar.Edit.class})
                                          @Validated(ValidateCar.Edit.class)
                                          @RequestBody OutfitDto outfitDto,
                                          @ApiIgnore Principal principal,
                                          @RequestParam UUID idOutfit)
  throws SaveSearchErrorException {
  return ResponseEntity.ok(outfitsServices.updateOutfitByMasterR(outfitDto, principal.getName(), idOutfit));
 }

 @Operation(summary = "Change master in outfit", description = "")
 @PutMapping(value = "/details/outfit/update-master", consumes = MediaType.APPLICATION_JSON_VALUE,
  produces = MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<UUID> updateOutfitMaster(@JsonView({ValidateTime.Edit.class})
                                                @Validated(ValidateTime.Edit.class)
                                                @RequestBody TimeDto timeDto,
                                                @ApiIgnore Principal principal)
  throws SaveSearchErrorException {
  return ResponseEntity.ok(outfitsServices.updateOutfitMasterByMasterR(timeDto, principal.getName()));
 }


}
