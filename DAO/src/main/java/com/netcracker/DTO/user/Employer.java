package com.netcracker.DTO.user;


import com.netcracker.user.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.MappedSuperclass;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class Employer {

 private UUID id;

 private String name;

 private String phone;

 private String mail;

 private String description;

 private Role role;

 private String login;

 private String password;

}
