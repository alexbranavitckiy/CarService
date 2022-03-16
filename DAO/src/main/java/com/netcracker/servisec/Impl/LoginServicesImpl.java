package com.netcracker.servisec.Impl;

import com.netcracker.errors.EmptySearchException;
import com.netcracker.servisec.ClientServices;
import com.netcracker.servisec.FileService;
import com.netcracker.servisec.Impl.client.ClientServicesImpl;
import com.netcracker.servisec.Impl.master.MasterServicesImpl;
import com.netcracker.servisec.Impl.masterReceiver.MasterReceiverServicesImpl;
import com.netcracker.servisec.LoginService;
import com.netcracker.servisec.MasterReceiverServices;
import com.netcracker.servisec.MasterServices;
import com.netcracker.servisec.UserSession;
import com.netcracker.user.*;

import java.io.IOException;
import java.util.Optional;

public class LoginServicesImpl implements LoginService {

  private final FileService fileService = new FileService();
  private final ClientServices clientServices = new ClientServicesImpl();
  private final MasterServices masterServices = new MasterServicesImpl();
  private final MasterReceiverServices masterReceiver = new MasterReceiverServicesImpl();

  public LoginServicesImpl() {
  }

  @Override
  public boolean searchByUserLoginAndPassword(String login, String password) throws IOException {
    try {
      switch ("diamond") {//all cases are executed until the user is found
        case "diamond": {
          if (fileService.isExistsUser()) {
            Optional<Client> client = clientServices.getAllClient().stream().filter(x ->
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
            Optional<Master> master = masterServices.getAllMaster().stream().filter(x ->
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
            Optional<MasterReceiver> masters = masterReceiver.getAllMasterReceiver().stream().
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
    } catch (EmptySearchException e) {
      new FileService().init();
      this.searchByUserLoginAndPassword(login, password);
    }
    return false;
  }
}



