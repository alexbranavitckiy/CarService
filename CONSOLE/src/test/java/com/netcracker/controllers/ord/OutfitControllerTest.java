package com.netcracker.controllers.ord;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.CarServiceApplication;
import com.netcracker.DTO.ord.OrderForm;
import com.netcracker.DTO.ord.OutfitDto;
import com.netcracker.services.OutfitsServices;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WithMockUser(authorities = "RECEPTIONIST")
@SpringBootTest(classes = CarServiceApplication.class)
class OutfitControllerTest {

 @Autowired
 private MockMvc mockMvc;

 @MockBean
 private OutfitsServices outfitsServices;

 @Autowired
 private ObjectMapper mapper;

 @Test
 @WithMockUser(authorities = "MASTER")
 void outfitEndMasterTest() throws Exception {
  Mockito.when(outfitsServices.outfitEndWork("name")).thenReturn(true);
  mockMvc.perform(patch("/aut/outfit-end").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
  .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
 }

 @Test
 @WithMockUser(authorities = "MASTER")
 void updateOutfitByMasterTest() throws Exception {
  OutfitDto masterDto = new OutfitDto();
  Mockito.when(outfitsServices.updateOutfitByMaster(masterDto, "")).thenReturn(UUID.randomUUID());
  mockMvc.perform(post("/aut/outfit-update").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
   .content(mapper.writeValueAsString(masterDto)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
 }


}