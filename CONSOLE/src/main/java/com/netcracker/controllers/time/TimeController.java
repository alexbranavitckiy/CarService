package com.netcracker.controllers.time;


import com.netcracker.DTO.time.TimeDto;
import com.netcracker.DTO.errs.SaveSearchErrorException;
import com.netcracker.services.OutfitsServices;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@ApiOperation("Controller for work with records for repairs")
public class TimeController {

 private final OutfitsServices outfitsServices;

 @Autowired
 public TimeController(OutfitsServices outfitsServices) {
  this.outfitsServices = outfitsServices;
 }

 @ApiOperation(value = "Get all clients")
 @GetMapping(value = "details/time/get-all")
 public ResponseEntity<List<TimeDto>> getAllClient() throws SaveSearchErrorException {
  return ResponseEntity.ok(outfitsServices.getAllOutfitByTime());
 }


}
