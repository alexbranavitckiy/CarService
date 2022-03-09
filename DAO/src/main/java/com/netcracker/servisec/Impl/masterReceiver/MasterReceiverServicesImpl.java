package com.netcracker.servisec.Impl.masterReceiver;

import com.netcracker.servisec.CRUDServices;
import com.netcracker.servisec.FileService;
import com.netcracker.servisec.MasterReceiverServices;
import com.netcracker.servisec.UserSession;
import com.netcracker.user.MasterReceiver;


public class MasterReceiverServicesImpl implements MasterReceiverServices {

    private final CRUDServicesMasterImpl crudServicesMasterReceiver = new CRUDServicesMasterImpl();
    private final FileService fileService = new FileService();


    public boolean updateMaster(MasterReceiver masterReceiver) {
        if (crudServicesMasterReceiver.updateObject(masterReceiver,
                masterReceiver.getId().toString(),
                fileService.getReceiverFile()))
            return UserSession.updateSession(masterReceiver);
        return false;
    }

}
