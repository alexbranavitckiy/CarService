package com.netcracker.file.services.impl.client;

import com.netcracker.ClientServices;
import com.netcracker.LoginService;
import com.netcracker.dto.model.ClientDto;
import com.netcracker.errors.EmptySearchException;
import com.netcracker.marka.CarClient;
import com.netcracker.file.services.CRUDServices;
import com.netcracker.file.FileService;
import com.netcracker.file.services.impl.CRUDServicesImpl;
import com.netcracker.file.services.impl.LoginServicesImpl;
import com.netcracker.session.UserSession;
import java.util.UUID;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class ClientServicesImpl implements ClientServices {

  private FileService fileService = new FileService();
  private LoginService loginService = new LoginServicesImpl();
  private CRUDServices<ClientDto> crudServices = new CRUDServicesImpl<>();

  @Override
  public List<ClientDto> getAllClient() throws EmptySearchException {
    return crudServices.getAll(new File(FileService.USER_PATH), ClientDto[].class);
  }

  @SneakyThrows
  public List<CarClient> getCarByIdClient(UUID uuidClient) {
    return crudServices.getAll(new File(FileService.USER_PATH), ClientDto[].class).stream()
      .filter(x -> x.getId().equals(uuidClient)).findFirst().get().getCarClients();
  }

  public boolean addObjectInClient(ClientDto o) {
    if (this.passwordCheck(o)) {
      return this.crudServices.addObject(o, fileService.getUserFile(), ClientDto[].class);
    }
    return false;
  }

  @Override
  public boolean updateClient(ClientDto client) {
    if (crudServices.updateObject(client,
      new File(FileService.USER_PATH), ClientDto[].class)) {
      return UserSession.updateSession(client);
    }
    return false;
  }

  @Override
  public boolean updateClientNotSession(ClientDto client) {
    return crudServices.updateObject(client,
      new File(FileService.USER_PATH), ClientDto[].class);
  }

  @Override
  public boolean updateClientCar(CarClient carClient) {
    ClientDto client = UserSession.getCloneClientSession();
    client.setCarClients(client.getCarClients().stream()
      .filter(x -> x.getId().toString().equalsIgnoreCase(carClient.getId().toString()))
      .collect(Collectors.toList()));
    return this.updateClient(client);
  }

  @Override
  public boolean addObjectInClientNotOnline(ClientDto client) {
    if (this.passwordCheck(client)) {
      return this.crudServices.addObject(client, fileService.getUserFile(), ClientDto[].class);
    }
    return false;
  }

  private boolean passwordCheck(ClientDto client) {
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
