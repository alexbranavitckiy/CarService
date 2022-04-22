package com.netcracker.controllers.user;


import com.netcracker.DTO.clients.ClientDto;
import com.netcracker.annotations.ClientLabel;
import com.netcracker.DTO.response.ContactConfirmationPayload;
import com.netcracker.DTO.response.ContactConfirmationResponse;
import com.netcracker.annotations.SwaggerLabelMaster;
import com.netcracker.security.UserRegister;
import com.netcracker.security.jwt.JWTUtil;
import com.netcracker.services.ClientServices;
import com.netcracker.services.OrderServices;
import com.netcracker.user.Client;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
 public RedirectView handleLogin(@ApiParam("User") @RequestParam String username, @ApiParam("Password") @RequestParam String password,
                                 HttpServletResponse httpServletResponse) {
  ContactConfirmationResponse loginResponse = userRegister.jwtLogin(new ContactConfirmationPayload(password, username));
  httpServletResponse.addHeader("token", loginResponse.getResult());
  return new RedirectView("/swagger-ui/");
 }

 @ClientLabel
 @ApiOperation("Update password and login")
 @PostMapping(value = "person/updateDate", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
  MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<Boolean> updateClientData(@Validated @RequestBody
                                                  ContactConfirmationPayload client, @ApiIgnore Principal principal) {
  client.setPassword(userRegister.newDate(client.getPassword()));
  return ResponseEntity.ok(clientServices.updateClientData(client, principal.getName()));
 }

 @ClientLabel
 @ApiOperation("Updating a logged in client")
 @PostMapping(value = "person/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
  MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<Boolean> updateUser(@Validated @RequestBody ClientDto client, @ApiIgnore Principal principal) {
  return ResponseEntity.ok(clientServices.updateClientByLogin(client, principal.getName()));
 }

 @ClientLabel
 @GetMapping("person/getClients")
 @ApiOperation("Get the client logged in")
 public ResponseEntity<List<ClientDto>> getClientsOnline(@ApiIgnore @CookieValue(name = "token", required = false) String token) {
  String login = jwtUtil.extractUsername(token);
  if (login != null) {
   Optional<ClientDto> master = clientServices.getClientDtoByLogin(login);
   if (master.isPresent()) {
    return ResponseEntity.ok(List.of(master.get()));
   }
  }
  return ResponseEntity.ok(new ArrayList<>());
 }

 @GetMapping("/pivot/getAll")
 @ApiOperation("Users list getting operation")
 public ResponseEntity<List<Client>> getAllClients() {
  return ResponseEntity.ok(clientServices.getAllClient());
 }

 @GetMapping("/pivot/byName")
 @ApiOperation(value = "Getting a user by name", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
  MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<List<ClientDto>> getClientsByName(@RequestParam("name") String name) {
  Optional<ClientDto> client = clientServices.getClientsByName(name);
  if (client.isEmpty()) {
   return ResponseEntity.ok(new ArrayList<>());
  }
  return ResponseEntity.ok(List.of(client.get()));
 }

}
