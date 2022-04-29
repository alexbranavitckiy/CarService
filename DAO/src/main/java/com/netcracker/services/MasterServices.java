package com.netcracker.services;

import com.netcracker.DTO.clients.MasterDto;
import com.netcracker.DTO.errs.SaveSearchErrorException;
import com.netcracker.DTO.response.ContactConfirmationPayload;
import com.netcracker.user.Master;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MasterServices {

 boolean updateMasterData(ContactConfirmationPayload clientFormUpdate, String login);

 boolean updateMasterByLogin(MasterDto masterDto, String name) throws SaveSearchErrorException;

 Optional<Master> getMasterByLogin(String login);

 boolean addMaster(Master master);

 List<MasterDto> getAllMasterDto();

 boolean updateMaster(MasterDto master, String login);

 //--Master--//
 Optional<MasterDto> getMasterDtoByLogin(String login);

 boolean updateMasterPass(String clientFormUpdate, String login) throws SaveSearchErrorException;

 boolean updateMasterLogin(String newLogin, String oldLogin) throws SaveSearchErrorException;

 boolean updateMasterEmail(String newLogin, String oldLogin) throws SaveSearchErrorException;

 boolean updateMasterPhone(String newLogin, String oldLogin) throws SaveSearchErrorException;
 //--Master--//

 //--validate--/
 boolean passwordChek(String password) throws SaveSearchErrorException;

 boolean loginChek(String password) throws SaveSearchErrorException;

 boolean emailChek(String password) throws SaveSearchErrorException;

 boolean phoneChek(String password) throws SaveSearchErrorException;
 //--validate--/

}

