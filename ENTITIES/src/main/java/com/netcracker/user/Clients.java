package com.netcracker.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.UUID;

import com.netcracker.PrincipalEntity;
import com.netcracker.car.CarClient;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AllArgsConstructor
@Entity(name = "clients")
@ToString
public class Clients extends User  {

 @JsonIgnore
 @OneToMany(mappedBy = "client")
 private List<CarClient> carClients;

 public Clients() {
  super();
 }


 @Builder
 public Clients(UUID id, String name, String phone, String email, String description, @NotNull @Size(min = 4, max = 14) String login, @NotNull String password, RoleUser roleUser) {
  super(id, name, phone, email, description, login, password, roleUser);
 }



}