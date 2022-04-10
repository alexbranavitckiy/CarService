package com.netcracker.services;


import com.netcracker.user.Clients;

import java.util.List;
import java.util.Optional;

public interface ClientServices {

 List<Clients> getAllClient() ;

 boolean addObjectInClient(Clients client) ;

 boolean addObjectInClientNotOnline(Clients client) ;

 boolean updateClient(Clients client);

 boolean updateClientNotSession(Clients client) ;

 Optional<Clients> getClientsByName(String name) ;

}
