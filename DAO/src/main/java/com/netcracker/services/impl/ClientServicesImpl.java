package com.netcracker.services.impl;

import com.netcracker.DTO.clients.ClientDto;
import com.netcracker.DTO.clients.MapperClients;
import com.netcracker.repository.ClientsRepository;
import com.netcracker.services.ClientServices;
import com.netcracker.user.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class ClientServicesImpl implements ClientServices {

 private final ClientsRepository clientsRepository;
 private final MapperClients mapperClients;

 @Autowired
 private ClientServicesImpl(MapperClients mapperClients, ClientsRepository clientsRepository) {
  this.clientsRepository = clientsRepository;
  this.mapperClients = mapperClients;
 }

 @Override
 public List<ClientDto> getAllClient() {
  return null;
 }

 @Override
 public boolean addObjectInClient(Client client) {
  return client.equals(clientsRepository.save(client));
 }

 @Override
 public boolean updateClient(ClientDto client) {
  return false;
 }

 @Override
 public Optional<ClientDto> getClientDtoByLogin(String name) {
  Optional<Client> client = clientsRepository.getAllByLogin(name);
  if (client.isEmpty()) {
   return Optional.empty();
  } else return Optional.of(mapperClients.toDto(client.get()));
 }

 @Override
 public Optional<ClientDto> getClientsByName(String name) {
  Optional<Client> client = clientsRepository.getByName(name);
  if (client.isEmpty()) {
   return Optional.empty();
  } else return Optional.of(mapperClients.toDto(client.get()));
 }

 @Override
 public Map<String, Object> getRoleClientByLogin(String login) {
  return clientsRepository.getMyM(login, login, login);
 }

 @Override
 public boolean updateClientByLogin(Client newClient, String login) {
  try {
   Optional<Client> client = clientsRepository.getByName(login);
   client.ifPresent(x -> {
    newClient.setPassword(x.getPassword());
    newClient.setId(x.getId());
    newClient.setRoleUser(x.getRoleUser());
   });
   clientsRepository.save(newClient);
   return true;
  } catch (ConstraintViolationException e) {
   log.warn(e.getMessage());
   return false;
  }
 }

}
