package com.netcracker.services;


import com.netcracker.DTO.user.ClientDto;
import com.netcracker.DTO.errs.SaveSearchErrorException;
import com.netcracker.DTO.response.ContactConfirmationPayload;
import com.netcracker.user.Client;

import java.util.*;

public interface ClientServices {
 //--validate--/
 boolean loginChek(String login) throws SaveSearchErrorException;

 //--validate--/

 //--client--//

 boolean updateClientPass(String clientFormUpdate, String login) throws SaveSearchErrorException;

 boolean updateClientLogin(String newLogin, String oldLogin) throws SaveSearchErrorException;

 boolean updateClientEmail(String newLogin, String oldLogin) throws SaveSearchErrorException;

 boolean updateClientPhone(String newLogin, String oldLogin) throws SaveSearchErrorException;

 boolean updateClientName(ClientDto client, String name) throws SaveSearchErrorException;


 UUID registrationClient(ClientDto client) throws SaveSearchErrorException;


 Optional<ClientDto> getClientDtoByLogin(String name);

 Map<String, Object> getRoleClientByLogin(String name);

 //--client--//

 UUID registrationMaster(ClientDto clients) throws SaveSearchErrorException;

 List<ClientDto> getAllClientOnMaster() throws SaveSearchErrorException;

 //--search--//
 List<ClientDto> getAllClientOnMaster(String search) throws SaveSearchErrorException;
}
