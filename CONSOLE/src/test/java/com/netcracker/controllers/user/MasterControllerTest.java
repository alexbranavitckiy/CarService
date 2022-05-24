package com.netcracker.controllers.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.CarServiceApplication;
import com.netcracker.DTO.user.ClientDto;
import com.netcracker.DTO.user.MasterDto;
import com.netcracker.services.MasterServices;
import com.netcracker.user.Role;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = CarServiceApplication.class)
class MasterControllerTest {

 @Autowired
 private MockMvc mockMvc;

 @MockBean
 private MasterServices masterServices;


 @Autowired
 private ObjectMapper mapper;

 @Test
 @WithMockUser(authorities = "MASTER")
 void getClientsOnlineTest() throws Exception {
  MasterDto masterDto = new MasterDto();
  Mockito.when(masterServices.getMasterDtoByLogin("")).thenReturn(Optional.of(masterDto));
  mockMvc.perform(get("/aut/get-master").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
   .content(mapper.writeValueAsString(masterDto)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
 }

 @Test
 @WithMockUser(authorities = "MASTER")
 void updateClientDataTest() throws Exception {
  ClientDto clientDto = ClientDto.builder().login("alex").build();
  Mockito.when(masterServices.updateMasterLogin(clientDto.getLogin(), "")).thenReturn(true);
  mockMvc.perform(put("/aut/update-date/login")
   .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
   .content(mapper.writeValueAsString(clientDto)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
 }

 @Test
 @WithMockUser(authorities = "MASTER")
 void updateClientEmailTest() throws Exception {
  ClientDto clientDto = ClientDto.builder().email("").build();
  Mockito.when(masterServices.updateMasterEmail(clientDto.getEmail(), "")).thenReturn(true);
  mockMvc.perform(put("/aut/update-date/email")
   .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
   .content(mapper.writeValueAsString(clientDto)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
  clientDto.setEmail("alex0377@hotmail.com");
  Mockito.when(masterServices.updateMasterEmail(clientDto.getEmail(), "")).thenReturn(true);
  mockMvc.perform(put("/aut/update-date/email")
   .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
   .content(mapper.writeValueAsString(clientDto)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
 }

 @Test
 @WithMockUser(authorities = "MASTER")
 void updateMasterPhoneTest() throws Exception {
  ClientDto clientDto = ClientDto.builder().phone("+375333308979").build();
  Mockito.when(masterServices.updateMasterPhone(clientDto.getPhone(), "")).thenReturn(true);
  mockMvc.perform(put("/aut/update-date/phone")
   .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
   .content(mapper.writeValueAsString(clientDto)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
 }

 @Test
 @WithMockUser(authorities = "MASTER")
 void updateUserTest() throws Exception {
  MasterDto masterDto = MasterDto.builder().mail("alex03@hotmail.com")
   .role(Role.RECEPTIONIST).description("asdasd")
   .homeAddress("asdsadasd")
   .name("Alex").education("Edsdfd").login("Alex").password("asdasdsdad").phone("+375333308979").build();
  Mockito.when(masterServices.updateMasterByLogin(masterDto, "")).thenReturn(true);
  mockMvc.perform(put("/aut/update-date/person").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
   .content(mapper.writeValueAsString(masterDto)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
 }

 @Test
 @WithMockUser(authorities = "RECEPTIONIST")
 void addUserTest() throws Exception {
  MasterDto masterDto = MasterDto.builder().mail("alex03@hotmail.com")
   .role(Role.RECEPTIONIST)
   .name("Alex").education("Edsdfd").login("Alex").password("asdasdsdad").phone("+375333308979").build();
  Mockito.when(masterServices.createMasterOnMasterReceiver(masterDto, "")).thenReturn(UUID.randomUUID());
  mockMvc.perform(post("/details/create-person").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
   .content(mapper.writeValueAsString(masterDto)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
 }

 @Test
 @WithMockUser(authorities = "RECEPTIONIST")
 void addUserErrorTest() throws Exception {
  MasterDto masterDto = new MasterDto();
  Mockito.when(masterServices.createMasterOnMasterReceiver(masterDto, "")).thenReturn(UUID.randomUUID());
  mockMvc.perform(post("/details/create-person").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
   .content(mapper.writeValueAsString(masterDto)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
 }

 @Test
 @WithMockUser(authorities = "RECEPTIONIST")
 void getClientsOnMasterTest() throws Exception {
  List masterDto = new ArrayList<>();
  Mockito.when(masterServices.getMasterDtoOnMaster()).thenReturn(new ArrayList<>());
  mockMvc.perform(get("/details/masters").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
   .content(mapper.writeValueAsString(masterDto)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
 }

}