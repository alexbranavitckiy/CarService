package com.netcracker.controllers.car;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.CarServiceApplication;
import com.netcracker.car.Mark;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CarServiceApplication.class)
@AutoConfigureMockMvc(addFilters = false)
public class MarkControllerTest {

 @Autowired
 private MockMvc mockMvc;

 @Autowired
 private ObjectMapper objectMapper;

 @Test
 void getAllMarkTest() throws Exception {
  mockMvc.perform(
   get("/person/mark/get-all").param("offset","1").param("limit","1")
    .contentType(MediaType.APPLICATION_JSON)
  ).andExpect(status().isOk());
 }


 @Test
 void getAllMarkOnMasterTest() throws Exception {
  mockMvc.perform(
   get("/details/mark/get-all").param("offset","1").param("limit","1")
    .contentType(MediaType.APPLICATION_JSON)
  ).andExpect(status().isOk());
 }


 @Test
 void getMarkByIdTest() throws Exception {
  mockMvc.perform(
   get("/person/mark/get-by-id").param("id", UUID.randomUUID().toString())
    .contentType(MediaType.APPLICATION_JSON)
  ).andExpect(status().isOk());
 }

 @Test
 void deleteMarkTest() throws Exception {
  mockMvc.perform(
   delete("/details/delete-mark")
    .param("id", UUID.randomUUID().toString())
    .contentType(MediaType.APPLICATION_JSON)
  ).andExpect(status().isOk());
 }

 @Test
 void addMarkTest() throws Exception {
  mockMvc.perform(
   post("/details/add-mark")
    .content(objectMapper.writeValueAsString(Mark.builder()
     .id(UUID.randomUUID())
     .name("name").yearEnd(new Date()).yearStart(new Date()).build()))
    .contentType(MediaType.APPLICATION_JSON)
  ).andExpect(status().isOk());
 }

 @Test
 void getAllMarkErrorTest() throws Exception {
  mockMvc.perform(
    get("/person/mark/1")
     .contentType(MediaType.APPLICATION_JSON).param("offset","1").param("limit","1")
   ).andExpect(status().isNotFound())
   .andExpect(mvcResult -> mvcResult.getResolvedException().getClass().equals(EntityNotFoundException.class));
 }
}