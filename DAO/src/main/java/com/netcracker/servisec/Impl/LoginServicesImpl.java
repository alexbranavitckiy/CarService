package com.netcracker.servisec.Impl;

import com.netcracker.servisec.CRUDServices;
import com.netcracker.servisec.FileService;
import com.netcracker.servisec.LoginService;
import com.netcracker.servisec.UserSession;
import com.netcracker.user.*;

import java.io.IOException;
import java.util.Optional;

public class LoginServicesImpl implements LoginService {

  private final FileService fileService = new FileService();
  private CRUDServices<Client> clientCRUDServices = new CRUDServicesImpl<>();
  private CRUDServices<Master> masterCRUDServices = new CRUDServicesImpl<>();
  private CRUDServices<MasterReceiver> masterReceiverCRUDServices = new CRUDServicesImpl<>();

  public LoginServicesImpl() {
  }

  @Override
  public boolean searchByUserLoginAndPassword(String login, String password) throws IOException {
    try {
      switch ("diamond") {//all cases are executed until the user is found
        case "diamond": {
          if (fileService.isExistsUser()) {
            Optional<Client> client = clientCRUDServices.getAll(fileService.getUserFile(),
              Client[].class).stream().filter(x ->
              {
                if (x != null) {
                  return x.getLogin().equalsIgnoreCase(login) && x.getPassword()
                    .equalsIgnoreCase(password);
                }
                return false;
              }
            ).findFirst();
            if (client.isPresent()) {
              client.ifPresent(UserSession::openSession);
              return true;
            }
          }
        }
        case "1": {
          if (fileService.isExistsMaster()) {
            Optional<Master> master = masterCRUDServices.getAll(fileService.getUserFile(),
              Master[].class).stream().filter(x ->
              {
                if (x != null) {
                  return x.getLogin().equalsIgnoreCase(login) && x.getPassword()
                    .equalsIgnoreCase(password);
                } else {
                  return false;
                }
              }
            ).findFirst();
            if (master.isPresent()) {
              master.ifPresent(UserSession::openSession);
              return true;
            }
          }
        }
        case "2": {
          if (fileService.isExistsReceiver()) {
            Optional<MasterReceiver> masters = masterReceiverCRUDServices.getAll(
                fileService.getMasterFile(), MasterReceiver[].class).stream().
              filter(x ->
                {
                  if (x != null) {
                    return x.getLogin().equalsIgnoreCase(login) && x.getPassword()
                      .equalsIgnoreCase(password);
                  } else {
                    return false;
                  }
                }
              ).findFirst();
            if (masters.isPresent()) {
              masters.ifPresent(UserSession::openSession);
              return true;
            }
          }
        }
      }
    } catch (Exception e) {
      new FileService().init();
      this.searchByUserLoginAndPassword(login, password);
    }
    return false;
  }
}




