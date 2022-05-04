package com.netcracker.user;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.UUID;

import com.netcracker.PrincipalEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@MappedSuperclass
@ToString
public class User implements PrincipalEntity {

 @Id
 @Schema(accessMode = Schema.AccessMode.READ_ONLY)
 @org.hibernate.annotations.Type(type = "pg-uuid")
 private UUID id;

 @Column(name = "name", columnDefinition = "VARCHAR NOT NULL")
 private String name;

 @Column(name = "phone", columnDefinition = "VARCHAR not null")
 private String phone;

 @Email
 @Column(name = "email", columnDefinition = "VARCHAR")
 private String email;

 @Column(name = "description", columnDefinition = "VARCHAR")
 private String description;

 @Column(name = "login", columnDefinition = "VARCHAR not null unique")
 private String login;

 @Column(name = "password", unique = true)
 private String password;

 @NotNull
 @Schema(accessMode = Schema.AccessMode.READ_ONLY)
 @Column(name = "role")
 private RoleUser roleUser;

}
