package com.netcracker.DTO.clients;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientDto {

 private UUID id;

 private String name;

 private String phone;

 private String email;

 private String description;

 private String login;

}
