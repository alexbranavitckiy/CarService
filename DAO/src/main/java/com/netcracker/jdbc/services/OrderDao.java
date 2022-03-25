package com.netcracker.jdbc.services;


import com.netcracker.order.Order;

import java.util.List;
import java.util.UUID;

public interface OrderDao extends CrudDao<Order, UUID> {

    List<Order> getAllOrderByUUIDState(UUID uuidState);

}
