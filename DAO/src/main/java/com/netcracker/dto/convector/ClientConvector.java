package com.netcracker.dto.convector;

import com.netcracker.dto.MapperDto;
import com.netcracker.dto.model.ClientDto;
import com.netcracker.user.Client;

public class ClientConvector implements MapperDto<ClientDto, Client> {

  @Override
  public Client toEntity(ClientDto dto) {
    return null;
  }

  @Override
  public ClientDto toDto(Client client) {
    return ClientDto.builder()
      //.carClients(client.getCarClients().stream().map(CarClient::getId).collect(Collectors.toList()))
      .description(client.getDescription())
      .id(client.getId())
      .login(client.getLogin())
      .password(client.getPassword())
      .roleuser(client.getRoleuser())
      .phone(client.getPhone())
      .orders(client.getOrders())
      .name(client.getName())
      .email(client.getEmail())
      .build();
  }
}
