package com.netcracker.file.services.impl.order;

import com.netcracker.errors.EmptySearchException;
import com.netcracker.order.Order;
import com.netcracker.order.State;
import com.netcracker.file.services.CRUDServices;
import com.netcracker.file.FileService;
import com.netcracker.file.services.impl.CRUDServicesImpl;
import com.netcracker.OrderServices;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderServicesImpl implements OrderServices {

  private CRUDServices<Order> searchServices = new CRUDServicesImpl<>();

  public OrderServicesImpl() {
  }

  @Override
  public boolean addOrder(Order order) {
    try {
      return (searchServices.addObject(order, new File(FileService.ORDERS_PATH), Order[].class));
    } catch (Exception e) {
      log.error("Error adding object", e);
    }
    return false;
  }

  @Override
  public List<Order> getAll() throws EmptySearchException {
    return searchServices.getAll(new File(FileService.ORDERS_PATH), Order[].class);
  }

  @Override
  @SneakyThrows
  public List<Order> getOrderWithRequestState() {
    return searchServices.getAll(new File(FileService.ORDERS_PATH), Order[].class).stream()
      .filter(x -> x.getStateOrder().equals(State.REQUEST)).collect(
        Collectors.toList());
  }

}
