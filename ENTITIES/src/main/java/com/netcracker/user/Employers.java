package com.netcracker.user;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class Employers {

 @Id
 @org.hibernate.annotations.Type(type="pg-uuid")
 private UUID id;

 private String name;

 private String phone;

 private String mail;

 private String description;

 @Column(name = "role")
 private Role role;

 @NotNull
 @Size(min = 4, max = 25)
 private String login;

 @NotNull
 @Size(min = 4, max = 14)
 private String password;

 @Column(name = "home_address")
 private String homeAddress;

 @Column(name = "qualification")
 private Qualification qualification;

 private String education;

}
