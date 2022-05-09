package com.netcracker.controllers.registration;

import com.fasterxml.jackson.annotation.JsonView;
import com.netcracker.DTO.user.ValidateClient;
import com.netcracker.DTO.user.ClientDto;
import com.netcracker.DTO.errs.SaveSearchErrorException;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
 public ResponseEntity<ValidationErrorResponse> createUser(@JsonView(ValidateClient.New.class) @Validated({ValidateClient.New.class, ValidateClient.UiCrossFieldChecks.class}) @RequestBody ClientDto clients) throws SaveSearchErrorException {
  ValidationErrorResponse validationResponse = new ValidationErrorResponse();
  userRegister.registerNewUser(clients);
  validationResponse.setViolations(List.of(new Violation("true", "This user has been successfully registered")));
  return ResponseEntity.ok(validationResponse);
 }


}
