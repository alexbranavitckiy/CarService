package com.netcracker.services.impl;

import com.netcracker.DTO.user.ClientDto;
import com.netcracker.DTO.convectror.MapperDto;
import com.netcracker.DTO.errs.SaveSearchErrorException;
import com.netcracker.DTO.response.ContactConfirmationPayload;
import com.netcracker.repository.CarClientRepository;
import com.netcracker.repository.ClientsRepository;
import com.netcracker.services.ClientServices;
import com.netcracker.user.Client;
import com.netcracker.user.RoleUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ClientServicesImpl implements ClientServices {

 private final ClientsRepository clientsRepository;
 private final MapperDto<ClientDto, Client> mapperDto;


 @Lazy
 @Autowired
 private ClientServicesImpl(@Qualifier("ClientConvectorImpl") MapperDto<ClientDto, Client> mapperDto,
                            ClientsRepository clientsRepository) {
  this.clientsRepository = clientsRepository;
  this.mapperDto = mapperDto;
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
 public UUID registrationClient(ClientDto client) throws SaveSearchErrorException {
  try {
   client.setId(UUID.randomUUID());
   if (clientsRepository.insertClient(client.getId(), client.getDescription(), client.getEmail(), client.getLogin(),
    client.getName(), client.getPassword(), client.getPhone(), client.getRoleUser().name()) == 1) {
    return client.getId();
   }
  } catch (DataIntegrityViolationException e) {
   throw new SaveSearchErrorException("Registration was not successful:", e.getMessage());
  } catch (Exception e) {
   throw new SaveSearchErrorException("Unknown error:" + e.getMessage());
  }
  throw new SaveSearchErrorException("The entered data is in use by other users.");
 }

 @Override
 public boolean passwordChek(String password) throws SaveSearchErrorException {
  if (!clientsRepository.existsByPassword(password)) {
   return false;
  }
  throw new SaveSearchErrorException("Invalid password entered.", "password");
 }

 @Override
 public boolean loginChek(String login) throws SaveSearchErrorException {
  if (clientsRepository.existsByLoginClientAndMaster(login, login) == 0) {
   return false;
  }
  throw new SaveSearchErrorException("The login you entered is in use by another user.", "login");
 }

 @Override
 public boolean idChek(UUID uuid) throws SaveSearchErrorException {
  if (clientsRepository.existsById(uuid)) {
   return false;
  }
  throw new SaveSearchErrorException("The client id you entered does not exist..", "Id");
 }

 @Override
 public boolean emailChek(String email) throws SaveSearchErrorException {
  if (!clientsRepository.existsByEmail(email)) {
   return false;
  }
  throw new SaveSearchErrorException("The entered mail is busy by another user.", "email");
 }

 @Override
 public boolean phoneChek(String phone) throws SaveSearchErrorException {
  if (!clientsRepository.existsByPhone(phone)) {
   return false;
  }
  throw new SaveSearchErrorException("The entered phone number is already registered.", "phone");
 }

 @Override
 public boolean updateClientData(ContactConfirmationPayload contactConfirmationPayload, String login)
  throws SaveSearchErrorException {
  try {
   clientsRepository.updatePasswordAndLogin(contactConfirmationPayload.getPassword(),
    contactConfirmationPayload.getLogin(), login);
   return true;
  } catch (Exception e) {
   log.warn(e.getMessage());
   throw new SaveSearchErrorException("The entered data is not valid");
  }
 }

 @Override
 public boolean updateClientPass(String clientFormUpdate, String login) throws SaveSearchErrorException {
  try {
   clientsRepository.updatePassword(clientFormUpdate, login);
   return true;
  } catch (DataIntegrityViolationException e) {
   throw new SaveSearchErrorException("Invalid password entered" + e.getMessage());
  } catch (Exception e) {
   throw new SaveSearchErrorException("Unknown error:" + e.getMessage());
  }
 }

 @Override
 public boolean updateClientLogin(String newLogin, String oldLogin) throws SaveSearchErrorException {
  try {
   clientsRepository.updateLogin(newLogin, oldLogin);
   return true;
  } catch (DataIntegrityViolationException e) {
   throw new SaveSearchErrorException("Invalid login entered" + e.getMessage(), "login");
  } catch (Exception e) {
   throw new SaveSearchErrorException("Unknown error:" + e.getMessage());
  }
 }

 @Override
 public boolean updateClientEmail(String email, String oldLogin) throws SaveSearchErrorException {
  try {
   clientsRepository.updateEmail(email, oldLogin);
   return true;
  } catch (DataIntegrityViolationException e) {
   throw new SaveSearchErrorException("The email you entered is being used by another user");
  } catch (Exception e) {
   throw new SaveSearchErrorException("Unknown error:" + e.getMessage());
  }
 }

 @Override
 public boolean updateClientPhone(String newLogin, String oldLogin) throws SaveSearchErrorException {
  try {
   clientsRepository.updatePhone(newLogin, oldLogin);
   return true;
  } catch (DataIntegrityViolationException e) {
   throw new SaveSearchErrorException("The phone you entered is being used by another user");
  } catch (Exception e) {
   throw new SaveSearchErrorException("Unknown error:" + e.getMessage());
  }
 }

 @Override
 public boolean updateClientName(ClientDto clientDto, String oldLogin) throws SaveSearchErrorException {
  try {
   if (clientsRepository.updateName(clientDto.getName(), oldLogin) == 1)
    return true;
  } catch (DataIntegrityViolationException e) {
   throw new SaveSearchErrorException("Name change was not successful");
  } catch (Exception e) {
   throw new SaveSearchErrorException("Unknown error:" + e.getMessage());
  }
  throw new SaveSearchErrorException("Name change was not successful");
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
  return clientsRepository.getMyUser(login, login);
 }

 @Override
 public boolean updateClientByLogin(ClientDto updateClient, String login) throws SaveSearchErrorException {
  try {
   if (clientsRepository.updateClient(updateClient.getName(), updateClient.getPhone(), updateClient.getEmail(),
    updateClient.getDescription(), login) > 0)
    return true;
  } catch (Exception e) {
   throw new SaveSearchErrorException(e.getMessage());
  }
  throw new SaveSearchErrorException("The entered data is in use by other users.");
 }

 @Override
 public UUID registrationMaster(ClientDto clients) throws SaveSearchErrorException {
  try {
   clients.setRoleUser(RoleUser.UNREGISTERED);
   clients.setId(UUID.randomUUID());
   if (clientsRepository.insertClient(clients.getId(), clients.getDescription(), clients.getEmail(), clients.getLogin(),
    clients.getName(), clients.getPassword(), clients.getPhone(), clients.getRoleUser().name()) == 1) {
    return clients.getId();
   }
  } catch (DataIntegrityViolationException e) {
   throw new SaveSearchErrorException("Registration was not successful", e.getMessage());
  } catch (Exception e) {
   log.error(e.getMessage());
  }
  throw new SaveSearchErrorException("The entered data is in use by other users.");
 }

 @Override
 public List<ClientDto> getAllClientOnMaster() throws SaveSearchErrorException {
  try {
   return clientsRepository.getAllBy().stream().map(mapperDto::toDto).collect(Collectors.toList());
  } catch (Exception e) {
   throw new SaveSearchErrorException("Unknown error:" + e.getMessage());
  }
 }

 @Override
 public List<ClientDto> getAllClientOnMaster(String search) throws SaveSearchErrorException {
  try {
   return clientsRepository.getAllByLike("%" + search + "%").stream().map(mapperDto::toDto)
    .collect(Collectors.toList());
  } catch (Exception e) {
   throw new SaveSearchErrorException("Unknown error:" + e.getMessage());
  }
 }

}
