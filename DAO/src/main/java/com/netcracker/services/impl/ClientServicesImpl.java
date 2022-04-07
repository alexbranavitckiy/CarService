package com.netcracker.services.impl;

import ch.qos.logback.core.net.server.Client;
import com.netcracker.repository.ClientsRepository;
import com.netcracker.services.ClientServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServicesImpl implements ClientServices {

 private final ClientsRepository clientsRepository;

 @Autowired
 private ClientServicesImpl(ClientsRepository clientsRepository) {
  this.clientsRepository = clientsRepository;


 }

 @Override
 public List<Client> getAllClient() {
  return null;
 }

 @Override
 public boolean addObjectInClient(Client client) {
  return false;
 }

 @Override
 public boolean addObjectInClientNotOnline(Client client) {
  return false;
 }

 @Override
 public boolean updateClient(Client client) {
  return false;
 }

 @Override
 public boolean updateClientNotSession(Client client) {
  return false;
 }
}
