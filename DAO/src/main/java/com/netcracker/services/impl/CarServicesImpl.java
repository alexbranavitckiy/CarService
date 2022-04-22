package com.netcracker.services.impl;

import com.netcracker.DTO.MapperCar;
import com.netcracker.DTO.car.CarClientDto;
import com.netcracker.DTO.convectror.MapperDto;
import com.netcracker.car.CarClient;
import com.netcracker.repository.CarClientRepository;
import com.netcracker.services.CarServices;
import com.netcracker.services.ClientServices;
import com.netcracker.user.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Lazy
@Slf4j
@Service
public class CarServicesImpl implements CarServices {

 private final CarClientRepository carClientRepository;
 private final ClientServices clientServices;
 private final MapperCar mapperCar;
 private final MapperDto<CarClientDto, CarClient> clientMapperDto;

 @Autowired
 private CarServicesImpl(@Qualifier("CarConvectorImpl") MapperDto<CarClientDto, CarClient> clientMapperDto, MapperCar mapperCar, ClientServices clientServices, CarClientRepository carClientRepository) {
  this.clientServices = clientServices;
  this.clientMapperDto = clientMapperDto;
  this.mapperCar = mapperCar;
  this.carClientRepository = carClientRepository;
 }

 @Override
 public List<CarClientDto> getCarByIdClientOnClient(UUID uuidClient, String login) {
  return null;
 }

 @Override
 public List<CarClientDto> getCarByLoginClient(String login) {
  List<CarClient> carClients = carClientRepository.getAllByClientLogin(login);
  return carClients.stream().map(clientMapperDto::toDto).collect(Collectors.toList());
 }

 @Override
 public Optional<CarClientDto> getCarByIdCarOnClient(UUID uuidCar, String login) {
  Optional<CarClient> carClient = carClientRepository.getAllByIdAndClient_Login(uuidCar, login);
  return carClient.map(clientMapperDto::toDto);
 }

 @Override
 public boolean createCarOnClient(CarClient carClient, String login) {
  try {
   Optional<Client> client = clientServices.getClientByLogin(login);
   if (client.isPresent()) {
    carClient.setClient(client.get());
    carClient.setId(UUID.randomUUID());
    carClientRepository.save(carClient);
    return true;
   }
  } catch (Exception e) {
   log.warn(e.getMessage());
   return false;
  }
  return false;
 }

 @Override
 public boolean updateCarClientByLogin(CarClientDto carClient, String login) {
  try {
   Optional<Client> client = clientServices.getClientByLogin(login);
   if (client.isPresent()) {
    Optional<CarClient> oldCar = client.get().getCars().stream().filter(x -> x.getId().equals(carClient.getId())).findFirst();
    if (oldCar.isPresent()) {
     carClientRepository.save(mapperCar.toEntityUpdate(carClient, oldCar.get()));
     return true;
    }
   }
  } catch (Exception e) {
   log.warn(e.getMessage());
   return false;
  }
  return false;
 }

 @Override
 public Optional<CarClient> getCarByIdOnMaster(UUID uuidCar) {
  return carClientRepository.getById(uuidCar);
 }
}
