package com.netcracker.controllers.car;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.netcracker.CarServiceApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import com.netcracker.car.Mark;
import com.netcracker.services.MarkServices;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.persistence.EntityNotFoundException;
import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CarServiceApplication.class)
@AutoConfigureMockMvc(addFilters = false)
public class MarkControllerTest {

 @Autowired
 private MockMvc mockMvc;

 @Autowired
 private ObjectMapper objectMapper;

 @MockBean
 private MarkServices markServices;

 @Test
 void getAllMarkTestThenOk() throws Exception {
  mockMvc.perform(
   get("/person/marks").param("offset", "1").param("limit", "1")
    .contentType(MediaType.APPLICATION_JSON)
  ).andExpect(status().isOk());
 }


 @Test
 void getAllMarkOnMasterTestThenOk() throws Exception {
  mockMvc.perform(
   get("/details/marks").param("offset", "1").param("limit", "1")
    .contentType(MediaType.APPLICATION_JSON)
  ).andExpect(status().isOk());
 }

 @Test
 void getMarkByIdTestThenOk() throws Exception {
  mockMvc.perform(
   get("/person/mark/id").param("id", UUID.randomUUID().toString())
    .contentType(MediaType.APPLICATION_JSON)
  ).andExpect(status().isOk());
 }



 @Test
 void whenValidInputGetMarkByIdTestThenOk() throws Exception {
  mockMvc.perform(
   get("/person/mark/id").param("id", UUID.randomUUID().toString())
    .contentType(MediaType.APPLICATION_JSON)
  ).andExpect(status().isOk());
 }

 @Test
 void whenNullValueGetMarkByIdTestThen400() throws Exception {
  mockMvc.perform(
   get("/person/mark/id").param("id", "")
    .contentType(MediaType.APPLICATION_JSON)
  ).andExpect(status().isBadRequest());
 }

 @Test
 void whenValidInputDeleteMarkTestThenOk() throws Exception {
  mockMvc.perform(
   delete("/details/mark")
    .param("id", UUID.randomUUID().toString())
    .contentType(MediaType.APPLICATION_JSON)
  ).andExpect(status().isOk());
 }

 @Test
 void whenNullValueDeleteMarkTestThen400() throws Exception {
  mockMvc.perform(
   delete("/details/mark")
    .param("id", "")
    .contentType(MediaType.APPLICATION_JSON)
  ).andExpect(status().isBadRequest());
 }

 @Test
 void whenValidInputAddMarkTestThen201() throws Exception {
  mockMvc.perform(
   post("/details/add-mark")
    .content(objectMapper.writeValueAsString(Mark.builder()
     .id(UUID.randomUUID())
     .name("name").yearEnd(new Date()).yearStart(new Date()).build()))
    .contentType(MediaType.APPLICATION_JSON)
  ).andExpect(status().isCreated());
 }


}