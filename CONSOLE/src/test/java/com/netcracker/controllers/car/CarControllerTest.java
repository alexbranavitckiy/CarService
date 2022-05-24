package com.netcracker.controllers.car;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.CarServiceApplication;
import com.netcracker.DTO.car.CarClientDto;
import com.netcracker.DTO.car.MarkDto;
import com.netcracker.car.Mark;
import com.netcracker.services.CarServices;
import org.junit.Assert;
import com.netcracker.services.MarkServices;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.junit.Assert.assertThat;
import javax.validation.ConstraintValidatorContext;
import java.time.Duration;
import java.util.*;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@AutoConfigureMockMvc
@SpringBootTest(classes = CarServiceApplication.class)
public class CarControllerTest {

 @Autowired
 private MockMvc mockMvc;

 @MockBean
 private MarkServices markServices;
 @MockBean
 private ConstraintValidatorContext context;

 @Autowired
 private ObjectMapper objectMapper;

 @MockBean
 private CarServices carServices;

 @Autowired
 private ObjectMapper mapper;

 @Test
 @WithMockUser(authorities = "REGISTERED")
 void whenValidInputCreateCarTestThen201() throws Exception {
  UUID uuid=UUID.randomUUID();
  CarClientDto car = new CarClientDto();
  car.setYear(Date.from(new GregorianCalendar(2025, Calendar.JUNE, 25, 5, 0)
   .getTime().toInstant().plus(Duration.ofHours(8))));
  Mark mark = Mark.builder()
   .id(uuid)
   .yearEnd(Date.from(new GregorianCalendar(2026, Calendar.JUNE, 25, 5, 0)
    .getTime().toInstant().plus(Duration.ofHours(6))))
   .yearStart(Date.from(new GregorianCalendar(2024, Calendar.JUNE, 25, 5, 0)
    .getTime().toInstant().plus(Duration.ofHours(9)))).build();
  car.setMark(MarkDto.builder().id(uuid).build());
  car.setId(uuid);
  Mockito.when(markServices.markById(uuid)).thenReturn(Optional.of(mark));
  Mockito.when(carServices.createCarOnClient(car, "")).thenReturn(uuid);
  String json = mapper.writeValueAsString(car);
  mockMvc.perform(post("/person/garage-registration").contentType(MediaType.APPLICATION_JSON)
   .characterEncoding("utf-8")
   .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
 }

 @Test
 @WithMockUser(authorities = "REGISTERED")
 void whenNotValidValueCreateCarTestThen400() throws Exception {
  UUID uuid=UUID.randomUUID();
  CarClientDto car = new CarClientDto();
  car.setYear(Date.from(new GregorianCalendar(2025, Calendar.JUNE, 25, 5, 0)
   .getTime().toInstant().plus(Duration.ofHours(8))));
  Mark mark = Mark.builder()
   .id(uuid)
   .yearEnd(Date.from(new GregorianCalendar(2022, Calendar.JUNE, 25, 5, 0)
    .getTime().toInstant().plus(Duration.ofHours(6))))
   .yearStart(Date.from(new GregorianCalendar(2024, Calendar.JUNE, 25, 5, 0)
    .getTime().toInstant().plus(Duration.ofHours(9)))).build();
  car.setMark(MarkDto.builder().id(uuid).build());
  car.setId(uuid);
  Mockito.when(markServices.markById(uuid)).thenReturn(Optional.of(mark));
  Mockito.when(carServices.createCarOnClient(car, "")).thenReturn(uuid);
  String json = mapper.writeValueAsString(car);
  mockMvc.perform(post("/person/garage-registration").contentType(MediaType.APPLICATION_JSON)
   .characterEncoding("utf-8")
   .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
 }


 @Test
 @WithMockUser(authorities = "RECEPTIONIST")
 void getAllCarTestThenOk() throws Exception {
  List<CarClientDto> cars = new ArrayList<>();
  CarClientDto car = new CarClientDto();
  cars.add(car);
  Mockito.when(carServices.getAllCarOnMaster()).thenReturn(cars);
  mockMvc.perform(get("/details/garage/cars")
   .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").param("offset", "0")
   .param("limit", "1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
 }
 @Test
 @WithMockUser(authorities = "MASTER")
 void getAllCarTestThenIsUnauthorized() throws Exception {
  List<CarClientDto> cars = new ArrayList<>();
  CarClientDto car = new CarClientDto();
  cars.add(car);
  Mockito.when(carServices.getAllCarOnMaster()).thenReturn(cars);
  mockMvc.perform(get("/details/garage/cars")
   .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").param("offset", "0")
   .param("limit", "1").accept(MediaType.APPLICATION_JSON)).andExpect(status().is4xxClientError());
 }

 @Test
 @WithMockUser(authorities = "RECEPTIONIST")
 void searchCarTestThenOk() throws Exception {
  mockMvc.perform(get("/details/garage-search")
   .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
   .param("offset", "0")
   .param("limit", "1")
   .param("search", "a").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
 }

 @Test
 @WithMockUser(authorities = "MASTER")
 void searchCarTestThenIsUnauthorized() throws Exception {
  mockMvc.perform(get("/details/garage-search")
   .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
   .param("offset", "0")
   .param("limit", "1")
   .param("search", "a").accept(MediaType.APPLICATION_JSON)).andExpect(status().is4xxClientError());
 }

}