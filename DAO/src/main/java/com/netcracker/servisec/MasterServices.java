package com.netcracker.servisec;

import com.netcracker.user.Master;
import java.util.List;

public interface MasterServices {

  List<Master> getAllMaster();

  boolean addMaster(Master master);
}
