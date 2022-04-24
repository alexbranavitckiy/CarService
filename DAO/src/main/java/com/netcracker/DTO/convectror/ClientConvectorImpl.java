package com.netcracker.DTO.convectror;

import com.netcracker.DTO.clients.ClientDto;
import com.netcracker.user.Client;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;


@Component
@Qualifier("ClientConvectorImpl")
public class ClientConvectorImpl implements MapperDto<ClientDto, Client> {

 private final ModelMapper mapper;

 @Autowired
 ClientConvectorImpl(ModelMapper modelMapper) {
  this.mapper = modelMapper;
 }

 @PostConstruct
 public void setupMapper() {
  mapper.createTypeMap(Client.class, ClientDto.class)
   .addMappings(m -> m.skip(ClientDto::setPassword));
 }

 @Override
 public Client toEntity(ClientDto dto) {
  return Objects.isNull(dto) ? null : mapper.map(dto, Client.class);
 }

 @Override
 public ClientDto toDto(Client entity) {
  return Objects.isNull(entity) ? null : mapper.map(entity, ClientDto.class);
 }

}
