package com.netcracker.user;


import com.netcracker.EntityId;
import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Employer implements EntityId<UUID> {

  private UUID id;

  private String name;

  private String phone;

  private String mail;

  private String description;

  private Role role;

  private String login;

  private String password;

  private String homeAddress;

  private Qualification qualificationEnum;

  private String education;


}
