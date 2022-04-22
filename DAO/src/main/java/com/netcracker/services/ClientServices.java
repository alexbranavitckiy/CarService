package com.netcracker.services;


import com.netcracker.DTO.clients.ClientDto;
import com.netcracker.DTO.response.ContactConfirmationPayload;
import com.netcracker.user.Client;

import java.util.*;

public interface ClientServices {
//--client--//
 boolean updateClientData(ContactConfirmationPayload clientFormUpdate, String login);

 List<Client> getAllClient();

 boolean registrationClient(Client client);

 Optional<Client> getClientByLogin(String name);

 Optional<ClientDto> getClientsByName(String name);

 Optional<ClientDto> getClientDtoByLogin(String name);

 Map<String, Object> getRoleClientByLogin(String name);

 boolean updateClientByLogin(ClientDto client, String login);

 //--client--//
}
