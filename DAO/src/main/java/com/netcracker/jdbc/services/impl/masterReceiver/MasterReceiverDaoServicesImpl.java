package com.netcracker.jdbc.services.impl.masterReceiver;

import com.netcracker.LoginService;
import com.netcracker.MasterReceiverServices;
import com.netcracker.errors.EmptySearchException;
import com.netcracker.file.services.impl.LoginServicesImpl;
import com.netcracker.jdbc.services.CrudDao;
import com.netcracker.jdbc.services.impl.MasterReceiverImpl;
import com.netcracker.session.UserSession;
import com.netcracker.user.MasterReceiver;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;
import java.util.UUID;


@Slf4j
public class MasterReceiverDaoServicesImpl implements MasterReceiverServices {

    private LoginService loginService = new LoginServicesImpl();
    private CrudDao<MasterReceiver, UUID> crudServices = new MasterReceiverImpl();

    @Override
    @SneakyThrows
    public List<MasterReceiver> getAllMasterReceiver() throws EmptySearchException {
        return crudServices.getAll();
    }

    @Override
    @SneakyThrows
    public boolean updateMaster(MasterReceiver masterReceiver) { //TODO!!! add password check for all entities analog login service from the file system
        if (crudServices.update(//this.passwordCheck(masterReceiver)
                masterReceiver)) {
            return UserSession.updateSession(masterReceiver);
        }
        return false;
    }

    @Override
    @SneakyThrows
    public boolean updateMasterAndSession(MasterReceiver masterReceiver) {
        if (this.passwordCheck(masterReceiver) && crudServices.update(
                masterReceiver)) {
            return UserSession.updateSession(masterReceiver);
        }
        return false;
    }

    @Override
    @SneakyThrows
    public boolean addMaster(MasterReceiver masterReceiver) {
        if (this.passwordCheck(masterReceiver)) {
            return crudServices.addObject(masterReceiver);
        }
        return false;
    }

    private boolean passwordCheck(MasterReceiver masterReceiver) {
        try {
            if (loginService.searchByUserLoginAndPassword(masterReceiver.getLogin(),
                    masterReceiver.getPassword())) {
                log.info("The username you entered is already taken");
                return false;
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return true;
    }

}
