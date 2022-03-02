package com.netcracker.servisec;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.user.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

public class LoginServices {


    private final FileService fileService = new FileService();
    private  Client client;
    private  Master master;
    private MasterReceiver masterReceiver;

    public void searchByUserLoginAndPassword(String login, String password) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            switch ("diamond") {
                case "diamond": {
                    if (fileService.isExistsUser()) {
                        Optional<Client> client = (Arrays.stream(objectMapper.readValue(fileService.getUserFile(), Client[].class)).filter(x ->
                                x.getLogin().equalsIgnoreCase(login) && x.getPassword().equalsIgnoreCase(password)
                        )).findFirst();
                        if (client.isPresent()) {
                            this.client=client.get();
                            break;
                        }
                    }
                }
                case "1": {
                    if (fileService.isExistsMaster()) {
                        Optional<Master> master = (Arrays.stream(objectMapper.readValue(fileService.getMasterFile(), Master[].class)).filter(x ->
                                x.getLogin().equalsIgnoreCase(login) && x.getPassword().equalsIgnoreCase(password)
                        ).findFirst());
                        if (master.isPresent()) {
                            this.master=master.get();
                            break;
                        }
                    }
                }
                case "2": {
                    if (fileService.isExistsReceiver()) {
                        Optional<MasterReceiver> masterReceiver = (Arrays.stream(objectMapper.readValue(fileService.getReceiverFile(), MasterReceiver[].class)).filter(x ->
                                x.getLogin().equalsIgnoreCase(login) && x.getPassword().equalsIgnoreCase(password)
                        ).findFirst());
                        if (masterReceiver.isPresent()) {
                            this.masterReceiver=masterReceiver.get();
                            break;
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
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

    public MasterReceiver getMasterReceiver() {
        return masterReceiver;
    }

    public void setMasterReceiver(MasterReceiver masterReceiver) {
        this.masterReceiver = masterReceiver;
    }
}

