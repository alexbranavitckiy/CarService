package com.netcracker.DTO.clients;

import com.netcracker.DTO.MapperDto;
import com.netcracker.user.Client;
import org.springframework.stereotype.Component;

@Component
public class MapperClients implements MapperDto<Client, ClientDto> {

 @Override
 public Client toEntity(ClientDto clientsDto) {
  return null;
 }//TODO!!

 @Override
 public ClientDto toDto(Client client) {
  return ClientDto.builder()
   .description(client.getDescription())
   .email(client.getEmail())
   .id(client.getId())
   .name(client.getName())
   .login(client.getLogin())
   .phone(client.getPhone())
   .build();
 }

}
