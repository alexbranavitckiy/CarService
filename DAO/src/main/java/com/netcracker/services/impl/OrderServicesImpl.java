package com.netcracker.services.impl;


import com.netcracker.DTO.convectror.MapperDto;
import com.netcracker.DTO.ord.OrdMapper;
import com.netcracker.DTO.ord.OrderDto;
import com.netcracker.car.CarClient;
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

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrderServicesImpl implements OrderServices {

 private final OrderRepository orderRepository;
 private final OrdMapper orderMapper;
 private final CarServices carServices;
 private final ClientServices clientServices;
 private final Environment env;
 private final MapperDto<OrderDto, Order> orderMapperDto;

 @Lazy
 @Autowired
 private OrderServicesImpl(MapperDto<OrderDto, Order> orderMapperDto, Environment env, ClientServices clientServices, CarServices carServices, OrdMapper orderMapper, OrderRepository orderRepository) {
  this.orderRepository = orderRepository;
  this.orderMapperDto = orderMapperDto;
  this.env = env;
  this.orderMapper = orderMapper;
  this.carServices = carServices;
  this.clientServices = clientServices;
 }

 @Override
 public boolean addOrder(Order order) {
  return false;
 }


 @Override
 public boolean repairRequest(OrderDto dto, String nameUser) {
  try {
   dto.setState(State.REQUEST);
   orderRepository.insertOrder(UUID.randomUUID().toString(), dto.getCreatedDate(), dto.getState().getCode(), dto.getUpdatedDate(), dto.getCarClient().toString(), dto.getDescription());
   return true;
  } catch (Exception e) {
   log.warn(e.getMessage());
  }
  return false;
 }

 @Override
 public List<OrderDto> getAllOrderClientsWithState(String login, State state) {
  return orderRepository.getAllOrderClient(login, state).stream().map(orderMapperDto::toDto).collect(Collectors.toList());
 }

 @Override
 public boolean updateOrder(Order order) {
  return false;
 }

 @Override
 public String cancelRequest(UUID uuid, String login) {
  try {
   Optional<Order> order = orderRepository.getAllById(uuid);
   if (order.isPresent() && order.get().getState().equals(State.REQUEST)) {
    order.get().setState(State.CANCELED);
    orderRepository.save(order.get());
    return env.getProperty("messages.cancel.request");
   }
  } catch (Exception e) {
   log.warn(e.getMessage());
   return env.getProperty("messages.cancel.err");
  }
  return env.getProperty("messages.cancel.false");
 }

 @Override
 public Optional<Order> getOrderById(UUID uuid) {
  return Optional.empty();
 }
}
