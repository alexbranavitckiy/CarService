package com.netcracker.services.impl;


import com.netcracker.DTO.convectror.MapperDto;
import com.netcracker.DTO.errs.SaveSearchErrorException;
import com.netcracker.DTO.ord.OutfitDto;
import com.netcracker.DTO.time.TimeDto;
import com.netcracker.outfit.Outfit;
import com.netcracker.outfit.State;
import com.netcracker.repository.OutfitsRepository;
import com.netcracker.services.OrderServices;
import com.netcracker.services.OutfitsServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
 private final OrderServices orderServices;

 @Autowired
 private OutfitsServicesImpl(OrderServices orderServices, MapperDto<TimeDto, Outfit> timeMapper,
                             MapperDto<OutfitDto, Outfit> outfitMapperDto, OutfitsRepository outfitsRepository) {
  this.outfitMapperDto = outfitMapperDto;
  this.orderServices = orderServices;
  this.timeMapper = timeMapper;
  this.outfitsRepository = outfitsRepository;
 }

 @Override
 public List<OutfitDto> getAllMasterOutfitWithStateAndSort(State state, String login) throws SaveSearchErrorException {
  try {
   List<OutfitDto> outfits = outfitsRepository.getAllByStateOutfitAndMasterLogin(login, state.getCode()).
    stream().map(outfitMapperDto::toDto).collect(Collectors.toList());
   if (outfits.size() > 0) return outfits;
  } catch (Exception e) {
   throw new SaveSearchErrorException("The entered data is in use by other users." + e.getMessage(), "err");
  }
  throw new SaveSearchErrorException("The search has not given any results.", "search");
 }

 @Override
 public UUID outfitStartWork(String login, UUID uuid) throws SaveSearchErrorException {
  try {
   Optional<Outfit> outfit = outfitsRepository.findById(uuid);
   if (outfit.isEmpty()) throw new SaveSearchErrorException("Invalid id entered.", "Id");
   if (outfit.get().getDateStart().before(new Date()) && outfit.get().getDateEnd().after(new Date())) {
    outfit.get().setStateOutfit(State.WORK);
    if (outfitsRepository.startWorkMaster(State.WORK.getCode(), uuid, login) == 1) {
     return UUID.randomUUID();
    }
   }
  } catch (Exception e) {
   log.warn("{}", e);
   throw new SaveSearchErrorException("Unknown error." + e.getMessage(), "err");
  }
  throw new SaveSearchErrorException("The outfit you entered does not belong to you.", "search");
 }

 @Override
 public boolean outfitEndWork(String name) throws SaveSearchErrorException {
  try {
   if (outfitsRepository.checkingOutfit(name) == 0 &&
    outfitsRepository.endWorkMaster(State.END.getCode(), new Date(), name) == 1)
    return true;
  } catch (Exception e) {
   log.error("{}", e.getMessage());
   throw new SaveSearchErrorException("Unknown error." + e.getMessage(), "err");
  }
  throw new SaveSearchErrorException("There are non-closed outfits.", "Outfit");
 }

 @Override
 public UUID updateOutfitByMaster(OutfitDto outfitDto, String login) throws SaveSearchErrorException {
  try {
   orderServices.checkTime(outfitDto.getDateStart(), outfitDto.getDateEnd(), outfitDto.getIdMaster());
   outfitDto.setStateOutfit(State.WORK);
   if (outfitsRepository.updateWorkMaster(outfitDto.getDateEnd(), outfitDto.getDateStart(), outfitDto.getDescription(),
    outfitDto.getName(), State.WORK.getCode(), login) == 1)
    return outfitDto.getId();
   throw new SaveSearchErrorException("Active outfit master ane not found", "save");
  } catch (Exception e) {
   throw new SaveSearchErrorException("Unknown error." + e.getMessage(), "err");
  }
 }

 @Override
 public List<TimeDto> getAllOutfitByTime() throws SaveSearchErrorException {
  try {
   return outfitsRepository.getAllByOutfitByDateStartDesc().stream().map(timeMapper::toDto).collect(Collectors.toList());
  } catch (Exception e) {
   log.error("{}", e.getMessage());
   throw new SaveSearchErrorException("The result did not return any results. ", "err");
  }
 }

 @Override
 public UUID updateOutfitByMasterR(OutfitDto outfitDto, String name, UUID idOutfit) throws SaveSearchErrorException {
  try {
   Optional<Outfit> outfit = outfitsRepository.findById(idOutfit);
   if (outfit.isEmpty()) throw new SaveSearchErrorException("Entered not valid id", "Id");
   if (outfitDto.getStateOutfit() != null) outfit.get().setStateOutfit(outfitDto.getStateOutfit());
   if (outfitDto.getName() != null) outfit.get().setName(outfitDto.getName());
   if (outfitDto.getDateEnd() != null) outfit.get().setDateEnd(outfitDto.getDateEnd());
   if (outfitDto.getDateStart() != null) outfit.get().setDateStart(outfitDto.getDateStart());
   if (outfitDto.getDescription() != null) outfit.get().setDescription(outfitDto.getDescription());
   outfitsRepository.save(outfit.get());
   return idOutfit;
  } catch (Exception e) {
   log.error("{}", e.getMessage());
   throw new SaveSearchErrorException("Unknown error." + e.getMessage(), "err");
  }
 }

 @Override
 public UUID updateOutfitMasterByMasterR(TimeDto idOutfit, String name) throws SaveSearchErrorException {
  try {
   orderServices.checkTime(idOutfit.getDateStart(), idOutfit.getDateEnd(), idOutfit.getMasterId());
   if (outfitsRepository.updateMasterOnMasterR(idOutfit.getMasterId(), idOutfit.getMasterId()) == 1) {
    return idOutfit.getId();
   } else throw new SaveSearchErrorException("Entered not valid Id.", "Id");
  } catch (Exception e) {
   log.error("{}", e.getMessage());
   throw new SaveSearchErrorException("Unknown error." + e.getMessage(), "err");
  }
 }

}
