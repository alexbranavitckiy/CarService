package com.netcracker.services.impl;


import com.netcracker.DTO.convectror.MapperDto;
import com.netcracker.DTO.errs.SaveSearchErrorException;
import com.netcracker.DTO.ord.OrderDto;
import com.netcracker.order.Order;
import com.netcracker.order.State;
import com.netcracker.repository.OrderRepository;
import com.netcracker.services.CarServices;
import com.netcracker.services.ClientServices;
import com.netcracker.services.OrderServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrderServicesImpl implements OrderServices {

 private final OrderRepository orderRepository;
 private final CarServices carServices;
 private final ClientServices clientServices;
 private final Environment env;
 private final MapperDto<OrderDto, Order> orderMapperDto;

 @Lazy
 @Autowired
 private OrderServicesImpl(MapperDto<OrderDto, Order> orderMapperDto, Environment env, ClientServices clientServices, CarServices carServices, OrderRepository orderRepository) {
  this.orderRepository = orderRepository;
  this.orderMapperDto = orderMapperDto;
  this.env = env;
  this.carServices = carServices;
  this.clientServices = clientServices;
 }

 @Override
 public boolean addOrderOnClient(OrderDto dto, String nameUser) throws SaveSearchErrorException {
  try {
   dto.setId(UUID.randomUUID());
   dto.setState(State.REQUEST);
   dto.setUpdatedDate(new Date());
   dto.setCreatedDate(new Date());
   if (orderRepository.insertOrder(UUID.randomUUID(), dto.getCreatedDate(), dto.getState().getCode(), dto.getUpdatedDate(), dto.getCarClient(), dto.getDescription()) == 1)
    return true;
   else throw new SaveSearchErrorException("Sending request was not successful", "Save");
  } catch (Exception e) {
   log.warn(e.getMessage());
   throw new SaveSearchErrorException("Unknown error:" + e.getMessage(), "Save");
  }
 }

 @Override
 public List<OrderDto> getAllOrderClientsWithState(String login, State state) throws SaveSearchErrorException {
  try {
   List<OrderDto> orderDto = orderRepository.getAllOrderClient(login, state.getCode()).stream().map(orderMapperDto::toDto).collect(Collectors.toList());
   if (orderDto.size() > 0) {
    return orderDto;
   } else throw new SaveSearchErrorException("The search has not given any results", "Search");
  } catch (Exception e) {
   throw new SaveSearchErrorException("Unknown error:" + e.getMessage(), "Search");
  }
 }

 public boolean cancelRequest(UUID uuidOrder, String login) throws SaveSearchErrorException {
  try {
   if (orderRepository.updateStateOrder(State.CANCELED.getCode(), new Date(), uuidOrder, login) == 1)
    return true;
   else throw new SaveSearchErrorException("The operation was not successful", "Search");
  } catch (Exception e) {
   log.warn(e.getMessage());
   throw new SaveSearchErrorException("Unknown error:" + e.getMessage(), "Search");
  }
 }

 @Override
 public boolean idChek(UUID uuid) throws SaveSearchErrorException {
  if (orderRepository.existsById(uuid))
   return true;
  throw new SaveSearchErrorException("Invalid id entered.", "id");
 }
}
