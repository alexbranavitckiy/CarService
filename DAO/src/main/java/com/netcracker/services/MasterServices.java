package com.netcracker.services;

import com.netcracker.DTO.clients.MasterDto;
import com.netcracker.DTO.response.ContactConfirmationPayload;
import com.netcracker.user.Master;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MasterServices {

 boolean updateMasterData(ContactConfirmationPayload clientFormUpdate, String login);

 boolean updateMasterByLogin(MasterDto masterDto, String name);

 Optional<MasterDto> getMasterDtoByLogin(String login);

 Optional<Master> getMasterByLogin(String login);

 boolean addMaster(Master master);

 List<MasterDto> getAllMasterDto();

 boolean updateMaster(MasterDto master,String login);
}

