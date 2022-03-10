package com.netcracker.servisec.Impl.masterReceiver;

import com.netcracker.servisec.FileService;
import com.netcracker.servisec.MasterReceiverServices;
import com.netcracker.servisec.UserSession;
import com.netcracker.user.MasterReceiver;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Slf4j
public class MasterReceiverServicesImpl implements MasterReceiverServices {

    private final CRUDServicesMasterReceiverImpl crudServicesMasterReceiver = new CRUDServicesMasterReceiverImpl();
    private final FileService fileService = new FileService();


    @Override
    public boolean updateMaster(MasterReceiver masterReceiver) {
        if (crudServicesMasterReceiver.updateObject(masterReceiver,
                masterReceiver.getId().toString(),
                fileService.getReceiverFile()))
            return UserSession.updateSession(masterReceiver);
        return false;
    }

    @Override
    public boolean addMaster(MasterReceiver masterReceiver) {
        return crudServicesMasterReceiver.addObject(masterReceiver, new File(FileService.RECEIVER_PATH));
    }


}
