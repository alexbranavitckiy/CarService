package com.netcracker.services.impl;


import com.netcracker.DTO.clients.MasterDto;
import com.netcracker.DTO.convectror.MapperDto;
import com.netcracker.DTO.response.ContactConfirmationPayload;
import com.netcracker.repository.MasterRepository;
import com.netcracker.services.MasterServices;
import com.netcracker.user.Master;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MasterServicesImpl implements MasterServices {

 private final MasterRepository masterRepository;
private final MapperDto<MasterDto, Master> mapperDto;


 @Lazy
 @Autowired
 private MasterServicesImpl(@Qualifier("MasterConvectorImpl") MapperDto<MasterDto, Master> mapperDto, MasterRepository masterRepository) {
  this.mapperDto=mapperDto;
  this.masterRepository = masterRepository;
 }

 @Override
 public boolean updateMasterData(ContactConfirmationPayload contactConfirmationPayload, String login) {
  try {
   masterRepository.updatePassword(contactConfirmationPayload.getPassword(), contactConfirmationPayload.getUsername(), login);
   return true;
  } catch (Exception e) {
   log.warn(e.getMessage());
  }
  return false;
 }

 @Override
 public boolean updateMasterByLogin(MasterDto masterDto, String login) {
  try {
   masterRepository.updateMaster(masterDto.getName(),masterDto.getPhone(),masterDto.getMail(),masterDto.getDescription(),masterDto.getHomeAddress(),masterDto.getEducation(),login);
   return true;
  } catch (Exception e) {
   log.warn(e.getMessage());
  }
  return false;
 }

 @Override
 public boolean updateMaster(MasterDto masterDto, String login) {
  try {
   masterRepository.updateMaster(masterDto.getName(),masterDto.getPhone(),masterDto.getMail(),masterDto.getDescription(),masterDto.getHomeAddress(),masterDto.getEducation(),login);
   return true;
  } catch (Exception e) {
   log.warn(e.getMessage());
  }
  return false;
 }

 @Override
 public Optional<MasterDto> getMasterDtoByLogin(String login) {
  Optional<Master> master = masterRepository.getAllByLogin(login);
  if (master.isEmpty()) {
   return Optional.empty();
  } else return Optional.of(mapperDto.toDto(master.get()));
 }

 @Override
 public Optional<Master> getMasterByLogin(String login) {
  return masterRepository.getAllByLogin(login);
 }

 @Override
 public boolean addMaster(Master master) {
  try {
    masterRepository.save(master);
    return true;
  } catch (Exception e) {
   log.warn(e.getMessage());
  }
  return false;
 }

 @Override
 public List<MasterDto> getAllMasterDto() {
   return masterRepository.getAllBy().stream().map(mapperDto::toDto).collect(Collectors.toList());
 }




}
