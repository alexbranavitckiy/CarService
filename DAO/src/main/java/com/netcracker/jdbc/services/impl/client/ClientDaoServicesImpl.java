package com.netcracker.jdbc.services.impl.client;

import com.netcracker.ClientServices;
import com.netcracker.LoginServices;
import com.netcracker.file.services.impl.LoginServicesImpl;
import com.netcracker.jdbc.services.CarDao;
import com.netcracker.jdbc.services.ClientDao;
import com.netcracker.jdbc.services.impl.CarClientDaoImpl;
import com.netcracker.jdbc.services.impl.ClientDaoImpl;
import com.netcracker.marka.CarClient;
import com.netcracker.user.Client;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Slf4j
public class ClientDaoServicesImpl implements ClientServices {

    private final LoginServices loginService = new LoginServicesImpl();
    private final CarDao carClientDao = new CarClientDaoImpl();

    @Override
    @SneakyThrows
    public List<Client> getAllClient() {
               return null;
    }

    @SneakyThrows
    public List<CarClient> getCarByIdClient(UUID uuidClient) {
        return carClientDao.getAllCarClientByIdClient(uuidClient);
    }

    @SneakyThrows
    public boolean addObjectInClient(Client clientDto) {

        return true;
    }

    @Override
    public boolean addObjectInClientNotOnline(Client client) throws IOException {
        return false;
    }

    @Override
    public boolean updateClient(Client client) {

        return false;
    }

    @Override
    public boolean updateClientNotSession(Client client) {
        return false;
    }




    private boolean passwordCheck(Client client) {
        try {
            if (loginService.searchByUserLoginAndPassword(client.getLogin(),
                    client.getPassword())) {
                log.info("The username you entered is already taken");
                return false;
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return true;
    }



}