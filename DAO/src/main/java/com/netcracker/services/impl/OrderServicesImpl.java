package com.netcracker.services.impl;


import com.netcracker.order.Order;
import com.netcracker.repository.OrderRepository;
import com.netcracker.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServicesImpl implements OrderServices {

 private final OrderRepository orderRepository;

 @Autowired
 private OrderServicesImpl(OrderRepository orderRepository) {
  this.orderRepository = orderRepository;
 }


 @Override
 public boolean addOrder(Order order) {
  return false;
 }

 @Override
 public boolean repairRequest(Order order, String nameUser) {
  return false;
 }

 @Override
 public List<Order> getAllOrderClientsWithState(String login, String state) {
  return null;
 }

 @Override
 public boolean updateOrder(Order order) {
  return false;
 }

 @Override
 public List<Order> getOrderWithRequestState() {
  return null;
 }

 @Override
 public boolean cancelRequest(UUID uuidCar) {
  return false;
 }

 @Override
 public Optional<Order> getOrderByIdCar(UUID car) {
  return Optional.empty();
 }

 @Override
 public Optional<Order> getOrderById(UUID uuid) {
  return Optional.empty();
 }
}
