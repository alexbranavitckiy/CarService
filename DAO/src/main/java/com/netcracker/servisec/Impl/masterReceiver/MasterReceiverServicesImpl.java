package com.netcracker.servisec.Impl.masterReceiver;

import com.netcracker.servisec.CRUDServices;
import com.netcracker.servisec.FileService;
import com.netcracker.servisec.Impl.CRUDServicesImpl;
import com.netcracker.servisec.Impl.LoginServicesImpl;
import com.netcracker.servisec.LoginService;
import com.netcracker.servisec.MasterReceiverServices;
import com.netcracker.servisec.UserSession;
import com.netcracker.user.MasterReceiver;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Slf4j
public class MasterReceiverServicesImpl implements MasterReceiverServices {


  private final FileService fileService = new FileService();
  private final LoginService loginService = new LoginServicesImpl();
  private final CRUDServices<MasterReceiver> searchServices = new CRUDServicesImpl<>();


  @Override
  public boolean updateMaster(MasterReceiver masterReceiver) {
    if (this.passwordCheck(masterReceiver) && searchServices.updateObject(
        masterReceiver,
        fileService.getReceiverFile(), MasterReceiver[].class)) {
      return UserSession.updateSession(masterReceiver);
    }
    return false;
  }

  @Override
  public boolean updateMasterAndSession(MasterReceiver masterReceiver) {
    if (this.passwordCheck(masterReceiver) && searchServices.updateObject(
        masterReceiver,
        fileService.getReceiverFile(), MasterReceiver[].class)) {
      return UserSession.updateSession(masterReceiver);
    }
    return false;
  }

  @Override
  public boolean addMaster(MasterReceiver masterReceiver) {
    if (this.passwordCheck(masterReceiver)) {
      return searchServices.addObject(masterReceiver,
          new File(FileService.RECEIVER_PATH), MasterReceiver[].class);
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
