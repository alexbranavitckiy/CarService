package com.netcracker.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

import com.netcracker.car.CarClient;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Data
@Getter
@Setter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(name = "clients")
public class Clients extends User  {

 @OneToMany(mappedBy = "client")
 @JsonIgnore
 private List<CarClient> carClients;

 public Clients() {
  super();
 }

}