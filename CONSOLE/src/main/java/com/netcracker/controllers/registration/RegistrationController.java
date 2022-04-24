package com.netcracker.controllers.registration;

import com.fasterxml.jackson.annotation.JsonView;
import com.netcracker.DTO.Validate;
import com.netcracker.DTO.clients.ClientDto;
import com.netcracker.DTO.errs.SaveErrorException;
import com.netcracker.DTO.response.ValidationErrorResponse;
import com.netcracker.DTO.response.Violation;
import com.netcracker.security.UserRegister;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@ApiOperation("Controller for registering new users")
public class RegistrationController {

 private final UserRegister userRegister;

 @Autowired
 RegistrationController(UserRegister userRegister) {
  this.userRegister = userRegister;
 }


 @ApiOperation(value = "Clients registration")
 @ApiResponses(value = {
  @ApiResponse(code = 200, message = "This user has been successfully registered", response = ValidationErrorResponse.class, responseContainer = "List"),
  @ApiResponse(code = 400, message = "Invalid input", response = ValidationErrorResponse.class, responseContainer = "List")})
 @PostMapping(value = "/registration", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
  MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<ValidationErrorResponse> createUser(@JsonView(Validate.Registration.class) @Validated({Validate.New.class, Validate.UiCrossFieldChecks.class}) @RequestBody ClientDto clients) {
  ValidationErrorResponse validationResponse = new ValidationErrorResponse();
  try {
   userRegister.registerNewUser(clients);
   validationResponse.setViolations(List.of(new Violation("true", "This user has been successfully registered")));
  } catch (SaveErrorException s) {
   validationResponse.setViolations(List.of(new Violation("false", s.getMessage())));
  }
  return ResponseEntity.ok(validationResponse);
 }
}
