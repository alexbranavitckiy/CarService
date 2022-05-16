package com.netcracker.user;


import java.util.List;
import java.util.UUID;

import com.netcracker.car.CarClient;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AllArgsConstructor
@Entity(name = "clients")
public class Client extends User  {

 @ApiModelProperty(hidden = true)
 @OneToMany(mappedBy = "client",fetch = FetchType.LAZY)
 private List<CarClient> cars;

 public Client() {
  super();
 }

 @Builder
 public Client(UUID id, String name, String phone, String email, String description, @NotNull @Size(min = 4, max = 14) String login, @NotNull String password, RoleUser roleUser) {
  super(id, name, phone, email, description, login, password, roleUser);
 }

}