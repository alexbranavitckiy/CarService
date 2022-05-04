package com.netcracker.services.impl;


import com.netcracker.DTO.convectror.MapperDto;
import com.netcracker.DTO.errs.SaveSearchErrorException;
import com.netcracker.DTO.ord.OutfitDto;
import com.netcracker.DTO.time.TimeDto;
import com.netcracker.outfit.Outfit;
import com.netcracker.outfit.State;
import com.netcracker.repository.OutfitsRepository;
import com.netcracker.services.OutfitsServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OutfitsServicesImpl implements OutfitsServices {

 private final OutfitsRepository outfitsRepository;
 private final MapperDto<OutfitDto, Outfit> outfitMapperDto;
 private final MapperDto<TimeDto, Outfit> timeMapper;


 @Autowired
 private OutfitsServicesImpl(MapperDto<TimeDto, Outfit> timeMapper, MapperDto<OutfitDto, Outfit> outfitMapperDto, OutfitsRepository outfitsRepository) {
  this.outfitMapperDto = outfitMapperDto;
  this.timeMapper = timeMapper;
  this.outfitsRepository = outfitsRepository;
 }

 @Override
 public List<OutfitDto> getAllMasterOutfitWithStateAndSort(State state, String login) throws SaveSearchErrorException {
  try {
   List<OutfitDto> outfits = outfitsRepository.getAllByStateOutfitAndMasterLogin(login, state.getCode()).stream().map(outfitMapperDto::toDto).collect(Collectors.toList());
   if (outfits.size() > 0) return outfits;
  } catch (Exception e) {
   throw new SaveSearchErrorException("The entered data is in use by other users." + e.getMessage(), "err");
  }
  throw new SaveSearchErrorException("The search has not given any results.", "search");
 }

 @Override
 public boolean outfitStartWork(String login, UUID uuid) throws SaveSearchErrorException {
  try {
   if (outfitsRepository.startWorkMaster(State.WORK.getCode(), uuid, login) == 1) {
    return true;
   }
  } catch (Exception e) {
   throw new SaveSearchErrorException("Unknown error." + e.getMessage(), "err");
  }
  throw new SaveSearchErrorException("The outfit you entered does not belong to you.", "search");
 }

 @Override
 public boolean outfitEndWork(String name) throws SaveSearchErrorException {
  try {
   if (outfitsRepository.checkingOutfit(name) == 0) {
    if (outfitsRepository.endWorkMaster(State.END.getCode(), name) == 1) {
     return true;
    }
   } else throw new SaveSearchErrorException("There are non-closed outfits.", "Outfit");
  } catch (Exception e) {
   log.warn("{}", e.getMessage());
   throw new SaveSearchErrorException("Unknown error." + e.getMessage(), "err");
  }
  throw new SaveSearchErrorException("The outfit you entered does not belong to you.", "search");
 }


 @Override
 public UUID createOutfitOnMasterR(OutfitDto outfitDto, String login) throws SaveSearchErrorException {
  try {
   outfitDto.setStateOutfit(State.NO_STATE);
   outfitDto.setId(UUID.randomUUID());
   if (outfitsRepository.createOutfit(outfitDto.getId(), outfitDto.getDateEnt(), outfitDto.getDateStart(),
    outfitDto.getIdMaster(), outfitDto.getDescription(), outfitDto.getName(),
    outfitDto.getStateOutfit().getCode(), outfitDto.getId(), outfitDto.getOrder()) == 1)
    return outfitDto.getId();
   throw new SaveSearchErrorException("Active outfit master ane not found", "err");
  } catch (Exception e) {
   throw new SaveSearchErrorException("Unknown error." + e.getMessage(), "err");
  }
 }

 @Override
 public boolean updateOutfitByMaster(OutfitDto outfitDto, String login) throws SaveSearchErrorException {
  try {
   outfitDto.setStateOutfit(State.WORK);
   if (outfitsRepository.updateWorkMaster(outfitDto.getDateEnt(), outfitDto.getDateStart(), outfitDto.getDescription(), outfitDto.getName(), State.WORK.getCode(), login) == 1)
    return true;
   throw new SaveSearchErrorException("Active outfit master ane not found", "save");
  } catch (Exception e) {
   throw new SaveSearchErrorException("Unknown error." + e.getMessage(), "err");
  }
 }

 @Override
 public List<TimeDto> getAllOutfitByTime() throws SaveSearchErrorException {
  try {
   List<TimeDto> outfits = outfitsRepository.getAllByOrderByDateStartDesc().stream().map(timeMapper::toDto).collect(Collectors.toList());
   if (outfits.size() > 0) return outfits;
  } catch (Exception e) {
   throw new SaveSearchErrorException("The entered data is in use by other users." + e.getMessage(), "err");
  }
  throw new SaveSearchErrorException("The search has not given any results.", "search");
 }

}
