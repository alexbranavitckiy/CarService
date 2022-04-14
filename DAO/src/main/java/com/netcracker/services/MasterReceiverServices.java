package com.netcracker.services;

import com.netcracker.user.Master;
import com.netcracker.user.MasterReceiver;

import java.util.List;
import java.util.Optional;

public interface MasterReceiverServices {

 boolean updateMaster(MasterReceiver masterReceiver);

 boolean updateMasterAndSession(MasterReceiver masterReceiver) ;

 Optional<MasterReceiver> getMasterReceiverByLogin(String name);

 boolean addMaster(MasterReceiver masterReceiver);

 List<MasterReceiver> getAllMasterReceiver() ;
}
