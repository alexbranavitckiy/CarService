package com.netcracker.servisec.Impl.client;

import com.netcracker.errors.EmptySearchException;
import com.netcracker.errors.WritingException;
import com.netcracker.marka.CarClient;
import com.netcracker.servisec.*;
import com.netcracker.servisec.Impl.LoginServicesImpl;
import com.netcracker.user.Client;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class ClientServicesImpl implements ClientServices {

    private final FileService fileService = new FileService();
    private final LoginService loginService = new LoginServicesImpl();
    private final CRUDServices crudServices = new CRUDServicesClientImpl();
    private final SearchServices<Client> searchServices = new CRUDServicesClientImpl();

    @Override
    public List<Client> getAllClient() throws EmptySearchException {
        return searchServices.getAll(new File(FileService.USER_PATH));
    }

    public boolean addObjectInClient(Client o) throws IOException {
        try {
            if (loginService.searchByUserLoginAndPassword(o.getLogin(), o.getPassword()))
                return this.crudServices.addObject(o, fileService.getUserFile());
        } catch (WritingException w) {
            log.error("Save error, please try again");
        }
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
        client.setCarClients(client.getCarClients().stream().filter(x -> x.getId().toString().equalsIgnoreCase(carClient.getId().toString())).collect(Collectors.toSet()));
        return this.updateClient(client);
    }

    @Override
    public boolean addObjectInClientNotOnline(Client client) {
        try {
            return this.crudServices.addObject(client, fileService.getUserFile());
        } catch (WritingException w) {
            log.error("Save error, please try again");
        }
        return false;
    }

}
