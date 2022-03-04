package com.netcracker.servisec.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.servisec.CRUDServices;
import com.netcracker.servisec.ClientServices;
import com.netcracker.servisec.FileService;
import com.netcracker.servisec.LoginService;
import com.netcracker.user.Client;

import java.io.IOException;


public class ClientServicesImpl implements ClientServices {

    private final CRUDServices crudServices = new CRUDServicesImpl();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final FileService fileService = new FileService();
    private final LoginService loginService = new LoginServicesImpl();

    public boolean addObjectInClient(Client o) throws IOException {
        if (loginService.searchByUserLoginAndPassword(o.getLogin(), o.getPassword()).equalsIgnoreCase(fileService.getNOT_FOUND()))
            return this.crudServices.addObject(o, objectMapper, fileService.getUserFile());
        else return false;
    }

    @Override
    public boolean updateClient(Client client)  {
        return crudServices.updateObject(client, client.getId().toString(), new ObjectMapper(), fileService.getUserFile());
    }
}
