package com.netcracker.controllers.car;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.CarServiceApplication;
import com.netcracker.DTO.basicValidation.CarClientValid;
import com.netcracker.DTO.car.CarClientDto;
import com.netcracker.DTO.car.MarkDto;
import com.netcracker.services.CarServices;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = CarServiceApplication.class)
public class CarControllerTest {

 @Autowired
 private MockMvc mockMvc;

 @MockBean
 private CarServices carServices;

 @MockBean
 private CarClientValid carClientValid;

 @Autowired
 private ObjectMapper mapper;

 @Test
 @WithMockUser(authorities = "REGISTERED")
 void createCarTest() throws Exception {
  CarClientDto car = new CarClientDto();
  Mockito.when(carServices.createCarOnClient(car, "")).thenReturn(UUID.randomUUID());
  Mockito.when(carClientValid.isValid(car, null)).thenReturn(true);
  String json = mapper.writeValueAsString(car);
  mockMvc.perform(post("/person/garage-registration").contentType(MediaType.APPLICATION_JSON)
   .characterEncoding("utf-8")
   .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
 }

 @Test
 @WithMockUser(authorities = "REGISTERED")
 void updateCarTest() throws Exception {
  CarClientDto car = new CarClientDto();
  car.setId(UUID.randomUUID());
  car.setIdClient(UUID.randomUUID());
  car.setMark(MarkDto.builder().id(UUID.fromString("cda01a34-4119-3e5e-9ab9-60b341f234fb")).build());
  car.setYear(new Date());
  Mockito.when(carServices.updateCarClientByLogin(car, "")).thenReturn(true);
  String json = mapper.writeValueAsString(car);
  mockMvc.perform(put("/person/car-update/meta").contentType(MediaType.APPLICATION_JSON)
   .characterEncoding("utf-8")
   .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
 }

 @Test
 @WithMockUser(authorities = "REGISTERED")
 void updateCarClientByIdWithMachineNumberTest() throws Exception {
  CarClientDto car = new CarClientDto();
  car.setId(UUID.randomUUID());
  car.setYear(new Date());
  Mockito.when(carServices.updateCarClientByIdWithMachineNumber(car, "")).thenReturn(true);
  String json = mapper.writeValueAsString(car);
  mockMvc.perform(put("/person/car-update/meta").contentType(MediaType.APPLICATION_JSON)
   .characterEncoding("utf-8")
   .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
 }

 @Test
 @WithMockUser(authorities = "RECEPTIONIST")
 void createCarOnMasterTest() throws Exception {
  CarClientDto car = new CarClientDto();
  car.setId(UUID.randomUUID());
  car.setYear(new Date());
  Mockito.when(carServices.createCarOnMaster(car)).thenReturn(UUID.randomUUID());
  String json = mapper.writeValueAsString(car);
  mockMvc.perform(post("/details/garage-registration")
   .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
   .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
 }

 @Test
 @WithMockUser(authorities = "RECEPTIONIST")
 void getAllCarTest() throws Exception {
  List<CarClientDto> cars = new ArrayList<>();
  CarClientDto car = new CarClientDto();
  cars.add(car);
  Mockito.when(carServices.getAllCarOnMaster()).thenReturn(cars);
  mockMvc.perform(post("/details/garage-registration")
   .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
   .content(mapper.writeValueAsString(car)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
 }

 @Test
 @WithMockUser(authorities = "RECEPTIONIST")
 void searchCarTest() throws Exception {
  List<CarClientDto> cars = new ArrayList<>();
  CarClientDto car = new CarClientDto();
  cars.add(car);
  Mockito.when(carServices.getSearchCarOnMaster("", 0, 1)).thenReturn(cars);
  String json = mapper.writeValueAsString(car);
  mockMvc.perform(post("/details/garage-registration")
   .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
   .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
 }

}