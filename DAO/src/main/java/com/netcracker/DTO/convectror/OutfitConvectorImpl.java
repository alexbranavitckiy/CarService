package com.netcracker.DTO.convectror;

import com.netcracker.DTO.ord.OrderDto;
import com.netcracker.DTO.ord.OutfitDto;
import com.netcracker.order.Order;
import com.netcracker.outfit.Outfit;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Objects;


@Slf4j
@Component
@Qualifier("OutfitConvectorImpl")
public class OutfitConvectorImpl implements MapperDto<OutfitDto, Outfit>{

 private final ModelMapper mapper;

 @Autowired
 OutfitConvectorImpl(ModelMapper modelMapper) {
  this.mapper = modelMapper;
 }

 @Override
 public Outfit toEntity(OutfitDto dto) {
  return Objects.isNull(dto) ? null : mapper.map(dto, Outfit.class);
 }

 @Override
 public OutfitDto toDto(Outfit entity) {
  return Objects.isNull(entity) ? null : mapper.map(entity, OutfitDto.class);
 }

}
