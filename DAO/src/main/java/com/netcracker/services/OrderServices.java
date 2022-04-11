package com.netcracker.services;


import com.netcracker.order.Orders;

import javax.persistence.criteria.Order;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderServices {

 boolean addOrder(Orders order);

 boolean repairRequest(Orders order,String nameUser);

 List<Order> getAllOrderClientsWithState(String login,String state) ;

 boolean updateOrder(Orders order);

 List<Orders> getOrderWithRequestState();

 boolean cancelRequest(UUID uuidCar);

 Optional<Orders> getOrderByIdCar(UUID car);

 Optional<Orders> getOrderById(UUID uuid);
}
