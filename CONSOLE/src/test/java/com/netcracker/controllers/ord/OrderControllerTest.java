package com.netcracker.controllers.ord;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.CarServiceApplication;
import com.netcracker.DTO.ord.OrderDto;
import com.netcracker.DTO.ord.OrderForm;
import com.netcracker.order.State;
import org.springframework.test.web.servlet.MockMvc;
import com.netcracker.services.OrderServices;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WithMockUser(authorities = "RECEPTIONIST")
@SpringBootTest(classes = CarServiceApplication.class)
class OrderControllerTest {

 @Autowired
 private MockMvc mockMvc;

 @MockBean
 private OrderServices orderServices;

 @Autowired
 private ObjectMapper mapper;

 @Test
 void getOrderOnClient() throws Exception {
  List<OrderDto> masterDto = new ArrayList<>();
  Mockito.when(orderServices.getAllOrderClientsWithState("name", State.REQUEST, 1, 1)).thenReturn(masterDto);
  mockMvc.perform(get("/person/order-request/getAll").param("offset", "1").param("limit", "1")
   .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
   .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
 }


 @Test
 void closeOrderRequest() throws Exception {
  OrderDto masterDto = new OrderDto();
  masterDto.setCarClient(UUID.randomUUID());
  masterDto.setId(UUID.randomUUID());
  Mockito.when(orderServices.cancelRequest(masterDto.getId(), "")).thenReturn(true);
  mockMvc.perform(put("/person/order-close").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
   .content(mapper.writeValueAsString(masterDto)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
 }


 @Test
 void addOrderOnMasterError() throws Exception {
  OrderForm masterDto = new OrderForm();
  Mockito.when(orderServices.addOrderOnMaster(masterDto, "")).thenReturn(UUID.randomUUID());
  mockMvc.perform(post("/details/new-order").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
   .content(mapper.writeValueAsString(masterDto)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
 }

 @Test
 void addOrderOnMaster() throws Exception {
  OrderForm masterDto = new OrderForm();
  masterDto.setIdMasterOutfit(UUID.randomUUID());
  masterDto.setCarClient(UUID.randomUUID());
  masterDto.setIdOrder(UUID.randomUUID());
  masterDto.setDateEntOutfit(new Date());
  masterDto.setDateStartOutfit(new Date());
  masterDto.setNameOutfit("asdd");
  masterDto.setDescription("UUID.randomUUID()");
  Mockito.when(orderServices.addOrderOnMaster(masterDto, "")).thenReturn(UUID.randomUUID());
  mockMvc.perform(post("/details/new-order").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
   .content(mapper.writeValueAsString(masterDto)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
 }

 @Test
 void updateRequestFromClient()  throws Exception {
  OrderForm masterDto = new OrderForm();
  masterDto.setIdMasterOutfit(UUID.randomUUID());
  masterDto.setCarClient(UUID.randomUUID());
  masterDto.setIdOrder(UUID.randomUUID());
  masterDto.setDateEntOutfit(new Date());
  masterDto.setDateStartOutfit(new Date());
  masterDto.setNameOutfit("asdd");
  masterDto.setDescription("UUID.randomUUID()");
  Mockito.when(orderServices.updateRequestFromClient(masterDto, "")).thenReturn(UUID.randomUUID());
  mockMvc.perform(put("/details/request-update").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
   .content(mapper.writeValueAsString(masterDto)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
 }

 @Test
 void updateOrderRequestMasterR() throws Exception{
  OrderDto masterDto = new OrderDto();
  masterDto.setId(UUID.randomUUID());
  masterDto.setCarClient(UUID.randomUUID());
  masterDto.setDescription("UUID.randomUUID()");
  Mockito.when(orderServices.updateOrderOnMasterR(masterDto, "")).thenReturn(true);
  mockMvc.perform(put("/details/order-update").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
   .content(mapper.writeValueAsString(masterDto)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
 }
}