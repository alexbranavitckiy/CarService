package com.netcracker.jdbc.services.impl.master;

import com.netcracker.LoginServices;
import com.netcracker.MasterServices;
import com.netcracker.errors.EmptySearchException;
import com.netcracker.file.services.impl.login.LoginServicesImpl;
import com.netcracker.jdbc.services.CrudDao;
import com.netcracker.jdbc.services.impl.MasterDaoImpl;
import com.netcracker.user.Master;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
public class MasterDaoServicesImpl implements MasterServices {

    private final LoginServices loginService = new LoginServicesImpl();
    private final CrudDao<Master, UUID> crudServices = new MasterDaoImpl();

    public MasterDaoServicesImpl() {
    }

    public boolean addMaster(Master master) {
        try {
            if (this.passwordCheck(master)) {
                return this.crudServices.addObject(master);
            }
        } catch (Exception e) {
            log.error("Error adding object", e);
        }
        return false;
    }

    @Override
    @SneakyThrows
    public Optional<Master> getMasterById(UUID master) throws EmptySearchException {
        return crudServices.getById(master);

    }

    @SneakyThrows
    public List<Master> getAllMaster() throws EmptySearchException {
        return this.crudServices.getAll();
    }


    public boolean passwordCheck(Master master) {
        try {//TODO!!
            if (loginService.searchByUserLoginAndPassword(master.getLogin(),
                    master.getPassword())) {
                log.info("The username you entered is already taken");
                return false;
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return true;
    }

}
