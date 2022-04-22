package com.netcracker.DTO.clients;

import com.netcracker.user.Qualification;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MasterDto {

 private UUID id;

 private String name;

 private String phone;

 private String mail;

 private String description;

 private String homeAddress;

 private Qualification qualification;

 private String education;

}
