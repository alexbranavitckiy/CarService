package com.netcracker.jdbc.services.impl.order;

import com.netcracker.OrderServices;
import com.netcracker.errors.PersistException;
import com.netcracker.jdbc.services.OrderDao;
import com.netcracker.jdbc.services.impl.OrderDaoImpl;
import com.netcracker.order.Order;
import com.netcracker.order.State;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OrderDaoServicesImpl implements OrderServices {

 private final OrderDao orderDao = new OrderDaoImpl();

 @Override
 public boolean addOrder(Order order) {
  try {
   return orderDao.addObject(order);
  } catch (PersistException p) {
   log.warn("Order add error:{}", p.getMessage());
  }
  return false;
 }

 @Override
 public List<Order> getAll() {
  try {
   return orderDao.getAll();
  } catch (PersistException p) {
   log.warn("Order fetch error{}", p.getMessage());
  }
  return new ArrayList<>();
 }

 @Override
 public List<Order> getOrderWithRequestState() {
  try {
   return orderDao.getAllByQuery(State.REQUEST.getId(), orderDao.getAllOrderByState());
  } catch (PersistException p) {
   log.warn("Order error{}", p.getMessage());
  }
  return new ArrayList<>();
 }

 @Override
 public boolean updateOrder(Order order) {
  try {
   return orderDao.update(order);
  } catch (PersistException p) {
   log.warn("Order  error{}", p.getMessage());
  }
  return false;
 }
}
