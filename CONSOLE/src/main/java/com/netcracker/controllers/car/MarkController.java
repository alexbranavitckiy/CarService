package com.netcracker.controllers.car;

import com.netcracker.DTO.car.MarkDto;
import com.netcracker.DTO.errs.SaveSearchErrorException;
import com.netcracker.car.Mark;
import com.netcracker.services.MarkServices;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
public class MarkController {

 private final MarkServices markServices;

 @Autowired
 MarkController(MarkServices markServices) {
  this.markServices = markServices;
 }

 @ApiOperation("Get all car brands")
 @GetMapping({"/person/marks", "/details/marks"})
 public ResponseEntity<List<MarkDto>> getAllMark(@RequestParam("offset") Integer offset,
                                                 @RequestParam("limit") Integer limit) throws SaveSearchErrorException {
  return ResponseEntity.ok(markServices.getAllMark(offset, limit));
 }

 @ApiOperation("Get all car brands with regex")
 @GetMapping({"/person/mark-search", "/details/mark-search"})
 public ResponseEntity<List<MarkDto>> getAllMark(@RequestParam("offset") Integer offset,
                                                 @RequestParam("limit") Integer limit, @RequestParam String regex)
  throws SaveSearchErrorException {
  return ResponseEntity.ok(markServices.getSearchMark(regex, offset, limit));
 }

 @ApiOperation("Get all car brands")
 @GetMapping({"/person/mark/id", "/details/mark/id"})
 public ResponseEntity<List<Mark>> getMarkById(@RequestParam UUID id) {
  return ResponseEntity.status(200).body(markServices.getMarkById(id));
 }

 @ApiOperation("Delete car brands")
 @DeleteMapping(value = "/details/mark", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces =
  MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<Boolean> deleteMark(@Validated @NotNull @RequestParam UUID id) {
  return ResponseEntity.ok(markServices.deleteMark(id));
 }

 @ApiOperation("Add car brands")
 @PostMapping(value = "/details/add-mark", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces =
  MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<Boolean> addMark(@Validated @RequestBody Mark mark) {
  return ResponseEntity.status(HttpStatus.CREATED).body(markServices.addMark(mark));
 }

}
