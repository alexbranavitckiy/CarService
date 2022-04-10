package com.netcracker.services.impl;

import com.netcracker.repository.ClientsRepository;
import com.netcracker.services.ClientServices;
import com.netcracker.user.Clients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServicesImpl implements ClientServices {

 private final ClientsRepository clientsRepository;

 @Autowired
 private ClientServicesImpl(ClientsRepository clientsRepository) {
  this.clientsRepository = clientsRepository;


 }

 @Override
 public List<Clients> getAllClient() {
  return null;
 }

 @Override
 public boolean addObjectInClient(Clients client) {
  return false;
 }

 @Override
 public boolean addObjectInClientNotOnline(Clients client) {
  return false;
 }

 @Override
 public boolean updateClient(Clients client) {
  return false;
 }

 @Override
 public boolean updateClientNotSession(Clients client) {
  return false;
 }


 @Override
 public Optional<Clients> getClientsByName(String name) {
  return clientsRepository.getAllByName(name);
 }

}
