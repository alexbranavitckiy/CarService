package com.netcracker.DTO.convectror;

import com.netcracker.DTO.car.MarkDto;
import com.netcracker.car.Mark;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class MarkConvector implements MapperDto<MarkDto, Mark> {

 private final ModelMapper mapper;

 @Autowired
 MarkConvector(ModelMapper modelMapper) {
  this.mapper = modelMapper;
 }

 @Override
 public Mark toEntity(MarkDto dto) {
  return Objects.isNull(dto) ? null : mapper.map(dto, Mark.class);
 }

 @Override
 public MarkDto toDto(Mark entity) {
  return Objects.isNull(entity) ? null : mapper.map(entity, MarkDto.class);
 }

}
