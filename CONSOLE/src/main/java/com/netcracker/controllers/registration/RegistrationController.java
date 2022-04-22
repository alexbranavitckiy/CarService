package com.netcracker.controllers.registration;

import com.netcracker.DTO.response.ContactConfirmationResponse;
import com.netcracker.security.UserRegister;
import com.netcracker.user.Client;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
public class RegistrationController {

 private final UserRegister userRegister;

 @Autowired
 RegistrationController(UserRegister userRegister) {
  this.userRegister = userRegister;
 }

 @ApiOperation("Clients registration")
 @PostMapping(value = "/registration", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
  MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<Boolean> createUser(@Validated @RequestBody Client clients) {
  return ResponseEntity.ok(userRegister.registerNewUser(clients));
 }

}
