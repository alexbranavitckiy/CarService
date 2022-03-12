package com.netcracker.servisec.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.netcracker.servisec.FileService;
import com.netcracker.servisec.LoginService;
import com.netcracker.servisec.ObjectMapperServices;
import com.netcracker.servisec.UserSession;
import com.netcracker.user.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

public class LoginServicesImpl implements LoginService {

  private final FileService fileService = new FileService();


  @Override
  public boolean searchByUserLoginAndPassword(String login, String password) throws IOException {
    try {
      switch ("diamond") {//all cases are executed until the user is found
        case "diamond": {
          if (fileService.isExistsUser()) {
            Optional<Client> client = (Arrays.stream(
                ObjectMapperServices.getObjectMapper().readValue(fileService.getUserFile(), Client[].class)).filter(x ->
                {
                  if (x != null) {
                    return x.getLogin().equalsIgnoreCase(login) && x.getPassword()
                        .equalsIgnoreCase(password);
                  }
                  return false;
                }
            )).findFirst();
            if (client.isPresent()) {
              client.ifPresent(UserSession::openSession);
              return true;
            }
          }
        }
        case "1": {
          if (fileService.isExistsMaster()) {
            Optional<Master> master = (Arrays.stream(
                ObjectMapperServices.getObjectMapper().readValue(fileService.getMasterFile(), Master[].class)).filter(x ->
                {
                    if (x != null) {
                        return x.getLogin().equalsIgnoreCase(login) && x.getPassword()
                            .equalsIgnoreCase(password);
                    } else {
                        return false;
                    }
                }
            ).findFirst());
            if (master.isPresent()) {
              master.ifPresent(UserSession::openSession);
              return true;
            }
          }
        }
        case "2": {
          if (fileService.isExistsReceiver()) {
            Optional<MasterReceiver> masterReceiver = (Arrays.stream(
                    ObjectMapperServices.getObjectMapper().readValue(fileService.getReceiverFile(), MasterReceiver[].class))
                .filter(x ->
                    {
                        if (x != null) {
                            return x.getLogin().equalsIgnoreCase(login) && x.getPassword()
                                .equalsIgnoreCase(password);
                        } else {
                            return false;
                        }
                    }
                ).findFirst());
            if (masterReceiver.isPresent()) {
              masterReceiver.ifPresent(UserSession::openSession);
              return true;
            }
          }
        }

      }
    } catch (MismatchedInputException e) {
      new FileService().init();
      this.searchByUserLoginAndPassword(login, password);
    }
    return false;
  }
}



