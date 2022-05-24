package com.netcracker.services.impl;

import com.netcracker.DTO.car.CarClientDto;
import com.netcracker.DTO.convectror.MapperDto;
import com.netcracker.DTO.errs.ApiError;
import com.netcracker.DTO.errs.SaveSearchErrorException;
import com.netcracker.car.CarClient;
import com.netcracker.repository.CarClientRepository;
import com.netcracker.services.CarServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Lazy
@Slf4j
@Service
public class CarServicesImpl implements CarServices {

 private final CarClientRepository carClientRepository;
 private final MapperDto<CarClientDto, CarClient> clientMapperDto;

 @Autowired
 private CarServicesImpl(@Qualifier("CarConvectorImpl") MapperDto<CarClientDto, CarClient> clientMapperDto,
                         CarClientRepository carClientRepository) {
  this.clientMapperDto = clientMapperDto;
  this.carClientRepository = carClientRepository;
 }


 @Override
 public List<CarClientDto> getCarByLoginClient(String login, Integer offset, Integer limit)
  throws SaveSearchErrorException {
  try {
   return carClientRepository.getAllByClientLogin(login, PageRequest.of(offset, limit))
    .stream().map(clientMapperDto::toDto).collect(Collectors.toList());
  } catch (Exception e) {
   log.error(e.getMessage());
   throw new SaveSearchErrorException("Unknown error.{}", e.getMessage());
  }
 }

 @Override
 public Optional<CarClientDto> getCarByIdCarOnClient(UUID uuidCar, String login) throws SaveSearchErrorException {
  try {
   return carClientRepository.getAllByIdAndClient_Login(uuidCar, login).map(clientMapperDto::toDto);
  } catch (Exception e) {
   log.error(e.getMessage());
   throw new SaveSearchErrorException("Unknown error.{}", e.getMessage());
  }
 }

 @Override
 public UUID createCarOnMaster(CarClientDto carClient) throws SaveSearchErrorException {
  try {
   carClient.setId(UUID.randomUUID());
   if (carClientRepository.createCarOnMaster(carClient.getId(), carClient.getDescription(),
    carClient.getYear(), carClient.getMetadataCar(), carClient.getRun(), carClient.getSummary(),
    carClient.getMark().getId()) > 0)
    return carClient.getId();
  } catch (Exception e) {
   throw new SaveSearchErrorException("The entered data is in use by other users.{}", e.getMessage());
  }
  throw new SaveSearchErrorException("Invalid values entered.");
 }

 @Override
 public List<CarClientDto> getAllCarOnMaster() throws SaveSearchErrorException {
  try {
   return carClientRepository.getAllBy().stream().map(clientMapperDto::toDto).collect(Collectors.toList());
  } catch (Exception e) {
   throw new SaveSearchErrorException("Unknown error. {}", e.getMessage());
  }
 }

 @Override
 public List<CarClientDto> getSearchCarOnMaster(String search, Integer offset, Integer limit)
  throws SaveSearchErrorException {
  try {
   return carClientRepository.getAllByDescriptionLike("%" + search + "%",
     PageRequest.of(offset, limit))
    .stream()
    .map(clientMapperDto::toDto)
    .collect(Collectors.toList());
  } catch (Exception e) {
   throw new SaveSearchErrorException("Unknown error.{}", e.getMessage());
  }
 }

 @Override
 public UUID createCarOnClient(CarClientDto carClient, String login) throws SaveSearchErrorException {
  try {
   carClient.setId(UUID.randomUUID());
   if (carClientRepository.createCarOnLogin(carClient.getId(), carClient.getDescription(),
    carClient.getYear(), carClient.getMetadataCar(), carClient.getRun(), carClient.getSummary(),
    login, carClient.getMark().getId()) > 0)
    return carClient.getId();
  } catch (Exception e) {
   throw new SaveSearchErrorException("The entered data is in use by other users.{}", e.toString());
  }
  throw new SaveSearchErrorException("Invalid values entered.");
 }

 @Override
 public boolean updateCarClientByIdWithMachineNumber(CarClientDto carClient, String login)
  throws SaveSearchErrorException {
  try {
   if (carClientRepository.updateCarClientById(carClient.getMetadataCar(), carClient.getId(), login) == 1) {
    return true;
   } else throw new SaveSearchErrorException("Duplicate car number entered.", "MetadataCar");
  } catch (DataAccessException e) {
   throw new SaveSearchErrorException("The entered data is in use by other users.{}", e.getMessage());
  }
 }

 @Override
 public boolean updateCarClientByLogin(CarClientDto carClient, String login) throws SaveSearchErrorException {
  try {
   if (carClientRepository.updateCarClientByIdWithoutMachineNumber(carClient.getDescription(), carClient.getYear(),
    carClient.getRun(), carClient.getId(), login) == 1)
    return true;
   else {
    throw new SaveSearchErrorException("Invalid values entered.", "Save");
   }
  } catch (DataAccessException e) {
   throw new SaveSearchErrorException("The entered data is in use by other users. {}", e.getMessage());
  }
 }

 @Override
 public boolean idChek(UUID uuid) throws SaveSearchErrorException {
  if (carClientRepository.existsById(uuid))
   return true;
  throw new SaveSearchErrorException("Invalid id entered.", "id");
 }


}


