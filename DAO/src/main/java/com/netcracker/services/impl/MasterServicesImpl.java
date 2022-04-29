package com.netcracker.services.impl;


import com.netcracker.DTO.clients.MasterDto;
import com.netcracker.DTO.convectror.MapperDto;
import com.netcracker.DTO.errs.SaveSearchErrorException;
import com.netcracker.DTO.response.ContactConfirmationPayload;
import com.netcracker.repository.MasterRepository;
import com.netcracker.services.MasterServices;
import com.netcracker.user.Master;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataIntegrityViolationException;
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
  this.mapperDto = mapperDto;
  this.masterRepository = masterRepository;
 }

 //--Master--//
 @Override
 public boolean updateMasterPass(String clientFormUpdate, String login) throws SaveSearchErrorException {
  try {
   masterRepository.updatePassword(clientFormUpdate, login);
   return true;
  } catch (DataIntegrityViolationException e) {
   throw new SaveSearchErrorException("Invalid password entered");
  } catch (Exception e) {
   throw new SaveSearchErrorException("Unknown error:" + e.getMessage());
  }
 }

 @Override
 public boolean updateMasterLogin(String newLogin, String oldLogin) throws SaveSearchErrorException {
  try {
   masterRepository.updateLogin(newLogin, oldLogin);
   return true;
  } catch (DataIntegrityViolationException e) {
   throw new SaveSearchErrorException("Invalid login entered", "login");
  } catch (Exception e) {
   throw new SaveSearchErrorException("Unknown error:" + e.getMessage());
  }
 }

 @Override
 public boolean updateMasterEmail(String email, String oldLogin) throws SaveSearchErrorException {
  try {
   masterRepository.updateEmail(email, oldLogin);
   return true;
  } catch (DataIntegrityViolationException e) {
   throw new SaveSearchErrorException("The email you entered is being used by another user");
  } catch (Exception e) {
   throw new SaveSearchErrorException("Unknown error:" + e.getMessage());
  }
 }

 @Override
 public boolean updateMasterPhone(String newLogin, String oldLogin) throws SaveSearchErrorException {
  try {
   masterRepository.updatePhone(newLogin, oldLogin);
   return true;
  } catch (DataIntegrityViolationException e) {
   throw new SaveSearchErrorException("The phone you entered is being used by another user");
  } catch (Exception e) {
   throw new SaveSearchErrorException("Unknown error:" + e.getMessage());
  }
 }

 @Override
 public Optional<MasterDto> getMasterDtoByLogin(String login) {
  Optional<Master> master = masterRepository.getAllByLogin(login);
  if (master.isEmpty()) {
   return Optional.empty();
  } else return Optional.of(mapperDto.toDto(master.get()));
 }

 @Override
 public boolean passwordChek(String password) throws SaveSearchErrorException {
  if (!masterRepository.existsByPassword(password)) {
   return false;
  }
  throw new SaveSearchErrorException("Invalid password entered.", "password");
 }

 @Override
 public boolean loginChek(String login) throws SaveSearchErrorException {
  if (!masterRepository.existsByLogin(login)) {
   return false;
  }
  throw new SaveSearchErrorException("The login you entered is in use by another user.", "login");
 }

 @Override
 public boolean emailChek(String email) throws SaveSearchErrorException {
  if (!masterRepository.existsByMail(email)) {
   return false;
  }
  throw new SaveSearchErrorException("The entered mail is busy by another user.", "email");
 }

 @Override
 public boolean phoneChek(String phone) throws SaveSearchErrorException {
  if (!masterRepository.existsByPhone(phone)) {
   return false;
  }
  throw new SaveSearchErrorException("The entered phone number is already registered.", "phone");
 }

 @Override
 public boolean updateMasterByLogin(MasterDto masterDto, String login) throws SaveSearchErrorException {
  try {
   if (masterRepository.updateDate(masterDto.getName(), masterDto.getDescription(), masterDto.getHomeAddress(), masterDto.getEducation(), login)==1)
   return true;
   throw new SaveSearchErrorException("Data update was not successful","Save");
  } catch (DataIntegrityViolationException e) {
   throw new SaveSearchErrorException("Data update was not successful");
  } catch (Exception e) {
   throw new SaveSearchErrorException("Unknown error:" + e.getMessage());
  }
 }

 //--Master--//


 @Override
 public boolean updateMasterData(ContactConfirmationPayload contactConfirmationPayload, String login) {
  try {
   masterRepository.updatePassword(contactConfirmationPayload.getPassword(), contactConfirmationPayload.getLogin(), login);
   return true;
  } catch (Exception e) {
   log.warn(e.getMessage());
  }
  return false;
 }


 @Override
 public boolean updateMaster(MasterDto masterDto, String login) {
  try {
   masterRepository.updateMaster(masterDto.getName(), masterDto.getPhone(), masterDto.getMail(), masterDto.getDescription(), masterDto.getHomeAddress(), masterDto.getEducation(), login);
   return true;
  } catch (Exception e) {
   log.warn(e.getMessage());
  }
  return false;
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
