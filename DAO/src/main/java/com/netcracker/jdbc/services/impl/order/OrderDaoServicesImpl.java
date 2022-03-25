package com.netcracker.jdbc.services.impl.order;

import com.netcracker.OrderServices;
import com.netcracker.errors.EmptySearchException;
import com.netcracker.jdbc.services.CrudDao;
import com.netcracker.jdbc.services.impl.OrderDaoImpl;
import com.netcracker.order.Order;
import lombok.SneakyThrows;

import java.util.List;
import java.util.UUID;

public class OrderDaoServicesImpl implements OrderServices {

    private final CrudDao<Order, UUID> orderDao = new OrderDaoImpl();

    @Override
    @SneakyThrows
    public boolean addOrder(Order order) {
        return (orderDao.addObject(order));
    }

    @Override
    @SneakyThrows
    public List<Order> getAll() throws EmptySearchException {
        return orderDao.getAll();
    }

    @Override
    public List<Order> getOrderWithRequestState() {
        //TODO!!!!
        return null;
    }

}
