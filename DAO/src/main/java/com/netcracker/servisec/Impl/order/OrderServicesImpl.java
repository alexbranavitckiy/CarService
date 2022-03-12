package com.netcracker.servisec.Impl.order;

import com.netcracker.errors.EmptySearchException;
import com.netcracker.order.Order;
import com.netcracker.servisec.CRUDServices;
import com.netcracker.servisec.FileService;
import com.netcracker.servisec.Impl.CRUDServicesImpl;
import com.netcracker.servisec.OrderServices;
import java.io.File;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderServicesImpl implements OrderServices {


  private final CRUDServices<Order> searchServices = new CRUDServicesImpl<>();


  @Override
  public boolean addOrder(Order order) {
    try {
      return (searchServices.addObject(order, new File(FileService.ORDERS_PATH),Order[].class));
    } catch (Exception e) {
      log.error("Error adding object", e);
    }
    return false;
  }


  @Override
  public List<Order> getAll() throws EmptySearchException {
    return searchServices.getAll(new File(FileService.ORDERS_PATH),Order[].class);
  }



}
