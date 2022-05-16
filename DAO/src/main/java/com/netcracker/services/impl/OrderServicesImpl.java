package com.netcracker.services.impl;


import com.netcracker.DTO.convectror.MapperDto;
import com.netcracker.DTO.errs.SaveSearchErrorException;
import com.netcracker.DTO.ord.OrderDto;
import com.netcracker.DTO.ord.OrderForm;
import com.netcracker.order.Order;
import com.netcracker.order.State;
import com.netcracker.repository.OrderRepository;
import com.netcracker.repository.OutfitsRepository;
import com.netcracker.services.OrderServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrderServicesImpl implements OrderServices {

 private final OrderRepository orderRepository;
 private final Environment env;
 private final MapperDto<OrderDto, Order> orderMapperDto;
 private final OutfitsRepository outfitsRepository;

 @Lazy
 @Autowired
 public OrderServicesImpl(OutfitsRepository outfitsRepository, MapperDto<OrderDto, Order> orderMapperDto,
                          Environment env, OrderRepository orderRepository) {
  this.orderRepository = orderRepository;
  this.outfitsRepository = outfitsRepository;
  this.orderMapperDto = orderMapperDto;
  this.env = env;
 }

 @Override
 public boolean addOrderOnClient(OrderDto dto, String nameUser) throws SaveSearchErrorException {
  try {
   dto.setId(UUID.randomUUID());
   dto.setState(State.REQUEST);
   dto.setUpdatedDate(new Date());
   dto.setCreatedDate(new Date());
   if (orderRepository.insertOrder(UUID.randomUUID(), dto.getCreatedDate(), dto.getState().getCode(),
    dto.getUpdatedDate(), dto.getCarClient(), dto.getDescription()) == 1)
    return true;
   else throw new SaveSearchErrorException("A request for this machine already exists", "Save");
  } catch (Exception e) {
   log.warn(e.getMessage());
   throw new SaveSearchErrorException("Unknown error:", e.getMessage());
  }
 }

 public void checkTime(Date dateStart, Date end, UUID master) throws SaveSearchErrorException {
  if (outfitsRepository.getAllOutfit(dateStart, end, master).size() != 0) {
   throw new SaveSearchErrorException("The selected time is occupied by another outfit", "Time");
  }
 }

 @Override
 public List<OrderDto> getAllOrderClientsWithState(String login, State state, int offset, int limit)
  throws SaveSearchErrorException {
  try {
   List<OrderDto> orderDto = orderRepository.getAllOrderClient(login, state.getCode(), PageRequest.of(offset, limit))
    .stream()
    .map(orderMapperDto::toDto).collect(Collectors.toList());
   if (orderDto.size() > 0) {
    return orderDto;
   } else throw new SaveSearchErrorException("The search has not given any results", "Search");
  } catch (Exception e) {
   throw new SaveSearchErrorException("Unknown error:", e.getMessage());
  }
 }

 public boolean cancelRequest(UUID uuidOrder, String login) throws SaveSearchErrorException {
  try {
   if (orderRepository.updateStateOrder(State.CANCELED.getCode(), new Date(), uuidOrder, login) == 1)
    return true;
   else throw new SaveSearchErrorException("The operation was not successful", "Search");
  } catch (Exception e) {
   log.warn(e.getMessage());
   throw new SaveSearchErrorException("Unknown error:", e.getMessage());
  }
 }

 @Override
 public boolean idChek(UUID uuid) throws SaveSearchErrorException {
  if (orderRepository.existsById(uuid))
   return true;
  throw new SaveSearchErrorException("Invalid id entered.", "id");
 }


 @Override
 public UUID addOrderOnMaster(OrderForm dto, String login) throws SaveSearchErrorException {
  try {
   UUID id_outfits = UUID.randomUUID();
   UUID dtoId = UUID.randomUUID();
   this.checkTime(dto.getDateStartOutfit(), dto.getDateEntOutfit(), dto.getIdMasterOutfit());
   if (orderRepository.insertOrder(id_outfits, dtoId, new Date(), State.CREATED.getCode(), new Date(),
    dto.getCarClient(), login, dto.getCarClient(), UUID.randomUUID(),
    com.netcracker.breakdown.State.IMPORTANT.getCode(), new Date(), dto.getDateEntOutfit(),
    dto.getDateStartOutfit(), dto.getIdMasterOutfit(), dto.getDescription(), dto.getNameOutfit(),
    com.netcracker.outfit.State.NO_STATE.getCode(), id_outfits, dto.getPriceBreakdown(), dto.getRun()) == 1)
    return dtoId;
   else throw new SaveSearchErrorException("Sending request was not successful", "Save");
  } catch (Exception e) {
   log.warn("{}", e);
   throw new SaveSearchErrorException("Unknown error:", e.getMessage());
  }
 }

 @Override
 public List<OrderDto> getAllOrderWithStateOnMaster(String login, State state) throws SaveSearchErrorException {
  try {
   return orderRepository.getAllByState(state).stream().map(orderMapperDto::toDto).collect(Collectors.toList());
  } catch (Exception e) {
   throw new SaveSearchErrorException("Unknown error:", " e.getMessage()");
  }
 }


 @Override
 public boolean updateOrderOnMasterR(OrderDto orderDto, String login) throws SaveSearchErrorException {
  try {
   if (orderRepository.updateOrderOnMaster(orderDto.getId(), orderDto.getDescription(), orderDto.getState().getCode(),
    new Date(), orderDto.getCarClient()) == 1)
    return true;
   else throw new SaveSearchErrorException("The operation was not successful", "Search");
  } catch (Exception e) {
   log.warn(e.getMessage());
   throw new SaveSearchErrorException("Unknown error:", e.getMessage());
  }
 }

 @Override
 public UUID updateRequestFromClient(OrderForm orderDto, String login) throws SaveSearchErrorException {
  try {
   UUID id_outfits = UUID.randomUUID();
   if (outfitsRepository.getAllOutfit(orderDto.getDateStartOutfit(), orderDto.getDateEntOutfit(),
    orderDto.getIdMasterOutfit()).size() != 0) {
    throw new SaveSearchErrorException("The selected time is occupied by another outfit", "Time");
   }
   if (orderRepository.updateOrderFromREQUEST(id_outfits, orderDto.getIdOrder(), new Date(),
    State.CREATED.getCode(), new Date(), login, UUID.randomUUID(),
    com.netcracker.breakdown.State.IMPORTANT.getCode(), new Date(), orderDto.getDateEntOutfit(),
    orderDto.getDateStartOutfit(), orderDto.getIdMasterOutfit(), orderDto.getDescription(), orderDto.getNameOutfit(),
    com.netcracker.outfit.State.NO_STATE.getCode(), orderDto.getPriceBreakdown(), orderDto.getRun()) == 1)
    return orderDto.getIdOrder();
   else throw new SaveSearchErrorException("Sending request was not successful", "Save");
  } catch (Exception e) {
   log.warn("{}", e);
   throw new SaveSearchErrorException("Unknown error:", e.getMessage());
  }
 }


}
