package com.netcracker.controllers.user;

import com.netcracker.DTO.clients.MasterDto;
import com.netcracker.DTO.response.ContactConfirmationPayload;
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
 private MasterController(JWTUtil jwtUtil, UserRegister userRegister, MasterServices masterServices) {
  this.masterServices = masterServices;
  this.jwtUtil = jwtUtil;
  this.userRegister = userRegister;
 }

 @SwaggerLabelMaster
 @ApiOperation("Update password and login master")
 @PostMapping(value = "/aut/updateDate", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
  MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<Boolean> updateClientData(@Validated @RequestBody
                                                  ContactConfirmationPayload client, @ApiIgnore Principal principal) {
  client.setPassword(userRegister.newDate(client.getPassword()));
  return ResponseEntity.ok(masterServices.updateMasterData(client, principal.getName()));
 }

 @SwaggerLabelMaster
 @ApiOperation("Updating logged in master data")
 @PostMapping(value = "aut/update/personDate", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
  MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<Boolean> updateUser(@Validated @RequestBody MasterDto masterFormUpdate, @ApiIgnore Principal principal) {
  return ResponseEntity.ok(masterServices.updateMasterByLogin(masterFormUpdate, principal.getName()));
 }

 @SwaggerLabelMasterReceiver
 @ApiOperation("Updating logged in master data")
 @PostMapping(value = "/pivot/details/master/update", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
 public ResponseEntity<Boolean> updateMaster(@RequestBody MasterDto master, @ApiIgnore Principal principal) {
  return ResponseEntity.ok(masterServices.updateMaster(master, principal.getName()));
 }



 @SwaggerLabelMaster
 @GetMapping("aut/getMaster")
 @ApiOperation("Get the details of the master  logged in")
 public ResponseEntity<List<MasterDto>> getClientsOnline(@ApiIgnore @CookieValue(name = "token", required = false) String token) {
  String login = jwtUtil.extractUsername(token);
  if (login != null) {
   Optional<MasterDto> master = masterServices.getMasterDtoByLogin(login);
   if (master.isPresent()) {
    return ResponseEntity.ok(List.of(master.get()));
   }
  }
  return ResponseEntity.ok(new ArrayList<>());
 }

 @SwaggerLabelMasterReceiver
 @ApiOperation("Master registration")
 @PostMapping(value = "/pivot/details/masterRegistration", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
  MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<Boolean> createMaster(@RequestBody Master master) {
  return ResponseEntity.ok(userRegister.registerNewMaster(master));
 }

 @SwaggerLabelMasterReceiver
 @GetMapping("/pivot/details/getAllMaster")
 @ApiOperation("Get a list of all masters")
 public ResponseEntity<List<MasterDto>> getAllMaster() {
  return ResponseEntity.ok(masterServices.getAllMasterDto());
 }



}
