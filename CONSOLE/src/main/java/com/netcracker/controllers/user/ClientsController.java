package com.netcracker.controllers.user;


import com.fasterxml.jackson.annotation.JsonView;
import com.netcracker.DTO.user.ValidateClient;
import com.netcracker.DTO.user.ClientDto;
import com.netcracker.DTO.errs.SaveSearchErrorException;
import com.netcracker.DTO.response.ValidationErrorResponse;
import com.netcracker.DTO.response.Violation;
import com.netcracker.annotations.ClientLabel;
import com.netcracker.DTO.response.ContactConfirmationPayload;
import com.netcracker.DTO.response.ContactConfirmationResponse;
import com.netcracker.security.MyUserDetailsService;
import com.netcracker.security.UserRegister;
import com.netcracker.security.jwt.JWTUtil;
import com.netcracker.services.ClientServices;
import com.netcracker.services.OrderServices;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.*;

@Slf4j
@Api(tags = "Clients")
@RestController
@ApiOperation("API for customer service")
public class ClientsController {

 private final JWTUtil jwtUtil;
 private final ClientServices clientServices;
 private final UserRegister userRegister;
 private AuthenticationManager authManager;
 private final MyUserDetailsService myUserDetailsService;

 @Autowired
 ClientsController(MyUserDetailsService myUserDetailsService, JWTUtil jwtUtil, AuthenticationManager authManager, UserRegister userRegister, ClientServices clientServices) {
  this.userRegister = userRegister;
  this.myUserDetailsService = myUserDetailsService;
  this.jwtUtil = jwtUtil;
  this.authManager = authManager;
  this.clientServices = clientServices;
 }

 @ClientLabel
 @ApiOperation("Login.")
 @PostMapping({"/perform_login"})
 @ResponseBody
 public Map<String, Object> handleLogin(@RequestBody ContactConfirmationPayload login) {
  try {
   UsernamePasswordAuthenticationToken authInputToken =
    new UsernamePasswordAuthenticationToken(login.getLogin(), login.getPassword());
   authManager.authenticate(authInputToken);
   String token = "Bearer:" + jwtUtil.generateToken(login);
   return Collections.singletonMap("Authorization", token);
  } catch (AuthenticationException authExc) {
   throw new RuntimeException("Invalid Login Credentials");
  }
 }

 @ClientLabel
 @ApiOperation("Update email")
 @PutMapping(value = "person/update-date/email", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
  MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<ValidationErrorResponse> updateClientEmail(@Validated @RequestBody String email, @ApiIgnore Principal principal)
  throws SaveSearchErrorException {
  ValidationErrorResponse validationResponse = new ValidationErrorResponse();
  clientServices.updateClientEmail(email, principal.getName());
  validationResponse.setViolations(List.of(new Violation("true", "Updates successfully committed")));
  return ResponseEntity.ok(validationResponse);
 }

 @ClientLabel
 @ApiOperation(value = "Update client name")
 @ApiResponses(value = {
  @ApiResponse(code = 200, message = "This user's data was successfully updated", response = ValidationErrorResponse.class, responseContainer = "List"),
  @ApiResponse(code = 400, message = "Invalid input", response = ValidationErrorResponse.class, responseContainer = "List")})
 @PostMapping(value = "person/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
  MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<ValidationErrorResponse> updateUser(@Validated({ValidateClient.Edit.class}) @JsonView(ValidateClient.Edit.class) @RequestBody ClientDto client, @ApiIgnore Principal principal) throws SaveSearchErrorException {
  ValidationErrorResponse validationResponse = new ValidationErrorResponse();
  clientServices.updateClientName(client, principal.getName());
  validationResponse.setViolations(List.of(new Violation("true", "Updates successfully committed")));
  return ResponseEntity.ok(validationResponse);
 }

 @ClientLabel
 @ApiOperation("Update phone")
 @PutMapping(value = "person/update-date/phone", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
  MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<ValidationErrorResponse> updateClientPhone(@Validated @RequestBody String phone, @ApiIgnore Principal principal) throws SaveSearchErrorException {
  ValidationErrorResponse validationResponse = new ValidationErrorResponse();
  clientServices.updateClientPhone(phone, principal.getName());
  validationResponse.setViolations(List.of(new Violation("true", "Updates successfully committed")));
  return ResponseEntity.ok(validationResponse);
 }

 @ClientLabel
 @ApiOperation("Update password")
 @PutMapping(value = "person/update-date/pass", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
  MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<ValidationErrorResponse> updateClientPas(@Validated @RequestBody String pass, @ApiIgnore Principal principal)
  throws SaveSearchErrorException {
  ValidationErrorResponse validationResponse = new ValidationErrorResponse();
  clientServices.updateClientPass(pass, principal.getName());
  validationResponse.setViolations(List.of(new Violation("true", "Updates successfully committed")));
  return ResponseEntity.ok(validationResponse);
 }

 @ClientLabel
 @ApiOperation("Update login")
 @PutMapping(value = "person/update-date/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
  MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<ValidationErrorResponse> updateClientData(@Validated @RequestBody
                                                                  String login, @ApiIgnore Principal principal)
  throws SaveSearchErrorException {
  ValidationErrorResponse validationResponse = new ValidationErrorResponse();
  clientServices.updateClientLogin(login, principal.getName());
  validationResponse.setViolations(List.of(new Violation("true", "Updates successfully committed")));
  return ResponseEntity.ok(validationResponse);
 }

 @ClientLabel
 @GetMapping("person/get-clients")
 @ApiOperation("Get the client logged in")
 @JsonView(ValidateClient.Details.class)
 public ResponseEntity<List<ClientDto>> getClientsOnline(@ApiIgnore Principal principal) {
  Optional<ClientDto> dto = clientServices.getClientDtoByLogin(principal.getName());
  return dto.map(clientDto -> ResponseEntity.ok(List.of(clientDto))).orElseGet(() -> ResponseEntity.ok(new ArrayList<>()));
 }

 @ApiOperation(value = "Clients registration")
 @ApiResponses(value = {
  @ApiResponse(code = 200, message = "This user has been successfully registered", response = ValidationErrorResponse.class, responseContainer = "List"),
  @ApiResponse(code = 400, message = "Invalid input", response = ValidationErrorResponse.class, responseContainer = "List")})
 @PostMapping(value = "details/registration-client", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
  MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<UUID> createUser(@JsonView(ValidateClient.masterRequest.class) @Validated({ValidateClient.masterRequest.class}) @RequestBody ClientDto clients) throws SaveSearchErrorException {
  clients.setPassword(userRegister.newDate(clients.getPhone()));
  return ResponseEntity.ok(clientServices.registrationMaster(clients));
 }

 @JsonView(ValidateClient.Details.class)
 @ApiOperation(value = "Get all clients")
 @GetMapping(value = "details/clients/get-all")
 public ResponseEntity<List<ClientDto>> getAllClient() throws SaveSearchErrorException {
  return ResponseEntity.ok(clientServices.getAllClientOnMaster());
 }

 @JsonView(ValidateClient.Details.class)
 @ApiOperation(value = "Client search")
 @GetMapping(value = "details/clients-search")
 public ResponseEntity<List<ClientDto>> searchClient(@RequestParam String search) throws SaveSearchErrorException {
  return ResponseEntity.ok(clientServices.getAllClientOnMaster(search));
 }

}
