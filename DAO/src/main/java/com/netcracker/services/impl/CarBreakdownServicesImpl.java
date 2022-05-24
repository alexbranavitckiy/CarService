package com.netcracker.services.impl;

import com.netcracker.DTO.car.CarBreakdownDto;
import com.netcracker.DTO.convectror.MapperDto;
import com.netcracker.DTO.errs.SaveSearchErrorException;
import com.netcracker.breakdown.CarBreakdown;
import com.netcracker.breakdown.State;
import com.netcracker.repository.CarBreakdownRepository;
import com.netcracker.services.CarBreakdownServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CarBreakdownServicesImpl implements CarBreakdownServices {

 private final CarBreakdownRepository breakdownRepository;
 private final MapperDto<CarBreakdownDto, CarBreakdown> mapperDto;

 @Lazy
 @Autowired
 private CarBreakdownServicesImpl(@Qualifier("CarBreakdownConvectorImpl") MapperDto<CarBreakdownDto,
  CarBreakdown> mapperDto, CarBreakdownRepository breakdownRepository) {
  this.breakdownRepository = breakdownRepository;
  this.mapperDto = mapperDto;
 }

 @Override
 public List<CarBreakdownDto> getAllBreakdownByCarIdLoginSort(UUID carUUID, String login) throws SaveSearchErrorException {
  try {
   return breakdownRepository.getAllByCarBreakdownByIdCarAndLoginSortDesc(login, carUUID).stream()
    .map(mapperDto::toDto).collect(Collectors.toList());
  } catch (Exception e) {
   log.error("Message:{}. Error:{}", e.getMessage(), e);
   throw new SaveSearchErrorException("The search has not given any results." + e.getMessage());
  }
 }

 @Override
 public List<CarBreakdownDto> getAllBreakdownByCarIdLogin(UUID carUUID, String login) throws SaveSearchErrorException {
  try {
   return breakdownRepository.getAllByCarBreakdownByIdCarAndLogin(login, carUUID).stream()
    .map(mapperDto::toDto).collect(Collectors.toList());
  } catch (Exception e) {
   log.error("Message:{}. Error:{}", e.getMessage(), e);
   throw new SaveSearchErrorException("The search has not given any results." + e.getMessage());
  }
 }

 @Override
 public List<CarBreakdownDto> getAllBreakdownByCarAndStateSortDesc(UUID carUUID, State state, String login)
  throws SaveSearchErrorException {
  try {
   return breakdownRepository.getAllByCarBreakdownByStateAndSortDesc(carUUID, login, state.getCode()).stream()
    .map(mapperDto::toDto).collect(Collectors.toList());
  } catch (Exception e) {
   log.error("Message:{}. Error:{}", e.getMessage(), e);
   throw new SaveSearchErrorException("The search has not given any results." + e.getMessage());
  }
 }


 @Override
 public UUID addBreakdownOnMaster(CarBreakdownDto carBreakdownDto, UUID orders, String login)
  throws SaveSearchErrorException {
  try {
   carBreakdownDto.setIdCar(UUID.randomUUID());
   breakdownRepository.insertCarBreakDownOnMaster(carBreakdownDto.getId(), carBreakdownDto.getPrice(),
    carBreakdownDto.getLocation(), carBreakdownDto.getDescription(), carBreakdownDto.getRunCarSize(),
    new Date(), State.IMPORTANT.getCode(), orders);
   return carBreakdownDto.getId();
  } catch (Exception e) {
   log.error("Message:{}. Error:{}", e.getMessage(), e);
   throw new SaveSearchErrorException("Invalid data entered.", "err");
  }
 }

 @Override
 public boolean updateBreakdownOnMaster(CarBreakdownDto carBreakdownForm, String login)
  throws SaveSearchErrorException {
  try {
   if (breakdownRepository.updateCarBreakDownByIdAndMasterLogin(carBreakdownForm.getDescription(),
    carBreakdownForm.getRunCarSize(), new Date(), carBreakdownForm.getState().getCode(),
    carBreakdownForm.getLocation(), carBreakdownForm.getId(), login) == 1)
    return true;
  } catch (Exception e) {
   log.error("Message:{}. Error:{}", e.getMessage(), e);
   throw new SaveSearchErrorException("The search has not given any results." + e.getMessage());
  }
  throw new SaveSearchErrorException("Invalid data entered.", "err");
 }

 @Override
 public UUID updateBreakdownOnMasterR(CarBreakdownDto carBreakdownForm, String login)
  throws SaveSearchErrorException {
  try {
   if (breakdownRepository.updateCarBreakDownByIdAndMasterOnR(carBreakdownForm.getPrice(),
    carBreakdownForm.getDescription(), carBreakdownForm.getRunCarSize(), new Date(),
    carBreakdownForm.getState().getCode(), carBreakdownForm.getLocation(), carBreakdownForm.getId(), login) == 1)
    return carBreakdownForm.getId();
  } catch (Exception e) {
   log.error("Message:{}. Error:{}", e.getMessage(), e);
   throw new SaveSearchErrorException("The search has not given any results." + e.getMessage());
  }
  throw new SaveSearchErrorException("Invalid data entered.", "err");
 }

 @Override
 public List<CarBreakdownDto> getAllBreakDownBOnMasterR(String name, int offset, int limit) throws SaveSearchErrorException {
  try {
   return breakdownRepository.getAllByOrderByUpdateDateDesc(PageRequest.of(offset, limit)).stream().map(mapperDto::toDto).
    collect(Collectors.toList());
  } catch (Exception e) {
   log.error("Message:{}. Error:{}", e.getMessage(), e);
   throw new SaveSearchErrorException("The search has not given any results." + e.getMessage());
  }
 }

 @Override
 public UUID updatePartBreakdownOnMasterR(CarBreakdownDto carBreakdownForm, String name) throws SaveSearchErrorException {
  try {
   Optional<CarBreakdown> carBreakdown = breakdownRepository.findById(carBreakdownForm.getId());
   if (carBreakdown.isEmpty()) throw new SaveSearchErrorException("Invalid id entered.", "Id");
   if (carBreakdownForm.getDescription() != null) carBreakdown.get().setDescription(carBreakdownForm.getDescription());
   if (carBreakdownForm.getLocation() != null) carBreakdown.get().setLocation(carBreakdownForm.getLocation());
   if (carBreakdownForm.getUpdateDate() != null) carBreakdown.get().setUpdateDate(new Date());
   if (carBreakdownForm.getRunCarSize() > carBreakdown.get().getRunCarSize()) carBreakdown.get()
    .setRunCarSize(carBreakdownForm.getRunCarSize());
   if (carBreakdownForm.getPrice() > 0) carBreakdown.get().setPrice(carBreakdownForm.getPrice());
   if (carBreakdownForm.getState() != null) carBreakdown.get().setState(carBreakdownForm.getState());
   breakdownRepository.save(carBreakdown.get());
   return carBreakdown.get().getId();
  } catch (Exception e) {
   log.error("Message:{}. Error:{}", e.getMessage(), e);
   throw new SaveSearchErrorException("Save data error.", "Save");
  }
 }

 @Override
 public List<CarBreakdownDto> getAllBreakDownBOnMaster(String name) throws SaveSearchErrorException {
  try {
   return breakdownRepository.getAllByMaster(name)
    .stream().map(mapperDto::toDto).collect(Collectors.toList());
  } catch (Exception e) {
   log.error("Message:{}. Error:{}", e.getMessage(), e);
   throw new SaveSearchErrorException("The search has not given any results." + e.getMessage());
  }
 }

 @Override
 public List<CarBreakdownDto> getAllBreakDownBOnMaster(String name, UUID id) throws SaveSearchErrorException {
  try {
   return breakdownRepository.getAllByIdOnMaster(name, id).stream().map(mapperDto::toDto).collect(Collectors.toList());
  } catch (Exception e) {
   log.error("Message:{}. Error:{}", e.getMessage(), e);
   throw new SaveSearchErrorException("The search has not given any results." + e.getMessage());
  }
 }

 @Override
 public List<CarBreakdownDto> getAllBreakDownOnCar(UUID id) throws SaveSearchErrorException {
  try {
   return breakdownRepository.getAllByCarClientId(id).stream().map(mapperDto::toDto).collect(Collectors.toList());
  } catch (Exception e) {
   log.error("Message:{}. Error:{}", e.getMessage(), e);
   throw new SaveSearchErrorException("The search has not given any results." + e.getMessage());
  }
 }

 @Override
 public List<CarBreakdownDto> getAllBreakdownByCarSortDesc(int offset, int limit, String login)
  throws SaveSearchErrorException {
  try {
   return breakdownRepository.getAllByLoginOnClients(login, PageRequest.of(offset, limit))
    .stream().map(mapperDto::toDto).collect(Collectors.toList());
  } catch (Exception e) {
   log.error("Message:{}. Error:{}", e.getMessage(), e);
   throw new SaveSearchErrorException("The search has not given any results." + e.getMessage());
  }
 }


}
