package com.netcracker.user;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.UUID;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@MappedSuperclass
public abstract class User {

 @Id
 @org.hibernate.annotations.Type(type = "pg-uuid")
 private UUID id;

 private String name;

 private String phone;

 private String email;

 private String description;

 @NotNull
 @Size(min = 4, max = 14)
 private String login;

 @NotNull
 @Size(min = 4, max = 14)
 private String password;

 @Column(name = "role_user")
 private RoleUser roleUser;

}
