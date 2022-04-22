package com.netcracker.controllers.car;

import com.netcracker.annotations.ClientLabel;
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

 @ClientLabel
 @ApiOperation("Get all car brands")
 @GetMapping("/person/mark/getAll")
 public ResponseEntity<List<Mark>> getAllMark() {
  return ResponseEntity.ok(markServices.getAllMark());
 }

 @ClientLabel
 @ApiOperation("Get all car brands")
 @GetMapping("/person/mark/getById/{id}")
 public ResponseEntity<List<Mark>> getMarkById(@PathVariable UUID id) {
  return ResponseEntity.ok(markServices.getMarkById(id));
 }

 @ApiOperation("Delete car brands")
 @PostMapping(value = "/pivot/details/delete/mark", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces =
  MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<Boolean> deleteMark(@Validated @RequestBody Mark mark) {
  return ResponseEntity.ok(markServices.deleteMark(mark));
 }

 @ApiOperation("Add car brands")
 @PostMapping(value = "/pivot/details/add/mark", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces =
  MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<Boolean> addMark(@Validated @RequestBody Mark mark) {
  return ResponseEntity.ok(markServices.addMark(mark));
 }

}
