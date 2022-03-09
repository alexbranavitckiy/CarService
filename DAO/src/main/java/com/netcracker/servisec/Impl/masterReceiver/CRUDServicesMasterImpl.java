package com.netcracker.servisec.Impl.masterReceiver;

import com.netcracker.servisec.CRUDServices;
import com.netcracker.servisec.FileService;
import com.netcracker.servisec.ObjectMapperServices;
import com.netcracker.user.Client;
import com.netcracker.user.MasterReceiver;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
public class CRUDServicesMasterImpl implements CRUDServices {

    private final FileService fileService=new FileService();
    private final ObjectMapperServices objectMapperServices=new ObjectMapperServices();

    @Override
    public boolean addObject(Object o, File file) {
        MasterReceiver client = (MasterReceiver) o;
        try {
            List<MasterReceiver> list = new ArrayList<>(Arrays.asList(ObjectMapperServices.getObjectMapper().readValue(file, MasterReceiver[].class)));
            list.add(client);
            objectMapperServices.getObjectMapperWrite().writeValue(file, list);
            return true;
        } catch (Exception e) {
            log.error("Error adding object", e);
        }
        return false;
    }


    @Override
    public boolean deleteObjectById(String id, File file) {
        try {
            List<MasterReceiver> clients = new ArrayList<>(Arrays.asList(ObjectMapperServices.getObjectMapper().readValue(file, MasterReceiver[].class)));
            objectMapperServices.getObjectMapperWrite().writeValue(file, clients.stream().filter(x -> !x.getId().toString().equals(id)).collect(Collectors.toList()));
            return true;
        } catch (Exception e) {
            log.error("Object deletion error", e);
        }
        return false;
    }

    @Override
    public boolean updateObject(Object o, String id, File file) {
        if (this.deleteObjectById(id, fileService.getReceiverFile()) && this.addObject(o,fileService.getReceiverFile())) {
            return true;
        }
        log.error("Error deleting and adding user!!!");
        return false;
    }
}
