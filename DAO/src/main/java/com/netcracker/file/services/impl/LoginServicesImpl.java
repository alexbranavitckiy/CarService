package com.netcracker.file.services.impl;

import com.netcracker.dto.modelDTO.ClientDto;
import com.netcracker.file.services.CRUDServices;
import com.netcracker.file.FileService;
import com.netcracker.LoginServices;
import com.netcracker.session.UserSession;
import com.netcracker.user.*;

import java.io.IOException;
import java.util.Optional;

public class LoginServicesImpl implements LoginServices {

    private final FileService fileService = new FileService();
    private final CRUDServices<ClientDto> clientCRUDServices = new CRUDServicesImpl<>();
    private final CRUDServices<Master> masterCRUDServices = new CRUDServicesImpl<>();
    private final CRUDServices<MasterReceiver> masterReceiverCRUDServices = new CRUDServicesImpl<>();

    public LoginServicesImpl() {
    }

    @Override
    public boolean searchByUserLoginAndPassword(String login, String password) throws IOException {
        try {
            switch ("diamond") {//all cases are executed until the user is found
                case "diamond": {
                    if (fileService.isExistsUser()) {
                        Optional<ClientDto> client = clientCRUDServices.getAll(fileService.getUserFile(),
                                ClientDto[].class).stream().filter(x ->
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
                        Optional<Master> master = masterCRUDServices.getAll(fileService.getMasterFile(),
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
                                fileService.getReceiverFile(), MasterReceiver[].class).stream().
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




