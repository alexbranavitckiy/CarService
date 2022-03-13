package com.netcracker.user;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.UUID;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class User  {

  private UUID id;

  private String name;

  private String phone;

  private String email;

  private String description;

  private String login;

  private String password;

  private RoleUser roleuser;

}
