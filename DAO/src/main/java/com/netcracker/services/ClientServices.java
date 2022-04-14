package com.netcracker.services;


import com.netcracker.DTO.clients.ClientDto;
import com.netcracker.user.Client;

import java.util.*;

public interface ClientServices {

 List<ClientDto> getAllClient();

 boolean addObjectInClient(Client client);

 boolean updateClient(ClientDto client);

 boolean updateClientByLogin(Client client, String login);

 Map<String, Object> getRoleClientByLogin(String name);

 Optional<ClientDto> getClientDtoByLogin(String name);

 Optional<ClientDto> getClientsByName(String name);

}
