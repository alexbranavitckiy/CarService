package com.netcracker.user;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employer {

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
