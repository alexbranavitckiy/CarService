package com.netcracker.controllers.car;

import com.netcracker.DTO.car.MarkDto;
import com.netcracker.DTO.errs.SaveSearchErrorException;
import com.netcracker.car.Mark;
import com.netcracker.services.MarkServices;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
 @GetMapping({"/person/mark/get-all", "/details/mark/get-all"})
 public ResponseEntity<List<MarkDto>> getAllMark(@RequestParam("offset") Integer offset,
                                              @RequestParam("limit") Integer limit) throws SaveSearchErrorException {
  return ResponseEntity.ok(markServices.getAllMark(offset,limit));
 }

 @ApiOperation("Get all car brands with regex")
 @GetMapping({"/person/mark-search", "/details/mark-search"})
 public ResponseEntity<List<MarkDto>> getAllMark(@RequestParam("offset") Integer offset,
                                              @RequestParam("limit") Integer limit, @RequestParam String regex)
  throws SaveSearchErrorException {
  return ResponseEntity.ok(markServices.getSearchMark(regex,offset,limit));
 }

 @ApiOperation("Get all car brands")
 @GetMapping({"/person/mark/get-by-id", "/details/mark/get-by-id"})
 public ResponseEntity<List<Mark>> getMarkById(@RequestParam UUID id) {
  return ResponseEntity.ok(markServices.getMarkById(id));
 }

 @ApiOperation("Delete car brands")
 @DeleteMapping(value = "/details/delete-mark", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces =
  MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<Boolean> deleteMark(@Validated @RequestParam UUID id) {
  return ResponseEntity.ok(markServices.deleteMark(id));
 }

 @ApiOperation("Add car brands")
 @PostMapping(value = "/details/add-mark", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces =
  MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<Boolean> addMark(@Validated @RequestBody Mark mark) {
  log.info("addMark");
  return ResponseEntity.ok(markServices.addMark(mark));
 }

 @ApiOperation("Add car brands")
 @PostMapping(value = "/details/add-mark", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
 public ResponseEntity<Boolean> addMarkPage(@Validated @RequestBody Mark mark) {
  log.info("addMarkPage");
  return ResponseEntity.ok(markServices.addMark(mark));
 }

}
