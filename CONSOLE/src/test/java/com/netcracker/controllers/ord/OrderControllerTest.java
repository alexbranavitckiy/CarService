package com.netcracker.controllers.ord;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.CarServiceApplication;
import com.netcracker.DTO.ord.OrderDto;
import com.netcracker.DTO.ord.OrderForm;
import com.netcracker.order.State;
import com.netcracker.services.OutfitsServices;
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

import java.time.Duration;
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

 @MockBean
 private OutfitsServices outfitsServices;

 @Autowired
 private ObjectMapper mapper;

 @Test
 @WithMockUser(authorities = "REGISTERED")
 void getOrderOnClientTest() throws Exception {
  List<OrderDto> masterDto = new ArrayList<>();
  Mockito.when(orderServices.getAllOrderClientsWithState("name", State.REQUEST, 1, 1))
   .thenReturn(masterDto);
  mockMvc.perform(get("/person/orders").param("offset", "1")
   .param("limit", "1").param("state", State.REQUEST.getCode())
   .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
   .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
 }


 @Test
 @WithMockUser(authorities = "REGISTERED")
 void closeOrderRequestTest() throws Exception {
  OrderDto masterDto = new OrderDto();
  masterDto.setCarClient(UUID.randomUUID());
  masterDto.setId(UUID.randomUUID());
  Mockito.when(orderServices.cancelRequest(masterDto.getId(), "")).thenReturn(true);
  mockMvc.perform(put("/person/close-order").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
   .content(mapper.writeValueAsString(masterDto)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
 }


 @Test
 void addOrderOnMasterErrorTest() throws Exception {
  OrderForm masterDto = new OrderForm();
  Mockito.when(orderServices.addOrderOnMaster(masterDto, "")).thenReturn(UUID.randomUUID());
  mockMvc.perform(post("/details/new-order").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
   .content(mapper.writeValueAsString(masterDto)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
 }

 @Test
 void addOrderOnMasterTest() throws Exception {
  OrderForm masterDto = new OrderForm();
  masterDto.setIdMasterOutfit(UUID.randomUUID());
  masterDto.setCarClient(UUID.randomUUID());
  masterDto.setIdOrder(UUID.randomUUID());
  masterDto.setDateEndOutfit(Date.from(new GregorianCalendar(2025, Calendar.JUNE, 25, 5, 0)
   .getTime().toInstant().plus(Duration.ofHours(6))));
  masterDto.setDateStartOutfit(Date.from(new GregorianCalendar(2025, Calendar.JUNE, 25, 5, 0)
   .getTime().toInstant().plus(Duration.ofHours(2))));
  masterDto.setNameOutfit("asdd");
  masterDto.setDescription("UUID.randomUUID()");
  Mockito.when(orderServices.addOrderOnMaster(masterDto, "")).thenReturn(UUID.randomUUID());
  mockMvc.perform(post("/details/new-order").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
   .content(mapper.writeValueAsString(masterDto)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
 }

 @Test
 void updateRequestFromClientTest() throws Exception {
  OrderForm masterDto = new OrderForm();
  masterDto.setIdMasterOutfit(UUID.randomUUID());
  masterDto.setCarClient(UUID.randomUUID());
  masterDto.setIdOrder(UUID.randomUUID());
  masterDto.setDateEndOutfit(Date.from(new GregorianCalendar(2025, Calendar.JUNE, 25, 5, 0)
   .getTime().toInstant().plus(Duration.ofHours(6))));
  masterDto.setDateStartOutfit(Date.from(new GregorianCalendar(2025, Calendar.JUNE, 25, 5, 0)
   .getTime().toInstant().plus(Duration.ofHours(2))));
  masterDto.setNameOutfit("asdd");
  masterDto.setDescription("UUID.randomUUID()");
  Mockito.when(orderServices.updateRequestFromClient(masterDto, "")).thenReturn(UUID.randomUUID());
  mockMvc.perform(put("/details/update-order").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
   .content(mapper.writeValueAsString(masterDto)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
 }

 @Test
 void updateOrderRequestMasterRTest() throws Exception {
  OrderDto masterDto = new OrderDto();
  masterDto.setId(UUID.randomUUID());
  masterDto.setCarClient(UUID.randomUUID());
  masterDto.setDescription("UUID.randomUUID()");
  Mockito.when(orderServices.updateOrderOnMasterR(masterDto, "")).thenReturn(true);
  mockMvc.perform(put("/details/order-update").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
   .content(mapper.writeValueAsString(masterDto)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
 }

}