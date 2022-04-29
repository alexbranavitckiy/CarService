package com.netcracker.services;


import com.netcracker.DTO.clients.ClientDto;
import com.netcracker.DTO.errs.SaveSearchErrorException;
import com.netcracker.DTO.response.ContactConfirmationPayload;
import com.netcracker.user.Client;

import java.util.*;

public interface ClientServices {
 //--validate--/
 boolean passwordChek(String password) throws SaveSearchErrorException;

 boolean loginChek(String password) throws SaveSearchErrorException;

 boolean emailChek(String password) throws SaveSearchErrorException;

 boolean phoneChek(String password) throws SaveSearchErrorException;
 //--validate--/

//--client--//
 boolean updateClientData(ContactConfirmationPayload clientFormUpdate, String login) throws SaveSearchErrorException;

 boolean updateClientPass(String clientFormUpdate, String login) throws SaveSearchErrorException;

 boolean updateClientLogin(String newLogin, String oldLogin) throws SaveSearchErrorException;

 boolean updateClientEmail(String newLogin, String oldLogin) throws SaveSearchErrorException;

 boolean updateClientPhone(String newLogin, String oldLogin) throws SaveSearchErrorException;

 boolean updateClientName(ClientDto client, String name)throws SaveSearchErrorException;

 List<Client> getAllClient();

 boolean registrationClient(ClientDto client) throws SaveSearchErrorException;

 Optional<Client> getClientByLogin(String name);

 Optional<ClientDto> getClientsByName(String name);

 Optional<ClientDto> getClientDtoByLogin(String name);

 Map<String, Object> getRoleClientByLogin(String name);

 boolean updateClientByLogin(ClientDto client, String login) throws SaveSearchErrorException;



 //--client--//
}