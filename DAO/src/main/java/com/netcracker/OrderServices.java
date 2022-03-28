package com.netcracker;

import com.netcracker.errors.EmptySearchException;
import com.netcracker.order.Order;

import java.util.List;

public interface OrderServices {

 boolean addOrder(Order order);

 boolean repairRequest(Order order);

 List<Order> getAll() throws EmptySearchException;

 boolean updateOrder(Order order);

 List<Order> getOrderWithRequestState();
}
