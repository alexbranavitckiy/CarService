package com.netcracker.services.impl;

import com.netcracker.DTO.clients.ClientsDto;
import com.netcracker.repository.ClientsRepository;
import com.netcracker.services.ClientServices;
import com.netcracker.user.Clients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
 public List<ClientsDto> getAllClient() {
  return new ArrayList<>(clientsRepository.getAllBy());
 }

 @Override
 public boolean addObjectInClient(Clients client) {
  return client.equals(clientsRepository.save(client));
 }


 @Override
 public boolean updateClient(ClientsDto client) {
  return false;
 }

 @Override
 public Optional<ClientsDto> getClientsByLogin(String name) {
  return clientsRepository.getByLogin(name);
 }

 @Override
 public Optional<ClientsDto> getClientsByName(String name) {
  return clientsRepository.getByName(name);
 }

}
