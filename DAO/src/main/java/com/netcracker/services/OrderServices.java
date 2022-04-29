package com.netcracker.services;


import com.netcracker.DTO.errs.SaveSearchErrorException;
import com.netcracker.DTO.ord.OrderDto;
import com.netcracker.order.Order;
import com.netcracker.order.State;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderServices {

 //boolean addOrder(Order order);
 //boolean repairRequest(OrderDto order, String nameUser);
 // boolean updateOrder(Order order);

 // Optional<Order> getOrderById(UUID uuid);

 List<OrderDto> getAllOrderClientsWithState(String login, State state) throws SaveSearchErrorException;

 boolean addOrderOnClient(OrderDto orderDto, String name) throws SaveSearchErrorException;

 boolean cancelRequest(UUID uuidCar, String login) throws SaveSearchErrorException;

 boolean idChek(UUID uuid) throws SaveSearchErrorException;
}
