package com.netcracker.controllers.registration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.CarServiceApplication;
import com.netcracker.DTO.user.ClientDto;
import com.netcracker.repository.ClientsRepository;
import com.netcracker.user.RoleUser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CarServiceApplication.class)
@AutoConfigureMockMvc
public class RegistrationControllerTest {

 @Autowired
 private MockMvc mockMvc;

 @Autowired
 private ObjectMapper objectMapper;

 @Autowired
 private ClientsRepository clientsRepository;

 @AfterEach
 public void resetDb() {
  clientsRepository.deleteAll();
 }

 @Test
 void createUser() throws Exception {
  ClientDto client = ClientDto.builder()
   .name("Alex")
   .description("")
   .password("password")
   .email("alex0377@hotmail.com")
   .roleUser(RoleUser.UNREGISTERED)
   .login("LoginL")
   .phone("+375333308979").build();
  mockMvc.perform(
    post("/registration")
     .content(objectMapper.writeValueAsString(client))
     .contentType(MediaType.APPLICATION_JSON)
   )
   .andExpect(status().isOk());
 }


}