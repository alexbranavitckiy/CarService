package com.netcracker.controllers.user;


import com.fasterxml.jackson.annotation.JsonView;
import com.netcracker.DTO.user.ValidateClient;
import com.netcracker.DTO.user.ClientDto;
import com.netcracker.DTO.errs.SaveSearchErrorException;
import com.netcracker.DTO.response.ValidationErrorResponse;
import com.netcracker.DTO.response.Violation;
import com.netcracker.DTO.response.ContactConfirmationPayload;
import com.netcracker.security.MyUserDetailsService;
import com.netcracker.security.UserRegister;
import com.netcracker.security.jwt.JWTUtil;
import com.netcracker.services.ClientServices;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

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
 ClientsController(MyUserDetailsService myUserDetailsService, JWTUtil jwtUtil,
                   AuthenticationManager authManager, UserRegister userRegister,
                   ClientServices clientServices) {
  this.userRegister = userRegister;
  this.myUserDetailsService = myUserDetailsService;
  this.jwtUtil = jwtUtil;
  this.authManager = authManager;
  this.clientServices = clientServices;
 }

 @ApiOperation("Method for authentication.")
 @PostMapping({"/registration/perform_login"})
 @ResponseBody
 public Map<String, Object> handleLogin(@Validated({ValidateClient.UiCrossFieldChecks.class})
                                        @RequestBody ContactConfirmationPayload login)
  throws SaveSearchErrorException {
  try {
   UsernamePasswordAuthenticationToken authInputToken =
    new UsernamePasswordAuthenticationToken(login.getLogin(), login.getPassword());
   authManager.authenticate(authInputToken);
   String token = "Bearer:" + jwtUtil.generateToken(login);
   return Collections.singletonMap("Authorization", token);
  } catch (AuthenticationException authExc) {
   throw new SaveSearchErrorException("Invalid Login Credentials", "Login");
  }
 }

 @ApiOperation("Update email")
 @PutMapping(value = "/person/update-date/email", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
  MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<ValidationErrorResponse> updateClientEmail(@Validated({ValidateClient.Email.class})
                                                                  @JsonView(ValidateClient.Email.class)
                                                                  @RequestBody ClientDto clientDto,
                                                                  @ApiIgnore Principal principal)
  throws SaveSearchErrorException {
  ValidationErrorResponse validationResponse = new ValidationErrorResponse();
  clientServices.updateClientEmail(clientDto.getEmail(), principal.getName());
  validationResponse.setViolations(List.of(new Violation("true", "Updates successfully committed")));
  return ResponseEntity.ok(validationResponse);
 }

 @ApiOperation(value = "Update client name")
 @ApiResponses(value = {
  @ApiResponse(code = 200, message = "This user's data was successfully updated",
   response = ValidationErrorResponse.class, responseContainer = "List"),
  @ApiResponse(code = 400, message = "Invalid input", response = ValidationErrorResponse.class,
   responseContainer = "List")})
 @PostMapping(value = "/person/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
  MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<ValidationErrorResponse> updateUser(@Validated({ValidateClient.Name.class})
                                                           @JsonView(ValidateClient.Name.class)
                                                           @RequestBody ClientDto client,
                                                           @ApiIgnore Principal principal)
  throws SaveSearchErrorException {
  ValidationErrorResponse validationResponse = new ValidationErrorResponse();
  clientServices.updateClientName(client, principal.getName());
  validationResponse.setViolations(List.of(new Violation("true", "Updates successfully committed")));
  return ResponseEntity.ok(validationResponse);
 }

 @ApiOperation("Update phone")
 @PutMapping(value = "/person/update-date/phone", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
  MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<ValidationErrorResponse> updateClientPhone(@Validated({ValidateClient.Phone.class})
                                                                  @JsonView(ValidateClient.Phone.class)
                                                                  @RequestBody ClientDto phone,
                                                                  @ApiIgnore Principal principal)
  throws SaveSearchErrorException {
  ValidationErrorResponse validationResponse = new ValidationErrorResponse();
  clientServices.updateClientPhone(phone.getPhone(), principal.getName());
  validationResponse.setViolations(List.of(new Violation("true", "Updates successfully committed")));
  return ResponseEntity.ok(validationResponse);
 }

 @ApiOperation("Update password")
 @PutMapping(value = "/person/update-date/pass", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
  MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<ValidationErrorResponse> updateClientPas(@Validated({ValidateClient.Password.class})
                                                                @JsonView(ValidateClient.Password.class)
                                                                @RequestBody ClientDto pass,
                                                                @ApiIgnore Principal principal)
  throws SaveSearchErrorException {
  ValidationErrorResponse validationResponse = new ValidationErrorResponse();
  clientServices.updateClientPass(pass.getPassword(), principal.getName());
  validationResponse.setViolations(List.of(new Violation("true", "Updates successfully committed")));
  return ResponseEntity.ok(validationResponse);
 }

 @ApiOperation("Update login")
 @PutMapping(value = "/person/update-date/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
  MediaType.APPLICATION_JSON_VALUE)
 public Map<String, Object> updateClientData(@Validated({ValidateClient.Login.class})
                                             @JsonView(ValidateClient.Login.class)
                                             @RequestBody ClientDto login,
                                             @ApiIgnore Principal principal)
  throws SaveSearchErrorException {
  clientServices.updateClientLogin(login.getLogin(), principal.getName());
  String token = "Bearer:" + jwtUtil.generateToken(login.getLogin());
  return Collections.singletonMap("Authorization", token);
 }

 @GetMapping("/person/get-clients")
 @ApiOperation("Get the client logged in")
 @JsonView(ValidateClient.Details.class)
 public ResponseEntity<List<ClientDto>> getClientsOnline(@ApiIgnore Principal principal) {
  Optional<ClientDto> dto = clientServices.getClientDtoByLogin(principal.getName());
  return dto.map(clientDto -> ResponseEntity.ok(List.of(clientDto))).orElseGet(() ->
   ResponseEntity.ok(new ArrayList<>()));
 }

 @ApiOperation(value = "Clients registration")
 @ApiResponses(value = {
  @ApiResponse(code = 200, message = "This user has been successfully registered",
   response = ValidationErrorResponse.class, responseContainer = "List"),
  @ApiResponse(code = 400, message = "Invalid input",
   response = ValidationErrorResponse.class, responseContainer = "List")})
 @PostMapping(value = "/details/registration-client", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
  MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<UUID> createUser(@JsonView(ValidateClient.New.class)
                                        @Validated({ValidateClient.New.class})
                                        @RequestBody ClientDto clients) throws SaveSearchErrorException {
  clients.setPassword(userRegister.newDate(clients.getPhone()));
  return ResponseEntity.ok(clientServices.registrationMaster(clients));
 }

 @JsonView(ValidateClient.Details.class)
 @ApiOperation(value = "Get all clients")
 @GetMapping(value = "/details/clients/get-all")
 public ResponseEntity<List<ClientDto>> getAllClient() throws SaveSearchErrorException {
  return ResponseEntity.ok(clientServices.getAllClientOnMaster());
 }

 @JsonView(ValidateClient.Details.class)
 @ApiOperation(value = "Client search")
 @GetMapping(value = "/details/clients-search")
 public ResponseEntity<List<ClientDto>> searchClient(@RequestParam String search) throws SaveSearchErrorException {
  return ResponseEntity.ok(clientServices.getAllClientOnMaster(search));
 }

}
