package com.netcracker.servisec.Impl.order;

import com.netcracker.order.Order;
import com.netcracker.servisec.CRUDServices;
import com.netcracker.servisec.FileService;
import com.netcracker.servisec.OrderServices;
import java.io.File;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderServicesImpl implements OrderServices {

  private final CRUDServices crudServices = new CRUDOrderServicesImpl();

  @Override
  public boolean addOrder(Order order) {
    try {
      return (crudServices.addObject(order, new File(FileService.ORDERS_PATH)));
    } catch (Exception e) {
      log.error("Error adding object", e);
    }
    return false;
  }
}
