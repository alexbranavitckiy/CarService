package com.netcracker.servisec.Impl;

import com.netcracker.marka.CarClient;
import com.netcracker.servisec.*;
import com.netcracker.user.Client;

import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;


public class ClientServicesImpl implements ClientServices {

    private final FileService fileService = new FileService();
    private final LoginService loginService = new LoginServicesImpl();
    private final CRUDServices crudServices= new CRUDServicesClientImpl();

    public boolean addObjectInClient(Client o) throws IOException {
        if (loginService.searchByUserLoginAndPassword(o.getLogin(), o.getPassword()).equalsIgnoreCase(FileService.NOT_FOUND))
            return this.crudServices.addObject(o, fileService.getUserFile());
        else
            return false;
    }

    @Override
    public boolean updateClient(Client client) {
        if (crudServices.updateObject(client, client.getId().toString(), new File(FileService.USER_PATH)))
            return UserSession.updateSession(client);
        return false;
    }

    @Override
    public boolean updateClientCar(CarClient carClient) {
        Client client = UserSession.getCloneClientSession();
        client.setCarClients(client.getCarClients().stream().filter(x->x.getId().toString().equalsIgnoreCase(carClient.getId().toString())).collect(Collectors.toSet()));
        return this.updateClient(client);
    }

}
