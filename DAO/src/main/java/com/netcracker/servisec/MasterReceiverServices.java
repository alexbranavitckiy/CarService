package com.netcracker.servisec;

import com.netcracker.user.MasterReceiver;
import java.io.IOException;

public interface MasterReceiverServices {

  boolean updateMaster(MasterReceiver masterReceiver) throws IOException;

  boolean updateMasterAndSession(MasterReceiver masterReceiver) throws IOException;

  boolean addMaster(MasterReceiver masterReceiver);
}
