package com.netcracker.dto.convector;

import com.netcracker.dto.MapperDto;
import com.netcracker.dto.modelDTO.ClientDto;
import com.netcracker.user.Client;

public class ClientConvector implements MapperDto<ClientDto, Client> {

    @Override
    public Client toEntity(ClientDto dto) {
        //TODO!!
        return null;
    }

    @Override
    public ClientDto toDto(Client client) {
        return ClientDto.builder()//TODO!!
                .description(client.getDescription())
                .id(client.getId())
                .login(client.getLogin())
                .password(client.getPassword())
                .phone(client.getPhone())
                .orders(client.getOrders())
                .name(client.getName())
                .email(client.getEmail())
                .build();
    }
}
