package com.netcracker.controllers.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.CarServiceApplication;
import com.netcracker.DTO.response.ContactConfirmationPayload;
import com.netcracker.DTO.user.ClientDto;
import com.netcracker.security.UserRegister;
import com.netcracker.security.jwt.JWTUtil;
import com.netcracker.services.ClientServices;
import com.netcracker.user.Role;
import com.netcracker.user.RoleUser;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WithMockUser(authorities = "RECEPTIONIST")
@SpringBootTest(classes = CarServiceApplication.class)
class ClientsControllerTest {
 @Autowired
 private MockMvc mockMvc;

 @MockBean
 private ClientServices clientServices;
 @MockBean
 private AuthenticationManager authManager;
 @MockBean
 private UserRegister userRegister;

 @Autowired
 JWTUtil jwtUtil;

 @Autowired
 private ObjectMapper mapper;

 @Test
 void handleLogin() throws Exception {
  ContactConfirmationPayload masterDto = new ContactConfirmationPayload("passsss", "login");
  Mockito.when(authManager.authenticate(new UsernamePasswordAuthenticationToken(masterDto.getLogin(), masterDto.getPassword()))).thenReturn(null);
  mockMvc.perform(post("/registration/perform-login").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
   .content(mapper.writeValueAsString(masterDto)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
 }

 @Test
 void handleLoginError() throws Exception {
  ContactConfirmationPayload masterDto = new ContactConfirmationPayload("", "");
  Mockito.when(authManager.authenticate(new UsernamePasswordAuthenticationToken(masterDto.getLogin(), masterDto.getPassword()))).thenReturn(null);
  mockMvc.perform(post("/registration/perform-login").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
   .content(mapper.writeValueAsString(masterDto)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
 }

 @Test
 void updateClientEmail() throws Exception {
  ClientDto clientDto = new ClientDto();
  Mockito.when(clientServices.updateClientEmail("", "")).thenReturn(true);
  mockMvc.perform(put("/person/update-date/email")
   .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
   .content(mapper.writeValueAsString(clientDto)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
  clientDto.setEmail("asdasdsadsa");
  mockMvc.perform(put("/person/update-date/email")
   .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
   .content(mapper.writeValueAsString(clientDto)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
  clientDto.setEmail("alex0377@hotmail.com");
  mockMvc.perform(put("/person/update-date/email")
   .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
   .content(mapper.writeValueAsString(clientDto)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
 }

 @Test
 void updateUser() throws Exception {
  ClientDto clientDto = ClientDto.builder().name("Alex").build();
  Mockito.when(clientServices.updateClientName(clientDto, "")).thenReturn(true);
  mockMvc.perform(post("/person/update")
   .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
   .content(mapper.writeValueAsString(clientDto)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
 }

 @Test
 void updateClientPhone() throws Exception {
  ClientDto clientDto = ClientDto.builder().phone("+375333308979").build();
  Mockito.when(clientServices.updateClientPhone("", "")).thenReturn(true);
  mockMvc.perform(put("/person/update-date/phone")
   .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
   .content(mapper.writeValueAsString(clientDto)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
 }

 @Test
 void updateClientPas() throws Exception {
  ClientDto clientDto = ClientDto.builder().password("375333308979").build();
  Mockito.when(clientServices.updateClientPass("", "")).thenReturn(true);
  mockMvc.perform(put("/person/update-date/pass")
   .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
   .content(mapper.writeValueAsString(clientDto)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
 }

 @Test
 void updateClientData() throws Exception {
  ClientDto clientDto = ClientDto.builder().login("75333308979").build();
  Mockito.when(clientServices.updateClientLogin("", "")).thenReturn(true);
  mockMvc.perform(put("/person/update-date/login")
   .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
   .content(mapper.writeValueAsString(clientDto)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
 }

 @Test
 void getClientsOnline() throws Exception {
  ClientDto clientDto = ClientDto.builder().login("75333308979").build();
  Mockito.when(clientServices.updateClientLogin("", "")).thenReturn(true);
  mockMvc.perform(put("/person/update-date/login")
   .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
   .content(mapper.writeValueAsString(clientDto)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
 }

 @Test
 void createUser() throws Exception {
  UUID ud = UUID.randomUUID();
  ClientDto clientDto = ClientDto.builder()
   .email("alex0377@hotmail.com")
   .phone("+375333308979")
   .name("asdas")
   .login("asdasddsa")
   .description("sadsad")
   .id(ud)
   .roleUser(RoleUser.UNREGISTERED).build();
  Mockito.when(userRegister.registerNewUser(clientDto)).thenReturn(ud);
  Mockito.when(clientServices.registrationMaster(clientDto)).thenReturn(ud);
  mockMvc.perform(post("/details/registration-client")
   .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
   .content(mapper.writeValueAsString(clientDto)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
  clientDto.setPassword("asdsad");
  mockMvc.perform(post("/details/registration-client")
   .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
   .content(mapper.writeValueAsString(clientDto)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
 }


}