package com.netcracker.services;


import com.netcracker.DTO.clients.ClientsDto;
import com.netcracker.user.Clients;

import java.util.List;
import java.util.Optional;

public interface ClientServices {

 List<ClientsDto> getAllClient() ;

 boolean addObjectInClient(Clients client) ;

 boolean updateClient(ClientsDto client);

 Optional<ClientsDto> getClientsByLogin(String name) ;

 Optional<ClientsDto> getClientsByName(String name);

}
