package com.netcracker.servisec;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.errors.EmptySearchException;
import com.netcracker.user.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

public class LoginServices {


    private final FileService fileService = new FileService();

    public String searchByUserLoginAndPassword(String login, String password) throws IOException {
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
        System.out.println("User is not found");
        return "User is not found";
    }
}



