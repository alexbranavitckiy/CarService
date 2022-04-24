package com.netcracker.services.impl;

import com.netcracker.DTO.clients.ClientDto;
import com.netcracker.DTO.convectror.ClientConvectorImpl;
import com.netcracker.DTO.convectror.MapperDto;
import com.netcracker.DTO.errs.SaveErrorException;
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
import java.util.UUID;

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
 public boolean registrationClient(ClientDto client) throws SaveErrorException {
  try {
   client.setId(UUID.randomUUID());
   Client clientNew = mapperDto.toEntity(client);
   if (clientsRepository.insertClient(clientNew.getId(), clientNew.getDescription(), clientNew.getEmail(), clientNew.getLogin(), clientNew.getName(), clientNew.getPassword(), clientNew.getPhone(), clientNew.getRoleUser().name()) == 1) {
    return true;
   }
  } catch (Exception e) {
   log.warn(e.getMessage());
   throw new SaveErrorException(e.getMessage());
  }
  throw new SaveErrorException("The entered data is in use by other users.");
 }

 @Override
 public boolean passwordChek(String password) throws SaveErrorException {
  if (clientsRepository.getAllByPassword(password).isEmpty())
   return true;
  throw new SaveErrorException("Invalid password entered.","password");
 }

 @Override
 public boolean loginChek(String login) throws SaveErrorException {
  if (clientsRepository.getAllByLogin(login).isEmpty())
   return true;
  throw new SaveErrorException("The login you entered is in use by another user.","login");
 }

 @Override
 public boolean emailChek(String email) throws SaveErrorException {
  if (clientsRepository.getAllByEmail(email).isEmpty())
   return true;
  throw new SaveErrorException("The entered mail is busy by another user.","email");
 }

 @Override
 public boolean phoneChek(String phone) throws SaveErrorException {
  if (clientsRepository.getAllByPhone(phone).isEmpty())
   return true;
  throw new SaveErrorException("The entered phone number is already registered.","phone");
 }

 @Override
 public boolean updateClientData(ContactConfirmationPayload contactConfirmationPayload, String login) throws SaveErrorException {
  try {
   clientsRepository.updatePassword(contactConfirmationPayload.getPassword(), contactConfirmationPayload.getLogin(), login);
   return true;
  } catch (Exception e) {
   log.warn(e.getMessage());
   throw new SaveErrorException("The entered data is not valid");
  }
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
 public boolean updateClientByLogin(ClientDto updateClient, String login) throws SaveErrorException {
  try {
   if (clientsRepository.updateClient(updateClient.getName(), updateClient.getPhone(), updateClient.getEmail(), updateClient.getDescription(), login) > 0)
    return true;
  } catch (Exception e) {
   throw new SaveErrorException(e.getMessage());
  }
  throw new SaveErrorException("The entered data is in use by other users.");
 }
}
