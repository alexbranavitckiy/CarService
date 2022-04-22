package com.netcracker.services.impl;

import com.netcracker.DTO.CarBreakdownMapper;
import com.netcracker.DTO.car.CarBreakdownDto;
import com.netcracker.DTO.car.CarBreakdownForm;
import com.netcracker.DTO.convectror.MapperDto;
import com.netcracker.breakdown.CarBreakdown;
import com.netcracker.breakdown.State;
import com.netcracker.car.CarClient;
import com.netcracker.repository.CarBreakdownRepository;
import com.netcracker.services.CarBreakdownServices;
import com.netcracker.services.CarServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CarBreakdownServicesImpl implements CarBreakdownServices {

 private final CarBreakdownRepository breakdownRepository;
 private final CarBreakdownMapper carBreakdownMapper;
 private final CarServices carServices;
 private final MapperDto<CarBreakdownDto, CarBreakdown> mapperDto;

 @Lazy
 @Autowired
 private CarBreakdownServicesImpl(@Qualifier("CarBreakdownConvectorImpl") MapperDto<CarBreakdownDto, CarBreakdown> mapperDto, CarServices carServices, CarBreakdownMapper carBreakdownMapper, CarBreakdownRepository breakdownRepository) {
  this.breakdownRepository = breakdownRepository;
  this.mapperDto=mapperDto;
  this.carServices = carServices;
  this.carBreakdownMapper = carBreakdownMapper;
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
 public boolean addBreakdownOnMaster(CarBreakdownForm carBreakdownForm) {
  try {
   Optional<CarClient> car = carServices.getCarByIdOnMaster(carBreakdownForm.getIdCar());
   if (car.isPresent()) {
    breakdownRepository.save(carBreakdownMapper.toEntityWithNewCar(carBreakdownForm, car.get()));
    return true;
   }
  } catch (Exception e) {
   log.warn(e.getMessage());
   return false;
  }
  return false;
 }

 @Override
 public List<CarBreakdownDto> getAllBreakDownByCarIdOnMaster(UUID carId) {
  return breakdownRepository.getAllByCarClientId(carId).stream().map(mapperDto::toDto).collect(Collectors.toList());
 }

 @Override
 public boolean updateBreakdownOnMaster(CarBreakdownForm carBreakdownForm) {
  try {
   Optional<CarBreakdown> carBreakdown = breakdownRepository.getAllById(carBreakdownForm.getId());
   if (carBreakdown.isPresent()) {
    breakdownRepository.save(carBreakdownMapper.toEntity(carBreakdownForm, carBreakdown.get()));
    return true;
   }
  } catch (Exception e) {
   log.warn(e.getMessage());
   return false;
  }
  return false;
 }

 @Override
 public List<CarBreakdownDto> getAllBreakdownByCarSortDesc(String login) {
  return breakdownRepository.getAllByLogin(login).stream().map(mapperDto::toDto).collect(Collectors.toList());
 }

}
