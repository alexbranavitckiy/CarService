package com.netcracker.servisec.Impl.client;

import com.netcracker.errors.EmptySearchException;
import com.netcracker.marka.CarClient;
import com.netcracker.servisec.*;
import com.netcracker.servisec.Impl.CRUDServicesImpl;
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
  private final CRUDServices<Client> crudServices = new CRUDServicesImpl<>();


  @Override
  public List<Client> getAllClient() throws EmptySearchException {
    return crudServices.getAll(new File(FileService.USER_PATH), Client[].class);
  }

  public boolean addObjectInClient(Client o) {
    if (this.passwordCheck(o)) {
      return this.crudServices.addObject(o, fileService.getUserFile(), Client[].class);
    }
    return false;
  }

  @Override
  public boolean updateClient(Client client) {
    if (crudServices.updateObject(client,
        new File(FileService.USER_PATH), Client[].class)) {
      return UserSession.updateSession(client);
    }
    return false;
  }

  @Override
  public boolean updateClientNotSession(Client client) {
    return crudServices.updateObject(client,
        new File(FileService.USER_PATH), Client[].class);
  }


  @Override
  public boolean updateClientCar(CarClient carClient) {
    Client client = UserSession.getCloneClientSession();
    client.setCarClients(client.getCarClients().stream()
        .filter(x -> x.getId().toString().equalsIgnoreCase(carClient.getId().toString()))
        .collect(Collectors.toList()));
    return this.updateClient(client);
  }

  @Override
  public boolean addObjectInClientNotOnline(Client client) {
    if (this.passwordCheck(client)) {
      return this.crudServices.addObject(client, fileService.getUserFile(), Client[].class);
    }
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
