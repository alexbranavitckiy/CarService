package com.netcracker.services;

import com.netcracker.user.Master;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MasterServices {

 List<Master> getAllMaster();

 List<Master> getMasterByLogin(String name);

 boolean addMaster(Master master);

 Optional<Master> getMasterById(UUID master) ;

 boolean updateMaster(Master master);
}
