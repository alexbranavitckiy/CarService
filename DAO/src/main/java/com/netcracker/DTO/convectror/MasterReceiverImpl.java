package com.netcracker.DTO.convectror;

import com.netcracker.DTO.clients.MasterDto;
import com.netcracker.user.Master;
import com.netcracker.user.MasterReceiver;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
@Qualifier("MasterReceiverImpl")
public class MasterReceiverImpl implements MapperDto<MasterDto, MasterReceiver>{

 private final ModelMapper mapper;

 @Autowired
 MasterReceiverImpl(ModelMapper modelMapper) {
  this.mapper = modelMapper;
 }

 @Override
 public MasterReceiver toEntity(MasterDto dto) {
  return Objects.isNull(dto) ? null : mapper.map(dto, MasterReceiver.class);
 }

 @Override
 public MasterDto toDto(MasterReceiver entity) {
  return Objects.isNull(entity) ? null : mapper.map(entity, MasterDto.class);
 }

}
