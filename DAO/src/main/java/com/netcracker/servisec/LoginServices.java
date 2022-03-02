package com.netcracker.servisec;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.errors.EmptySearchException;
import com.netcracker.user.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

public class LoginServices {


    private final FileService fileService = new FileService();


    public Object searchByUserLoginAndPassword(String login, String password) throws EmptySearchException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            switch ("diamond") {
                case "diamond": {
                    if (fileService.isExistsUser()) {
                        Optional<Client> client = (Arrays.stream(objectMapper.readValue(fileService.getUserFile(), Client[].class)).filter(x ->
                                x.getLogin().equalsIgnoreCase(login) && x.getPassword().equalsIgnoreCase(password)
                        )).findFirst();
                        if (client.isPresent()) {
                            return client.get();
                        }
                    }
                }
                case "1": {
                    if (fileService.isExistsMaster()) {
                        Optional<Master> master = (Arrays.stream(objectMapper.readValue(fileService.getMasterFile(), Master[].class)).filter(x ->
                                x.getLogin().equalsIgnoreCase(login) && x.getPassword().equalsIgnoreCase(password)
                        ).findFirst());
                        if (master.isPresent()) {
                            return master.get();
                        }
                    }
                }
                case "2": {
                    if (fileService.isExistsReceiver()) {
                        Optional<MasterReceiver> masterReceiver = (Arrays.stream(objectMapper.readValue(fileService.getReceiverFile(), MasterReceiver[].class)).filter(x ->
                                x.getLogin().equalsIgnoreCase(login) && x.getPassword().equalsIgnoreCase(password)
                        ).findFirst());
                        if (masterReceiver.isPresent()) {
                            return masterReceiver.get();
                        }
                    }
                }
                case "3": {

                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("An input error occurred please try again later. We are already working on it");
        }
        throw new EmptySearchException("User is not found");
    }
}

