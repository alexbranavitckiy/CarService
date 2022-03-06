package com.netcracker.servisec.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.marka.CarClient;
import com.netcracker.servisec.*;
import com.netcracker.user.Client;

import java.io.IOException;


public class ClientServicesImpl implements ClientServices {

    private final CRUDServices crudServices = new CRUDServicesImpl();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final FileService fileService = new FileService();
    private final LoginService loginService = new LoginServicesImpl();

    public boolean addObjectInClient(Client o) throws IOException {
        if (loginService.searchByUserLoginAndPassword(o.getLogin(), o.getPassword()).equalsIgnoreCase(FileService.NOT_FOUND))
            return this.crudServices.addObject(o, objectMapper, fileService.getUserFile());
        else return false;
    }

    @Override
    public boolean updateClient(Client client) {
        return crudServices.updateObject(client, client.getId().toString(), new ObjectMapper(), fileService.getUserFile());
    }

    @Override
    public boolean updateClientCar(CarClient carClient) {
        UserSession.getClientSession().get().getCarClients().stream().forEach(x -> {
            if (x.getId().equals(carClient.getId())) {
                x = carClient;
            }
        });
        return this.updateClient(UserSession.getClientSession().get());
    }


}
