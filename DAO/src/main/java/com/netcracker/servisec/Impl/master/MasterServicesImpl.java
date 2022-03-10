package com.netcracker.servisec.Impl.master;

import com.netcracker.errors.WritingException;
import com.netcracker.servisec.CRUDServices;
import com.netcracker.servisec.FileService;
import com.netcracker.servisec.Impl.LoginServicesImpl;
import com.netcracker.servisec.LoginService;
import com.netcracker.servisec.MasterServices;
import com.netcracker.user.Master;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class MasterServicesImpl implements MasterServices {

    private final CRUDServices crudServicesMaster = new CRUDServicesMasterImpl();
    private final FileService fileService = new FileService();
    private final LoginService loginService=new LoginServicesImpl();

    public boolean addMaster(Master master) {
        try {
            return this.crudServicesMaster.addObject(master, fileService.getMasterFile());
        } catch (WritingException w) {
            log.error("Save error, please try again. {}",w.getLocalizedMessage());
        }
        return false;

    }


}
