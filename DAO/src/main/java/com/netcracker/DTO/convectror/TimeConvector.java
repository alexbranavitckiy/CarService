package com.netcracker.DTO.convectror;

import com.netcracker.DTO.ord.OutfitDto;
import com.netcracker.DTO.time.TimeDto;
import com.netcracker.DTO.user.MasterDto;
import com.netcracker.outfit.Outfit;
import com.netcracker.user.Master;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Objects;


@Slf4j
@Component
@Qualifier("OutfitConvectorImpl")
public class TimeConvector implements MapperDto<TimeDto, Outfit> {

 private final ModelMapper mapper;

 @Autowired
 TimeConvector(ModelMapper modelMapper) {
  this.mapper = modelMapper;
 }

 @Override
 public Outfit toEntity(TimeDto dto) {
  return Objects.isNull(dto) ? null : mapper.map(dto, Outfit.class);
 }

 @Override
 public TimeDto toDto(Outfit entity) {
  TimeDto timeDto = Objects.isNull(entity) ? null : mapper.map(entity, TimeDto.class);
//  if (entity!=null&&entity.getMaster().getName()!=null&& entity.getMaster().getId()!=null&&entity.getOrder().getId()!=null){
//  timeDto.setMasterName(entity.getMaster().getName());
//  timeDto.setMasterId(entity.getMaster().getId());
//  timeDto.setOrderId(entity.getOrder().getId());
//  }
  return timeDto;
 }

}
