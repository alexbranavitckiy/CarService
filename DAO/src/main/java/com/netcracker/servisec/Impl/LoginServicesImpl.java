package com.netcracker.servisec.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.servisec.FileService;
import com.netcracker.servisec.LoginService;
import com.netcracker.servisec.UserSession;
import com.netcracker.user.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

public class LoginServicesImpl implements LoginService {

    private final FileService fileService = new FileService();


    @Override
    public String searchByUserLoginAndPassword(String login, String password) throws IOException {
        UserSession.closeSession();
        ObjectMapper objectMapper = new ObjectMapper();
        switch ("diamond") {//all cases are executed until the user is found
            case "diamond": {
                if (fileService.isExistsUser()) {
                    Optional<Client> client = (Arrays.stream(objectMapper.readValue(fileService.getUserFile(), Client[].class)).filter(x ->
                            {
                                if (x != null) {
                                    return x.getLogin().equalsIgnoreCase(login) && x.getPassword().equalsIgnoreCase(password);
                                }
                                return false;
                            }
                    )).findFirst();
                    if (client.isPresent()) {
                        client.ifPresent(UserSession::openSession);
                        return "Login Successful";
                    }
                }
            }
            case "1": {
                if (fileService.isExistsMaster()) {
                    Optional<Master> master = (Arrays.stream(objectMapper.readValue(fileService.getMasterFile(), Master[].class)).filter(x ->
                            {
                                if (x != null) {
                                    return x.getLogin().equalsIgnoreCase(login) && x.getPassword().equalsIgnoreCase(password);
                                } else return false;
                            }

                    ).findFirst());
                    if (master.isPresent()) {
                        master.ifPresent(UserSession::openSession);
                        return "Login Successful";
                    }

                }
            }
            case "2": {
                if (fileService.isExistsReceiver()) {
                    Optional<MasterReceiver> masterReceiver = (Arrays.stream(objectMapper.readValue(fileService.getReceiverFile(), MasterReceiver[].class)).filter(x ->
                            {
                                if (x != null) {
                                    return x.getLogin().equalsIgnoreCase(login) && x.getPassword().equalsIgnoreCase(password);
                                } else return false;
                            }
                    ).findFirst());
                    if (masterReceiver.isPresent()) {
                        masterReceiver.ifPresent(UserSession::openSession);
                        return "Login Successful";
                    }
                }
            }
        }
        return FileService.NOT_FOUND;
    }
}



