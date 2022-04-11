package com.netcracker.services;

import com.netcracker.user.Master;
import com.netcracker.user.MasterReceiver;

import java.util.List;

public interface MasterReceiverServices {

 boolean updateMaster(MasterReceiver masterReceiver);

 boolean updateMasterAndSession(MasterReceiver masterReceiver) ;

 List<Master> getMasterReceiverByLogin(String name);

 boolean addMaster(MasterReceiver masterReceiver);

 List<MasterReceiver> getAllMasterReceiver() ;
}
