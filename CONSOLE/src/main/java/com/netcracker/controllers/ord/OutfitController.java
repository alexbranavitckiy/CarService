package com.netcracker.controllers.ord;


import com.netcracker.DTO.ord.OutfitDto;
import com.netcracker.annotations.ClientLabel;
import com.netcracker.annotations.SwaggerLabelMaster;
import com.netcracker.outfit.Outfit;
import com.netcracker.outfit.State;
import com.netcracker.services.OutfitsServices;
import io.swagger.annotations.ApiOperation;
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
public class OutfitController {

 private final OutfitsServices outfitsServices;

 @Autowired
 OutfitController(OutfitsServices outfitsServices) {
  this.outfitsServices = outfitsServices;
 }

 //--Master--//
 @SwaggerLabelMaster
 @ApiOperation("Get all master's outfits sorted by date")
 @GetMapping(value = "/aut/outfitGetAll{state}")
 public ResponseEntity<List<OutfitDto>> getAllMasterOutfitWithStateAndSort(@PathVariable State state, @ApiIgnore Principal principal) {
  return ResponseEntity.ok(outfitsServices.getAllMasterOutfitWithStateAndSort(state, principal.getName()));
 }

 @SwaggerLabelMaster
 @ApiOperation("Start working with the outfit")
 @PostMapping(value = "/aut/outfitStart{state}")
 public ResponseEntity<Boolean> outfitStartMaster(@PathVariable State state,@RequestParam  UUID uuidOutfit , @ApiIgnore Principal principal) {
  return ResponseEntity.ok(outfitsServices.outfitStartWork(state, principal.getName(),uuidOutfit));
 }

 @SwaggerLabelMaster
 @ApiOperation("End working with the outfit")
 @PostMapping(value = "/aut/outfitEnt{state}")
 public ResponseEntity<Boolean> outfitEndMaster(@PathVariable State state,@RequestParam  UUID uuidOutfit , @ApiIgnore Principal principal) {
  return ResponseEntity.ok(outfitsServices.outfitStartWork(state, principal.getName(),uuidOutfit));
 }

 @SwaggerLabelMaster
 @ApiOperation("Update outfit by id")
 @PostMapping(value = "/aut/outfitUpdate")
 public ResponseEntity<Boolean> updateOutfitByMaster( @RequestBody OutfitDto outfitDto, @ApiIgnore Principal principal) {
  return ResponseEntity.ok(outfitsServices.updateOutfitByMaster(outfitDto, principal.getName()));
 }
//--Master--//




}
