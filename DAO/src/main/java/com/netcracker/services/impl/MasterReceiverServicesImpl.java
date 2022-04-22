package com.netcracker.services.impl;


import com.netcracker.DTO.clients.MasterDto;
import com.netcracker.DTO.convectror.MapperDto;
import com.netcracker.repository.MasterReceiverRepository;
import com.netcracker.services.MasterReceiverServices;
import com.netcracker.user.MasterReceiver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class MasterReceiverServicesImpl implements MasterReceiverServices {

 private final MasterReceiverRepository masterReceiverRepository;
 private final MapperDto<MasterDto, MasterReceiver> mapperDto;

 @Lazy
 @Autowired
 private MasterReceiverServicesImpl(@Qualifier("MasterReceiverImpl")MapperDto<MasterDto, MasterReceiver> mapperDto, MasterReceiverRepository masterReceiverRepository) {
  this.masterReceiverRepository = masterReceiverRepository;
  this.mapperDto=mapperDto;
 }

 @Override
 public Optional<MasterReceiver> getMasterReceiverByLogin(String login) {
  return masterReceiverRepository.getAllByLogin(login);
 }

 @Override
 public boolean addMaster(MasterReceiver masterReceiver) {
  try {
   masterReceiverRepository.save(masterReceiver);
   return true;
  } catch (Exception e) {
   log.warn(e.getMessage());
  }
  return false;
 }

 @Override
 public Optional<MasterDto> getMasterDtoByLogin(String login) {
  Optional<MasterReceiver> master = masterReceiverRepository.getAllByLogin(login);
  if (master.isEmpty()) {
   return Optional.empty();
  } else return Optional.of(mapperDto.toDto(master.get()));
 }

 @Override
 public Optional<MasterDto> getMasterReceiverById(UUID idMaster) {
  Optional<MasterReceiver> master = masterReceiverRepository.getAllById(idMaster);
  if (master.isEmpty()) {
   return Optional.empty();
  } else return Optional.of(mapperDto.toDto(master.get()));
 }

}
