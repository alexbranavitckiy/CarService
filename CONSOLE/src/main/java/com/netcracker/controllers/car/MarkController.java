package com.netcracker.controllers.car;

import com.netcracker.DTO.errs.SaveSearchErrorException;
import com.netcracker.car.Mark;
import com.netcracker.services.MarkServices;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class MarkController {

 private final MarkServices markServices;

 @Autowired
 MarkController(MarkServices markServices) {
  this.markServices = markServices;
 }

 @ApiOperation("Get all car brands")
 @GetMapping({"/person/mark/get-all", "/details/mark/get-all"})
 public ResponseEntity<List<Mark>> getAllMark() throws SaveSearchErrorException {
  return ResponseEntity.ok(markServices.getAllMark());
 }

 @ApiOperation("Get all car brands with regex")
 @GetMapping({"/person/mark-search", "/details/mark-search"})
 public ResponseEntity<List<Mark>> getAllMark(@RequestParam String regex) throws SaveSearchErrorException {
  return ResponseEntity.ok(markServices.getSearchMark(regex));
 }

 @ApiOperation("Get all car brands")
 @GetMapping({"/person/mark/get-by-id", "/details/mark/get-by-id"})
 public ResponseEntity<List<Mark>> getMarkById(@RequestParam UUID id) {
  return ResponseEntity.ok(markServices.getMarkById(id));
 }

 @ApiOperation("Delete car brands")
 @DeleteMapping(value = "/details/delete/mark", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces =
  MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<Boolean> deleteMark(@Validated @RequestBody Mark mark) {
  return ResponseEntity.ok(markServices.deleteMark(mark));
 }

 @ApiOperation("Add car brands")
 @PostMapping(value = "/details/add/mark", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces =
  MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<Boolean> addMark(@Validated @RequestBody Mark mark) {
  return ResponseEntity.ok(markServices.addMark(mark));
 }


}
