package com.netcracker.services;

import com.netcracker.DTO.clients.MasterDto;
import com.netcracker.user.Master;
import com.netcracker.user.MasterReceiver;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MasterReceiverServices {

 Optional<MasterReceiver> getMasterReceiverByLogin(String name);

 boolean addMaster(MasterReceiver masterReceiver);

 Optional<MasterDto> getMasterDtoByLogin(String login);

 Optional<MasterDto> getMasterReceiverById(UUID idMaster);
}
