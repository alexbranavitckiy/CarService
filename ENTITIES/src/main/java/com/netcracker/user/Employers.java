package com.netcracker.user;


import com.netcracker.PrincipalEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class Employers  implements PrincipalEntity {

 @Id
 @org.hibernate.annotations.Type(type="pg-uuid")
 private UUID id;

 @Column(name = "name",columnDefinition = "VARCHAR(50) not null")
 private String name;

 @Column(name = "phone",columnDefinition = "VARCHAR(50) not null unique")
 private String phone;

 @NotNull
 @Column(name = "mail",columnDefinition = "VARCHAR(50) not null unique")
 private String mail;

 private String description;

 @NotNull
 @Column(name = "role")
 private Role role;

 @NotNull
 @Size(min = 4, max = 20)
 @Column(name = "login",columnDefinition = "VARCHAR(50) not null unique")
 private String login;

 @NotNull
 @Column(name = "password",unique=true)
 private String password;

 @Column(name = "home_address")
 private String homeAddress;

 @Column(name = "qualification")
 private Qualification qualification;

 private String education;

}
