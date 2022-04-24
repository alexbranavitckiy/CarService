package com.netcracker.services.impl;


import com.netcracker.DTO.convectror.MapperDto;
import com.netcracker.DTO.ord.OrdMapper;
import com.netcracker.DTO.ord.OrderDto;
import com.netcracker.DTO.ord.OutfitDto;
import com.netcracker.order.Order;
import com.netcracker.outfit.Outfit;
import com.netcracker.outfit.State;
import com.netcracker.repository.OutfitsRepository;
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
 private final OrdMapper ordMapper;
 private final MapperDto<OutfitDto, Outfit> outfitMapperDto;


 @Autowired
 private OutfitsServicesImpl(  MapperDto<OutfitDto, Outfit> outfitMapperDto,OrdMapper ordMapper, OutfitsRepository outfitsRepository) {
  this.ordMapper = ordMapper;
  this.outfitMapperDto=outfitMapperDto;
  this.outfitsRepository = outfitsRepository;
 }

 @Override
 public List<OutfitDto> getAllMasterOutfitWithStateAndSort(State state, String login) {
  return outfitsRepository.getAllByStateOutfitAndMasterLogin(state, login).stream().map(outfitMapperDto::toDto).collect(Collectors.toList());
 }

 @Override
 public boolean outfitStartWork(State state, String login, UUID uuid) {
  try {
   Optional<Outfit> outfit = outfitsRepository.findById(uuid);
   if (outfit.isPresent()) {
    outfit.get().setStateOutfit(state);
    outfitsRepository.save(outfit.get());
    return true;
   }
  } catch (Exception e) {
   log.warn(e.getMessage());
  }
  return false;
 }

 @Override
 public boolean updateOutfitByMaster(OutfitDto outfitDto, String login) {
  try {
   Optional<Outfit> outfit = outfitsRepository.findById(outfitDto.getId());
   if (outfit.isPresent()) {
    outfitsRepository.save(ordMapper.toEntity(outfitDto, outfit.get()));
    return true;
   }
  } catch (Exception e) {
   log.warn(e.getMessage());
  }
  return false;
 }


}
