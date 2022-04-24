package com.netcracker.services;


import com.netcracker.DTO.clients.ClientDto;
import com.netcracker.DTO.errs.SaveErrorException;
import com.netcracker.DTO.response.ContactConfirmationPayload;
import com.netcracker.user.Client;

import java.util.*;

public interface ClientServices {
 //--validate--/

 boolean passwordChek(String password) throws SaveErrorException;

 boolean loginChek(String password) throws SaveErrorException;

 boolean emailChek(String password) throws SaveErrorException;

 boolean phoneChek(String password) throws SaveErrorException;

 //--validate--/
//--client--//
 boolean updateClientData(ContactConfirmationPayload clientFormUpdate, String login) throws SaveErrorException;

 List<Client> getAllClient();

 boolean registrationClient(ClientDto client) throws SaveErrorException;

 Optional<Client> getClientByLogin(String name);

 Optional<ClientDto> getClientsByName(String name);

 Optional<ClientDto> getClientDtoByLogin(String name);

 Map<String, Object> getRoleClientByLogin(String name);

 boolean updateClientByLogin(ClientDto client, String login) throws SaveErrorException;

 //--client--//
}
