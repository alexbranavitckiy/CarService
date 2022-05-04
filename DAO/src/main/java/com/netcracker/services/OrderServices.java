package com.netcracker.services;


import com.netcracker.DTO.errs.SaveSearchErrorException;
import com.netcracker.DTO.ord.OrderDto;
import com.netcracker.DTO.ord.OrderForm;
import com.netcracker.order.State;

import java.util.List;
import java.util.UUID;

public interface OrderServices {

 List<OrderDto> getAllOrderClientsWithState(String login, State state) throws SaveSearchErrorException;

 boolean addOrderOnClient(OrderDto orderDto, String name) throws SaveSearchErrorException;

 boolean cancelRequest(UUID uuidCar, String login) throws SaveSearchErrorException;

 boolean idChek(UUID uuid) throws SaveSearchErrorException;

 UUID addOrderOnMaster(OrderForm orderDto, String login) throws SaveSearchErrorException;

 List<OrderDto> getAllOrderWithStateOnMaster(String login, State state) throws SaveSearchErrorException;

}
