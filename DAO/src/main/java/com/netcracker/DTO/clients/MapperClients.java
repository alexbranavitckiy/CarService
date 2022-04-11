package com.netcracker.DTO.clients;

import com.netcracker.DTO.MapperDto;
import com.netcracker.user.Clients;
import org.springframework.stereotype.Component;

@Component
public class MapperClients implements MapperDto<Clients, ClientsDto> {

 @Override
 public Clients toEntity(ClientsDto clientsDto) {
  return null;
 }

 @Override
 public ClientsDto toDto(Clients clients) {
  return ClientsDto.builder()
   .description(clients.getDescription())
   .email(clients.getEmail())
   .id(clients.getId())
   .name(clients.getName())
   .phone(clients.getPhone())
   .build();
 }

}
