package com.netcracker.servisec.Impl;

import com.netcracker.errors.EmptySearchException;
import com.netcracker.servisec.CRUDServices;
import com.netcracker.servisec.FileService;
import com.netcracker.servisec.ObjectMapperServices;
import com.netcracker.servisec.SearchServices;
import com.netcracker.user.Client;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class CRUDServicesClientImpl implements CRUDServices, SearchServices<Client> {

    private final ObjectMapperServices objectMapperServices = new ObjectMapperServices();

    @Override
    public List<Client> getAll(File file) throws EmptySearchException {
        try {
            return new ArrayList<>(List.of(ObjectMapperServices.getObjectMapper().readValue(new File(FileService.USER_PATH), Client[].class)));
        } catch (IOException e) {
            System.out.println("Output error, please try again");
        }
        throw new EmptySearchException("No orders available");
    }

    @Override
    public boolean addObject(Object o, File file) {
        Client client = (Client) o;
        try {
            List<Client> list = new ArrayList<>(Arrays.asList(ObjectMapperServices.getObjectMapper().readValue(file, Client[].class)));
            list.add(client);
            objectMapperServices.getObjectMapperWrite().writeValue(file, list);
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    @Override
    public boolean deleteObjectById(String id, File file) {
        try {
            List<Client> clients = new ArrayList<>(Arrays.asList(ObjectMapperServices.getObjectMapper().readValue(file, Client[].class)));
            objectMapperServices.getObjectMapperWrite().writeValue(file, clients.stream().filter(x -> !x.getId().toString().equals(id)).collect(Collectors.toList()));
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    @Override
    public boolean updateObject(Object o, String id, File file) {
        if (this.deleteObjectById(id, new File(FileService.USER_PATH)) && this.addObject(o, new File(FileService.USER_PATH))) {
            return true;
        }
        log.error("Error deleting and adding user!!!");
        return false;
    }
}
