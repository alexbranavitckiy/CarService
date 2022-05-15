package com.netcracker.DTO.convectror;

import com.netcracker.DTO.user.MasterDto;
import com.netcracker.user.Master;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Lazy
@Slf4j
@Component
@Qualifier("MasterConvectorImpl")
public class MasterConvectorImpl implements MapperDto<MasterDto, Master> {

 private final ModelMapper mapper;

 @Autowired
 MasterConvectorImpl(ModelMapper modelMapper) {
  this.mapper = modelMapper;
 }

 @PostConstruct
 public void setupMapper() {
  mapper.createTypeMap(Master.class, MasterDto.class)
   .addMappings(m -> m.skip(MasterDto::setPassword));
 }

 @Override
 public Master toEntity(MasterDto dto) {
  return Objects.isNull(dto) ? null : mapper.map(dto, Master.class);
 }

 @Override
 public MasterDto toDto(Master entity) {
  return Objects.isNull(entity) ? null : mapper.map(entity, MasterDto.class);
 }

}
