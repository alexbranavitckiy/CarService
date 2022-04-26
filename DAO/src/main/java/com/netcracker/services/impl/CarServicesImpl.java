package com.netcracker.services.impl;

import com.netcracker.DTO.car.CarClientDto;
import com.netcracker.DTO.convectror.MapperDto;
import com.netcracker.DTO.errs.SaveErrorException;
import com.netcracker.car.CarClient;
import com.netcracker.repository.CarClientRepository;
import com.netcracker.services.CarServices;
import com.netcracker.services.ClientServices;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
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
 private final MapperDto<CarClientDto, CarClient> clientMapperDto;

 @Autowired
 private CarServicesImpl(@Qualifier("CarConvectorImpl") MapperDto<CarClientDto, CarClient> clientMapperDto, ClientServices clientServices, CarClientRepository carClientRepository) {
  this.clientServices = clientServices;
  this.clientMapperDto = clientMapperDto;
  this.carClientRepository = carClientRepository;
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
 public boolean createCarOnClient(CarClientDto carClient, String login) throws SaveErrorException {
  try {
   carClient.setId(UUID.randomUUID());
   if (carClientRepository.createCarOnLogin(carClient.getId(), carClient.getDescription(), carClient.getEar(), carClient.getMetadataCar(), carClient.getRun(), carClient.getSummary(), login, carClient.getMark().getId()) > 0)
    return true;
  } catch (DataAccessException e) {
   throw new SaveErrorException("The entered data is in use by other users." + e.getMessage());
  }
  return false;
 }

 @Override
 public boolean updateCarClientByIdWithMachineNumber(CarClientDto carClient, String login) throws SaveErrorException {
  try {
   return carClientRepository.updateCarClientById(carClient.getMetadataCar(), carClient.getId(), login) == 1;
  } catch (DataAccessException e) {
   throw new SaveErrorException("The entered data is in use by other users." + e.getMessage());
  }
 }

 @Override
 public boolean updateCarClientByLogin(CarClientDto carClient, String login) throws SaveErrorException {
  try {
   return carClientRepository.updateCarClientByIdWithoutMachineNumber(carClient.getDescription(), carClient.getEar(), carClient.getRun(), carClient.getId(), login) == 1;
  } catch (DataAccessException e) {
   throw new SaveErrorException("The entered data is in use by other users." + e.getMessage());
  }
 }


 @Override
 public boolean metadataCarChek(String metadata) throws SaveErrorException {
  if (carClientRepository.existsByMetadataCar(metadata))
   throw new SaveErrorException("This car number is already registered.", "metadataCar");
  return true;
 }


 @Override
 public Optional<CarClient> getCarByIdOnMaster(UUID uuidCar) {
  return carClientRepository.getById(uuidCar);
 }
}
