package com.netcracker.servisec;

import com.netcracker.errors.EmptySearchException;
import com.netcracker.order.Order;
import java.util.List;

public interface OrderServices {


  boolean addOrder(Order master);

  List<Order> getAll() throws EmptySearchException;

}
