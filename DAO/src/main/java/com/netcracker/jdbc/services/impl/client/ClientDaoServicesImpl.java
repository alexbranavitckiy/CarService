package com.netcracker.jdbc.services.impl.client;

import com.netcracker.LoginService;
import com.netcracker.errors.EmptySearchException;
import com.netcracker.file.services.impl.LoginServicesImpl;
import com.netcracker.jdbc.services.CrudDao;
import com.netcracker.jdbc.services.impl.CarClientDaoImpl;
import com.netcracker.jdbc.services.impl.ClientDaoImpl;
import com.netcracker.marka.CarClient;
import com.netcracker.user.Client;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;

@Slf4j
public class ClientDaoServicesImpl {

    private LoginService loginService = new LoginServicesImpl();
    private final CrudDao<Client, UUID> clientDao = new ClientDaoImpl();
    private final CrudDao<CarClient, UUID> carClientDao = new CarClientDaoImpl();

    @SneakyThrows
    public List<Client> getAllClient() throws EmptySearchException {
        return clientDao.getAll();
    }


}