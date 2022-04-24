package com.netcracker.services.impl;

import com.netcracker.DTO.car.CarBreakdownDto;
import com.netcracker.DTO.convectror.MapperDto;
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
 public boolean addBreakdownOnMaster(CarBreakdownDto carBreakdownDto) {
  try {
   carBreakdownDto.setId(UUID.randomUUID());
   breakdownRepository.insertCarBreakDown(carBreakdownDto.getId().toString(),
    carBreakdownDto.getLocation(), carBreakdownDto.getDescription(),
    carBreakdownDto.getRunCarSize(), carBreakdownDto.getUpdateDate(),
    carBreakdownDto.getState().getCode(),
    carBreakdownDto.getIdCar().toString());
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
 public boolean updateBreakdownOnMaster(CarBreakdownDto carBreakdownForm) {
  try {
   breakdownRepository.updateCarBreakDownById(carBreakdownForm.getDescription(), carBreakdownForm.getRunCarSize(), carBreakdownForm.getUpdateDate(), carBreakdownForm.getState().getCode(), carBreakdownForm.getLocation(), carBreakdownForm.getId().toString());
   return true;
  } catch (Exception e) {
   log.warn(e.getMessage());
  }
  return false;
 }

 @Override
 public List<CarBreakdownDto> getAllBreakdownByCarSortDesc(String login) {
  return breakdownRepository.getAllByLogin(login).stream().map(mapperDto::toDto).collect(Collectors.toList());
 }

}
