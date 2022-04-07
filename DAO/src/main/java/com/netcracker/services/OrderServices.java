package com.netcracker.services;


import javax.persistence.criteria.Order;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderServices {

 boolean addOrder(Order order);

 boolean repairRequest(Order order);

 List<Order> getAll() ;

 boolean updateOrder(Order order);

 List<Order> getOrderWithRequestState();

 boolean cancelRequest(UUID uuidCar);

 Optional<Order> getOrderByIdCar(UUID car);

 Optional<Order> getOrderById(UUID uuid);
}
