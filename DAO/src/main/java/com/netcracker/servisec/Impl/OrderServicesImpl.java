package com.netcracker.servisec.Impl;

import com.netcracker.errors.EmptySearchException;
import com.netcracker.errors.WritingException;
import com.netcracker.order.Order;
import com.netcracker.servisec.CRUDServices;
import com.netcracker.servisec.FileService;
import com.netcracker.servisec.ObjectMapperServices;
import com.netcracker.servisec.SearchServices;
import com.netcracker.user.Client;
import com.netcracker.user.MasterReceiver;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class OrderServicesImpl implements CRUDServices, SearchServices<Order> {

    @Override
    public List<Order> getAll(File file) throws EmptySearchException {
        try {
            return new ArrayList<>(List.of(ObjectMapperServices.getObjectMapper().readValue(new File(FileService.ORDERS_PATH), Order[].class)));
        } catch (IOException e) {
            System.out.println("Output error, please try again");
        }
        throw new EmptySearchException("No orders available");
    }

    @Override
    public boolean addObject(Object o, File file) throws WritingException {
        try {
            Order order = (Order) o;
            List<Order> list = new ArrayList<>(Arrays.asList(ObjectMapperServices.getObjectMapper().readValue(file, Order[].class)));
            list.add(order);
            ObjectMapperServices.getObjectMapper().writeValue(file, list);
            return true;
        } catch (Exception e) {
            log.error("Invalid data entered");
        }
        throw new WritingException("Save error, please try again");
    }

    @Override
    public boolean deleteObjectById(String id, File file) {
        // TODO add here code!!!
        return false;
    }

    @Override
    public boolean updateObject(Object o, String id, File file) {
        // TODO add here code!!!
        return false;
    }
}
