package com.netcracker.services;

import com.netcracker.DTO.user.MasterDto;
import com.netcracker.DTO.errs.SaveSearchErrorException;
import com.netcracker.user.Master;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MasterServices {

 boolean updateMasterByLogin(MasterDto masterDto, String name) throws SaveSearchErrorException;

 //--Master--//
 Optional<MasterDto> getMasterDtoByLogin(String login);

 boolean updateMasterPass(String clientFormUpdate, String login) throws SaveSearchErrorException;

 boolean updateMasterLogin(String newLogin, String oldLogin) throws SaveSearchErrorException;

 boolean updateMasterEmail(String email, String oldLogin) throws SaveSearchErrorException;

 boolean updateMasterPhone(String newLogin, String oldLogin) throws SaveSearchErrorException;
 //--Master--//

 //--validate--/

 UUID createMasterOnMasterReceiver(MasterDto master, String name) throws SaveSearchErrorException;

 List<MasterDto> getMasterDtoOnMaster() throws SaveSearchErrorException;
 //--validate--/

}

