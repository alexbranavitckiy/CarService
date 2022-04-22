package com.netcracker.DTO.convectror;

import com.netcracker.DTO.car.CarClientDto;
import com.netcracker.DTO.clients.ClientDto;
import com.netcracker.car.CarClient;
import com.netcracker.user.Client;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Objects;


@Component
@Qualifier("CarConvectorImpl")
public class CarConvectorImpl implements MapperDto<CarClientDto, CarClient> {

 private final ModelMapper mapper;

 @Autowired
 CarConvectorImpl(ModelMapper modelMapper) {
  this.mapper = modelMapper;
 }

 @Override
 public CarClient toEntity(CarClientDto dto) {
  return Objects.isNull(dto) ? null : mapper.map(dto, CarClient.class);
 }


 @Override
 public CarClientDto toDto(CarClient entity) {
  return Objects.isNull(entity) ? null : mapper.map(entity, CarClientDto.class);
 }

}
