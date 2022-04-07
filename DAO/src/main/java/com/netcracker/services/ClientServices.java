package com.netcracker.services;


import ch.qos.logback.core.net.server.Client;
import java.util.List;

public interface ClientServices {

 List<Client> getAllClient() ;

 boolean addObjectInClient(Client client) ;

 boolean addObjectInClientNotOnline(Client client) ;

 boolean updateClient(Client client);

 boolean updateClientNotSession(Client client) ;

}
