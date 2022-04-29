package com.netcracker.services.impl;


import com.netcracker.DTO.convectror.MapperDto;
import com.netcracker.DTO.errs.SaveSearchErrorException;
import com.netcracker.DTO.ord.OutfitDto;
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


 @Autowired
 private OutfitsServicesImpl(MapperDto<OutfitDto, Outfit> outfitMapperDto, OutfitsRepository outfitsRepository) {
  this.outfitMapperDto = outfitMapperDto;
  this.outfitsRepository = outfitsRepository;
 }

 @Override
 public List<OutfitDto> getAllMasterOutfitWithStateAndSort(State state, String login) throws SaveSearchErrorException {
  try {
   List<OutfitDto> outfits = outfitsRepository.getAllByStateOutfitAndMasterLogin(state.getCode(), login).stream().map(outfitMapperDto::toDto).collect(Collectors.toList());
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
 public boolean outfitEndWork(String name, UUID uuidOutfit) throws SaveSearchErrorException {
  try {
   if (outfitsRepository.startWorkMaster(State.END.getCode(), uuidOutfit, name) == 1) {
    return true;
   }
  } catch (Exception e) {
   throw new SaveSearchErrorException("Unknown error." + e.getMessage(), "err");
  }
  throw new SaveSearchErrorException("The outfit you entered does not belong to you.", "search");
 }

 @Override
 public boolean updateOutfitByMaster(OutfitDto outfitDto, String login) throws SaveSearchErrorException {
  try {
   if (outfitsRepository.updateWorkMaster(outfitDto.getDateEnt(), outfitDto.getDateStart(), outfitDto.getDescription(), outfitDto.getName(), State.WORK.getCode(), login) == 1)
    return true;
   throw new SaveSearchErrorException("Active outfit master ane not found", "save");
  } catch (Exception e) {
   throw new SaveSearchErrorException("Unknown error." + e.getMessage(), "err");
  }
 }


}
