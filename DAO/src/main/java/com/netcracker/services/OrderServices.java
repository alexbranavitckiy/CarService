package com.netcracker.services;


import com.netcracker.DTO.ord.OrderDto;
import com.netcracker.order.Order;
import com.netcracker.order.State;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderServices {

 boolean addOrder(Order order);

 boolean repairRequest(OrderDto order, String nameUser);

 List<OrderDto> getAllOrderClientsWithState(String login, State state);

 boolean updateOrder(Order order);

 String cancelRequest(UUID uuidCar, String login);

 Optional<Order> getOrderById(UUID uuid);


}
