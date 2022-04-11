package com.netcracker.DTO.clients;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientsDto {

 private UUID id;

 private String name;

 private String phone;

 private String email;

 private String description;

}
