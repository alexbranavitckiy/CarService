package com.netcracker.user;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.UUID;

import com.netcracker.PrincipalEntity;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

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
@ToString
public abstract class User  implements PrincipalEntity {

 @Id
 @org.hibernate.annotations.Type(type = "pg-uuid")
 private UUID id;

 @Column(name = "name",columnDefinition = "VARCHAR(50) NOT NULL ")
 private String name;

 @Column(name = "phone",columnDefinition = "VARCHAR(23) not null unique")
 private String phone;

 @Column(name = "email",columnDefinition = "VARCHAR(50) not null unique")
 private String email;

 @Column(name = "description",columnDefinition = "VARCHAR(100)")
 private String description;

 @NotNull
 @Size(min = 4, max = 20)
 @Column(name = "login",columnDefinition = "VARCHAR(50) not null unique")
 private String login;

 @NotNull
 @Column(name = "password",unique=true)
 private String password;

 @Column(name = "role_user")
 private RoleUser roleUser;

}
