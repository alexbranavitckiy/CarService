package com.netcracker.controllers.user;

import com.fasterxml.jackson.annotation.JsonView;
import com.netcracker.DTO.user.ClientDto;
import com.netcracker.DTO.user.MasterDto;
import com.netcracker.DTO.user.ValidateClient;
import com.netcracker.DTO.errs.SaveSearchErrorException;
import com.netcracker.DTO.response.ValidationErrorResponse;
import com.netcracker.DTO.response.Violation;
import com.netcracker.security.UserRegister;
import com.netcracker.security.jwt.JWTUtil;
import com.netcracker.services.MasterServices;
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
import java.util.*;

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

 @JsonView({ValidateClient.Details.class})
 @GetMapping({"/aut/get-master", "/details/get-master"})
 @ApiOperation("Get the details of the master logged in")
 public ResponseEntity<List<MasterDto>> getClientsOnline(@ApiIgnore Principal principal) {
  Optional<MasterDto> master = masterServices.getMasterDtoByLogin(principal.getName());
  return master.map(masterDto -> ResponseEntity.ok(List.of(masterDto))).orElseGet(() ->
   ResponseEntity.ok(new ArrayList<>()));
 }

 @ApiOperation("Update login")
 @PutMapping(value = {"/aut/update-date/login", "/details/update-date/login"},
  consumes = MediaType.APPLICATION_JSON_VALUE, produces =
  MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<Map<String, Object>> updateClientData(@Validated({ValidateClient.Login.class})
                                                             @JsonView(ValidateClient.Login.class)
                                                             @RequestBody ClientDto login, @ApiIgnore Principal principal)
  throws SaveSearchErrorException {
  masterServices.updateMasterLogin(login.getLogin(), principal.getName());
  return ResponseEntity.ok(Collections.singletonMap("Authorization", "Bearer:" + jwtUtil.generateToken(login.getLogin())));
 }

 @ApiOperation("Update email")
 @PutMapping(value = {"/aut/update-date/email", "/details/update-date/email"},
  consumes = MediaType.APPLICATION_JSON_VALUE, produces =
  MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<ValidationErrorResponse> updateClientEmail(@Validated({ValidateClient.Email.class})
                                                                  @JsonView(ValidateClient.Email.class)
                                                                  @RequestBody ClientDto clientDto,
                                                                  @ApiIgnore Principal principal)
  throws SaveSearchErrorException {
  ValidationErrorResponse validationResponse = new ValidationErrorResponse();
  masterServices.updateMasterEmail(clientDto.getEmail(), principal.getName());
  validationResponse.setViolations(List.of(new Violation("true", "Updates successfully committed")));
  return ResponseEntity.ok(validationResponse);
 }

 @ApiOperation("Update phone")
 @PutMapping(value = {"/aut/update-date/phone", "/details/update-date/phone"},
  consumes = MediaType.APPLICATION_JSON_VALUE, produces =
  MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<ValidationErrorResponse> updateMasterPhone(@Validated({ValidateClient.Phone.class})
                                                                  @JsonView(ValidateClient.Phone.class)
                                                                  @RequestBody ClientDto phone,
                                                                  @ApiIgnore Principal principal)
  throws SaveSearchErrorException {
  ValidationErrorResponse validationResponse = new ValidationErrorResponse();
  masterServices.updateMasterPhone(phone.getPhone(), principal.getName());
  validationResponse.setViolations(List.of(new Violation("true", "Updates successfully committed")));
  return ResponseEntity.ok(validationResponse);
 }

 @ApiOperation("Update password")
 @PutMapping(value = {"/aut/update-date/pass", "/details/update-date/pass"},
  consumes = MediaType.APPLICATION_JSON_VALUE, produces =
  MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<ValidationErrorResponse> updateClientPas(@Validated @RequestBody String pass,
                                                                @ApiIgnore Principal principal)
  throws SaveSearchErrorException {
  ValidationErrorResponse validationResponse = new ValidationErrorResponse();
  masterServices.updateMasterPass(pass, principal.getName());
  validationResponse.setViolations(List.of(new Violation("true", "Updates successfully committed")));
  return ResponseEntity.ok(validationResponse);
 }

 @ApiOperation("Updating logged in master data")
 @PutMapping(value = {"/aut/update-date/person", "/details/update-date/person"},
  consumes = MediaType.APPLICATION_JSON_VALUE, produces =
  MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<ValidationErrorResponse> updateUser(@JsonView({ValidateClient.Edit.class})
                                                           @Validated({ValidateClient.Edit.class})
                                                           @RequestBody MasterDto masterFormUpdate,
                                                           @ApiIgnore Principal principal)
  throws SaveSearchErrorException {
  ValidationErrorResponse validationResponse = new ValidationErrorResponse();
  masterServices.updateMasterByLogin(masterFormUpdate, principal.getName());
  validationResponse.setViolations(List.of(new Violation("true", "Updates successfully committed")));
  return ResponseEntity.ok(validationResponse);
 }

 @ApiOperation("Create master")
 @PostMapping(value = {"/details/create-person"}, consumes = MediaType.APPLICATION_JSON_VALUE, produces =
  MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<UUID> addUser(@JsonView({ValidateClient.NewAdmin.class})
                                                        @Validated({ValidateClient.NewAdmin.class,
                                                         ValidateClient.NewAdmin.class})
                                                        @RequestBody MasterDto master, @ApiIgnore Principal principal)
  throws SaveSearchErrorException {
  master.setPassword(userRegister.newDate(master.getPassword()));
  return ResponseEntity.ok(masterServices.createMasterOnMasterReceiver(master, principal.getName()));
 }

 @JsonView({ValidateClient.Details.class})
 @GetMapping({"/details/get-masters",})
 @ApiOperation("Get a list of all masters")
 public ResponseEntity<List<MasterDto>> getClientsOnMaster() throws SaveSearchErrorException {
  List<MasterDto> master = masterServices.getMasterDtoOnMaster();
  if (master.size() != 0) return ResponseEntity.ok(master);
  return ResponseEntity.ok(new ArrayList<>());
 }

}
