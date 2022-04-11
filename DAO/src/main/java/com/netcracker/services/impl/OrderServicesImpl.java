package com.netcracker.services.impl;


import com.netcracker.order.Orders;
import com.netcracker.repository.OrderRepository;
import com.netcracker.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Order;
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
 public boolean addOrder(Orders order) {
  return false;
 }

 @Override
 public boolean repairRequest(Orders order, String nameUser) {
  return false;
 }

 @Override
 public List<Order> getAll() {
  return null;
 }

 @Override
 public boolean updateOrder(Orders order) {
  return false;
 }

 @Override
 public List<Orders> getOrderWithRequestState() {
  return null;
 }

 @Override
 public boolean cancelRequest(UUID uuidCar) {
  return false;
 }

 @Override
 public Optional<Orders> getOrderByIdCar(UUID car) {
  return Optional.empty();
 }

 @Override
 public Optional<Orders> getOrderById(UUID uuid) {
  return Optional.empty();
 }
}
