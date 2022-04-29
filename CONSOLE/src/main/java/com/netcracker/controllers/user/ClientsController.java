package com.netcracker.controllers.user;


import com.fasterxml.jackson.annotation.JsonView;
import com.netcracker.DTO.clients.ValidateClient;
import com.netcracker.DTO.clients.ClientDto;
import com.netcracker.DTO.errs.SaveSearchErrorException;
import com.netcracker.DTO.response.ValidationErrorResponse;
import com.netcracker.DTO.response.Violation;
import com.netcracker.annotations.ClientLabel;
import com.netcracker.DTO.response.ContactConfirmationPayload;
import com.netcracker.DTO.response.ContactConfirmationResponse;
import com.netcracker.security.UserRegister;
import com.netcracker.security.jwt.JWTUtil;
import com.netcracker.services.ClientServices;
import com.netcracker.services.OrderServices;
import com.netcracker.user.Client;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Api(tags = "Clients")
@RestController
@ApiOperation("API for customer service")
public class ClientsController {

 private final JWTUtil jwtUtil;
 private final ClientServices clientServices;
 private final UserRegister userRegister;
 private final OrderServices orderServices;

 @Autowired
 ClientsController(JWTUtil jwtUtil, OrderServices orderServices, UserRegister userRegister, ClientServices clientServices) {
  this.userRegister = userRegister;
  this.jwtUtil = jwtUtil;
  this.orderServices = orderServices;
  this.clientServices = clientServices;
 }

 @ClientLabel
 @ApiOperation("Logout.")
 @GetMapping("/person/logout")
 public void fakeLogout() {
  throw new IllegalStateException("This method shouldn't be called. It's implemented by Spring Security filters.");
 }

 @ClientLabel
 @ApiOperation("Login.")
 @PostMapping({"/perform_login"})
 @ResponseBody
 public RedirectView handleLogin(@ApiParam("User") @RequestParam String login, @ApiParam("Password") @RequestParam String password,
                                 HttpServletResponse httpServletResponse) {
  ContactConfirmationResponse loginResponse = userRegister.jwtLogin(new ContactConfirmationPayload(password, login));
  Cookie cookie = new Cookie("token", loginResponse.getResult());
  httpServletResponse.addCookie(cookie);
  return new RedirectView("/swagger-ui/");
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

// @GetMapping("/pivot/get-all")
// @ApiOperation("Users list getting operation")
// public ResponseEntity<List<Client>> getAllClients() {
//  return ResponseEntity.ok(clientServices.getAllClient());
// }

// @GetMapping("/pivot/byName")
// @ApiOperation(value = "Getting a user by name", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
//  MediaType.APPLICATION_JSON_VALUE)
// public ResponseEntity<List<ClientDto>> getClientsByName(@RequestParam("name") String name) {
//  Optional<ClientDto> client = clientServices.getClientsByName(name);
//  if (client.isEmpty()) {
//   return ResponseEntity.ok(new ArrayList<>());
//  }
//  return ResponseEntity.ok(List.of(client.get()));
// }

}
