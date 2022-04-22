package com.netcracker.services.impl;

import com.netcracker.DTO.clients.ClientDto;
import com.netcracker.DTO.convectror.ClientConvectorImpl;
import com.netcracker.DTO.convectror.MapperDto;
import com.netcracker.DTO.response.ContactConfirmationPayload;
import com.netcracker.repository.CarClientRepository;
import com.netcracker.repository.ClientsRepository;
import com.netcracker.services.ClientServices;
import com.netcracker.user.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class ClientServicesImpl implements ClientServices {

 private final ClientsRepository clientsRepository;
 private final CarClientRepository carClientRepository;
 private final ClientConvectorImpl clientConvector;
 private final MapperDto<ClientDto, Client> mapperDto;


 @Lazy
 @Autowired
 private ClientServicesImpl(@Qualifier("ClientConvectorImpl") MapperDto<ClientDto, Client> mapperDto, ClientConvectorImpl clientConvector, CarClientRepository carClientRepository, ClientsRepository clientsRepository) {
  this.clientsRepository = clientsRepository;
  this.mapperDto = mapperDto;
  this.clientConvector = clientConvector;
  this.carClientRepository = carClientRepository;
 }

 @Override
 public Optional<Client> getClientByLogin(String name) {
  return clientsRepository.getAllByLogin(name);
 }

 @Override
 public List<Client> getAllClient() {
  return clientsRepository.getAllBy();
 }

 @Override
 public boolean registrationClient(Client client) {
  try {
   Optional<Client> optionalClient = clientsRepository.getByName(client.getLogin());
   if (optionalClient.isEmpty()) {
    clientsRepository.save(client);
    return true;
   }
  } catch (Exception e) {
   log.warn(e.getMessage());
   return false;
  }
  return false;
 }

 @Override
 public boolean updateClientData(ContactConfirmationPayload contactConfirmationPayload, String login) {
  try {
   clientsRepository.updatePassword(contactConfirmationPayload.getPassword(), contactConfirmationPayload.getUsername(), login);
   return true;
  } catch (Exception e) {
   log.warn(e.getMessage());
  }
  return false;
 }

 @Override
 public Optional<ClientDto> getClientDtoByLogin(String name) {
  Optional<Client> client = clientsRepository.getAllByLogin(name);
  if (client.isEmpty()) {
   return Optional.empty();
  } else return Optional.of(mapperDto.toDto(client.get()));
 }

 @Override
 public Optional<ClientDto> getClientsByName(String name) {
  Optional<Client> client = clientsRepository.getByName(name);
  if (client.isEmpty()) {
   return Optional.empty();
  } else return Optional.of(mapperDto.toDto(client.get()));
 }

 @Override
 public Map<String, Object> getRoleClientByLogin(String login) {
  return clientsRepository.getMyUser(login, login, login);
 }

 @Override
 public boolean updateClientByLogin(ClientDto updateClient, String login) {
  try {
   clientsRepository.updateClient(updateClient.getName(), updateClient.getPhone(), updateClient.getEmail(), updateClient.getDescription(), login);
   return true;
  } catch (Exception e) {
   log.warn("{}",e);
  }
  return false;
 }

}
