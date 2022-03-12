package com.netcracker.servisec.Impl.master;

import com.netcracker.servisec.CRUDServices;
import com.netcracker.servisec.FileService;
import com.netcracker.servisec.Impl.CRUDServicesImpl;
import com.netcracker.servisec.Impl.LoginServicesImpl;
import com.netcracker.servisec.LoginService;
import com.netcracker.servisec.MasterServices;
import com.netcracker.user.Master;
import java.io.IOException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MasterServicesImpl implements MasterServices {

  private final FileService fileService = new FileService();
  private final LoginService loginService = new LoginServicesImpl();
  private final CRUDServices<Master> crudServices = new CRUDServicesImpl<>();

  public boolean addMaster(Master master) {
    try {
      if (this.passwordCheck(master)) {
        return this.crudServices.addObject(master, fileService.getMasterFile(), Master[].class);
      }
    } catch (Exception e) {
      log.error("Error adding object", e);
    }
    return false;
  }


  public List<Master> getAllMaster() {
    return this.getAllMaster();
  }


  private boolean passwordCheck(Master master) {
    try {
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
