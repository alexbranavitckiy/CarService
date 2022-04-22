package com.netcracker.DTO.convectror;

import com.netcracker.DTO.car.CarBreakdownDto;
import com.netcracker.breakdown.CarBreakdown;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Objects;


@Component
@Qualifier("CarBreakdownConvectorImpl")
public class CarBreakdownConvectorImpl implements MapperDto<CarBreakdownDto, CarBreakdown> {

 private final ModelMapper mapper;

 @Autowired
 CarBreakdownConvectorImpl(ModelMapper modelMapper) {
  this.mapper = modelMapper;
 }

 @Override
 public CarBreakdown toEntity(CarBreakdownDto dto) {
  return Objects.isNull(dto) ? null : mapper.map(dto, CarBreakdown.class);
 }

 @Override
 public CarBreakdownDto toDto(CarBreakdown entity) {
  return Objects.isNull(entity) ? null : mapper.map(entity, CarBreakdownDto.class);
 }

}