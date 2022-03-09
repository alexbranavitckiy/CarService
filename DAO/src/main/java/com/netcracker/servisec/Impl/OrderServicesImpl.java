package com.netcracker.servisec.Impl;

import com.netcracker.errors.EmptySearchException;
import com.netcracker.errors.WritingException;
import com.netcracker.order.Order;
import com.netcracker.servisec.CRUDServices;
import com.netcracker.servisec.FileService;
import com.netcracker.servisec.ObjectMapperServices;
import com.netcracker.servisec.SearchServices;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class OrderServicesImpl implements CRUDServices, SearchServices<Order> {

    private final FileService fileService = new FileService();

    @Override
    public List<Order> getAll(File file) throws EmptySearchException {
        try {
            return new ArrayList<>(List.of(ObjectMapperServices.getObjectMapper().readValue(new File(FileService.ORDERS_PATH), Order[].class)));
        } catch (IOException e) {
          log.error("Output error, please try again",e);
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
        try {
            List<Order> orders = new ArrayList<>(Arrays.asList(ObjectMapperServices.getObjectMapper().readValue(file, Order[].class)));
            ObjectMapperServices.getObjectMapper().writeValue(file, orders.stream().filter(x -> !x.getId().toString().equals(id)).collect(Collectors.toList()));
            return true;
        } catch (Exception e) {
            log.error("Delete error",e);
        }
        return false;
    }

    @Override
    public boolean updateObject(Object o, String id, File file) {
        try {
            if (this.deleteObjectById(id, fileService.getReceiverFile()) && this.addObject(o, fileService.getReceiverFile())) {
                return true;
            }
        } catch (WritingException e) {
            log.error("Error update user!!!");
        }
        return false;
    }
}
