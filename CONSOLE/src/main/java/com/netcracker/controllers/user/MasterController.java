package com.netcracker.controllers.user;

import com.fasterxml.jackson.annotation.JsonView;
import com.netcracker.DTO.clients.MasterDto;
import com.netcracker.DTO.clients.ValidateClient;
import com.netcracker.DTO.errs.SaveSearchErrorException;
import com.netcracker.DTO.response.ContactConfirmationPayload;
import com.netcracker.DTO.response.ValidationErrorResponse;
import com.netcracker.DTO.response.Violation;
import com.netcracker.annotations.ClientLabel;
import com.netcracker.annotations.SwaggerLabelMaster;
import com.netcracker.annotations.SwaggerLabelMasterReceiver;
import com.netcracker.security.UserRegister;
import com.netcracker.security.jwt.JWTUtil;
import com.netcracker.services.MasterServices;
import com.netcracker.user.Master;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Api(tags = "Master")
@RestController
@ApiOperation("API for the master")
public class MasterController {

 private final MasterServices masterServices;
 private final UserRegister userRegister;
 private final JWTUtil jwtUtil;

 @Autowired
 public MasterController(JWTUtil jwtUtil, UserRegister userRegister, MasterServices masterServices) {
  this.masterServices = masterServices;
  this.jwtUtil = jwtUtil;
  this.userRegister = userRegister;
 }

 //--Master--//
 @JsonView({ValidateClient.Details.class})
 @SwaggerLabelMaster
 @GetMapping("aut/get-master")
 @ApiOperation("Get the details of the master  logged in")
 public ResponseEntity<List<MasterDto>> getClientsOnline(@ApiIgnore Principal principal) {
  Optional<MasterDto> master = masterServices.getMasterDtoByLogin(principal.getName());
  return master.map(masterDto -> ResponseEntity.ok(List.of(masterDto))).orElseGet(() -> ResponseEntity.ok(new ArrayList<>()));
 }

 @SwaggerLabelMaster
 @ApiOperation("Update login")
 @PutMapping(value = "aut/update-date/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
  MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<ValidationErrorResponse> updateClientData(@Validated @RequestBody
                                                                  String login, @ApiIgnore Principal principal)
  throws SaveSearchErrorException {
  ValidationErrorResponse validationResponse = new ValidationErrorResponse();
  masterServices.updateMasterLogin(login, principal.getName());
  validationResponse.setViolations(List.of(new Violation("true", "Updates successfully committed")));
  return ResponseEntity.ok(validationResponse);
 }

 @SwaggerLabelMaster
 @ApiOperation("Update email")
 @PutMapping(value = "aut/update-date/email", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
  MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<ValidationErrorResponse> updateClientEmail(@Validated @RequestBody String email, @ApiIgnore Principal principal)
  throws SaveSearchErrorException {
  ValidationErrorResponse validationResponse = new ValidationErrorResponse();
  masterServices.updateMasterEmail(email, principal.getName());
  validationResponse.setViolations(List.of(new Violation("true", "Updates successfully committed")));
  return ResponseEntity.ok(validationResponse);
 }


 @SwaggerLabelMaster
 @ApiOperation("Update phone")
 @PutMapping(value = "aut/update-date/phone", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
  MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<ValidationErrorResponse> updateMasterPhone(@Validated @RequestBody String phone, @ApiIgnore Principal principal) throws SaveSearchErrorException {
  ValidationErrorResponse validationResponse = new ValidationErrorResponse();
  masterServices.updateMasterPhone(phone, principal.getName());
  validationResponse.setViolations(List.of(new Violation("true", "Updates successfully committed")));
  return ResponseEntity.ok(validationResponse);
 }

 @SwaggerLabelMaster
 @ApiOperation("Update password")
 @PutMapping(value = "aut/update-date/pass", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
  MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<ValidationErrorResponse> updateClientPas(@Validated @RequestBody String pass, @ApiIgnore Principal principal)
  throws SaveSearchErrorException {
  ValidationErrorResponse validationResponse = new ValidationErrorResponse();
  masterServices.updateMasterPass(pass, principal.getName());
  validationResponse.setViolations(List.of(new Violation("true", "Updates successfully committed")));
  return ResponseEntity.ok(validationResponse);
 }

 @SwaggerLabelMaster

 @ApiOperation("Updating logged in master data")
 @PutMapping(value = "aut/update-date/person", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
  MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<ValidationErrorResponse> updateUser(@JsonView({ValidateClient.Edit.class}) @Validated({ValidateClient.Edit.class}) @RequestBody MasterDto masterFormUpdate, @ApiIgnore Principal principal) throws SaveSearchErrorException {
  ValidationErrorResponse validationResponse = new ValidationErrorResponse();
  masterServices.updateMasterByLogin(masterFormUpdate, principal.getName());
  validationResponse.setViolations(List.of(new Violation("true", "Updates successfully committed")));
  return ResponseEntity.ok(validationResponse);
 }

//--Master--//


// @SwaggerLabelMaster
// @ApiOperation("Update password and login master")
// @PostMapping(value = "/aut/update-date", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
//  MediaType.APPLICATION_JSON_VALUE)
// public ResponseEntity<Boolean> updateClientData(@Validated @RequestBody
//                                                  ContactConfirmationPayload client, @ApiIgnore Principal principal) {
//  client.setPassword(userRegister.newDate(client.getPassword()));
//  return ResponseEntity.ok(masterServices.updateMasterData(client, principal.getName()));
// }


// @SwaggerLabelMasterReceiver
// @ApiOperation("Master registration")
// @PostMapping(value = "/pivot/details/masterRegistration", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
//  MediaType.APPLICATION_JSON_VALUE)
// public ResponseEntity<Boolean> createMaster(@RequestBody Master master) {
//  return ResponseEntity.ok(userRegister.registerNewMaster(master));
// }
//
// @SwaggerLabelMasterReceiver
// @GetMapping("/pivot/details/getAllMaster")
// @ApiOperation("Get a list of all masters")
// public ResponseEntity<List<MasterDto>> getAllMaster() {
//  return ResponseEntity.ok(masterServices.getAllMasterDto());
// }

// @SwaggerLabelMasterReceiver
// @ApiOperation("Updating logged in master data")
// @PostMapping(value = "/pivot/details/master/update", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
// public ResponseEntity<Boolean> updateMaster(@RequestBody MasterDto master, @ApiIgnore Principal principal) {
//  return ResponseEntity.ok(masterServices.updateMaster(master, principal.getName()));
// }

}