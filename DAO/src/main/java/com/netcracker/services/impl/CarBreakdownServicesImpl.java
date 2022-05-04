package com.netcracker.services.impl;

import com.netcracker.DTO.car.CarBreakdownDto;
import com.netcracker.DTO.convectror.MapperDto;
import com.netcracker.DTO.errs.SaveSearchErrorException;
import com.netcracker.breakdown.CarBreakdown;
import com.netcracker.breakdown.State;
import com.netcracker.repository.CarBreakdownRepository;
import com.netcracker.services.CarBreakdownServices;
import com.netcracker.services.CarServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CarBreakdownServicesImpl implements CarBreakdownServices {

 private final CarBreakdownRepository breakdownRepository;
 private final CarServices carServices;
 private final MapperDto<CarBreakdownDto, CarBreakdown> mapperDto;

 @Lazy
 @Autowired
 private CarBreakdownServicesImpl(@Qualifier("CarBreakdownConvectorImpl") MapperDto<CarBreakdownDto, CarBreakdown> mapperDto, CarServices carServices, CarBreakdownRepository breakdownRepository) {
  this.breakdownRepository = breakdownRepository;
  this.mapperDto = mapperDto;
  this.carServices = carServices;
 }

 @Override
 public List<CarBreakdownDto> getAllBreakdownByCarIdLoginSort(UUID carUUID, String login) {
  return breakdownRepository.getAllByCarBreakdownByIdCarAndLoginSortDesc(login, carUUID).stream().map(mapperDto::toDto).collect(Collectors.toList());
 }

 @Override
 public List<CarBreakdownDto> getAllBreakdownByCarIdLogin(UUID carUUID, String login) {
  return breakdownRepository.getAllByCarBreakdownByIdCarAndLogin(login, carUUID).stream().map(mapperDto::toDto).collect(Collectors.toList());
 }

 @Override
 public List<CarBreakdownDto> getAllBreakdownByCarAndStateSortDesc(UUID carUUID, State state, String login) {
  return breakdownRepository.getAllByCarBreakdownByStateAndSortDesc(carUUID, login, state.getCode()).stream().map(mapperDto::toDto).collect(Collectors.toList());
 }

 @Override
 public boolean addBreakdownOnMaster(CarBreakdownDto carBreakdownDto, UUID orders, String login) {
  try {
   breakdownRepository.insertCarBreakDownOnMaster(UUID.randomUUID(), carBreakdownDto.getPrice(),
    carBreakdownDto.getLocation(), carBreakdownDto.getDescription(), carBreakdownDto.getRunCarSize(),
    new Date(), State.IMPORTANT.getCode(), orders);
   return true;
  } catch (Exception e) {
   log.warn(e.getMessage());
  }
  return false;
 }

 @Override
 public List<CarBreakdownDto> getAllBreakDownByCarIdOnMaster(UUID carId) {
  return breakdownRepository.getAllByCarClientId(carId).stream().map(mapperDto::toDto).collect(Collectors.toList());
 }

 @Override
 public boolean updateBreakdownOnMaster(CarBreakdownDto carBreakdownForm, String login) throws SaveSearchErrorException {
  try {
   if (breakdownRepository.updateCarBreakDownByIdAndMasterLogin(carBreakdownForm.getDescription(), carBreakdownForm.getRunCarSize(), new Date(), carBreakdownForm.getState().getCode(), carBreakdownForm.getLocation(), carBreakdownForm.getId(), login) == 1)
    return true;
  } catch (Exception e) {
   throw new SaveSearchErrorException("The search has not given any results." + e.getMessage());
  }
  throw new SaveSearchErrorException("Invalid data entered.", "err");
 }

 @Override
 public List<CarBreakdownDto> getAllBreakDownBOnMaster(String name) throws SaveSearchErrorException {
  try {
   return breakdownRepository.getAllByMaster(name).stream().map(mapperDto::toDto).collect(Collectors.toList());
  } catch (Exception e) {
   throw new SaveSearchErrorException("The search has not given any results." + e.getMessage());
  }
 }

 @Override
 public List<CarBreakdownDto> getAllBreakDownBOnMaster(String name, UUID id) throws SaveSearchErrorException {
  try {
   return breakdownRepository.getAllByIdOnMaster(name, id).stream().map(mapperDto::toDto).collect(Collectors.toList());
  } catch (Exception e) {
   throw new SaveSearchErrorException("The search has not given any results." + e.getMessage());
  }
 }

 @Override
 public List<CarBreakdownDto> getAllBreakDownOnCar(UUID id) throws SaveSearchErrorException {
  try {
   return breakdownRepository.getAllByCarClientId(id).stream().map(mapperDto::toDto).collect(Collectors.toList());
  } catch (Exception e) {
   throw new SaveSearchErrorException("The search has not given any results." + e.getMessage());
  }
 }

 @Override
 public List<CarBreakdownDto> getAllBreakdownByCarSortDesc(String login) {
  return breakdownRepository.getAllByLogin(login).stream().map(mapperDto::toDto).collect(Collectors.toList());
 }

}
