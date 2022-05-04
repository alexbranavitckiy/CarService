package com.netcracker.user;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.netcracker.order.Order;
import com.netcracker.outfit.Outfit;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(name = "master")

public class Master extends Employer {

 @OneToMany(mappedBy = "master")
 private List<Outfit> outfits;

 @ManyToMany(mappedBy = "masterReceiver")
 private List<Order> orders;

 @Builder
 public Master(UUID id, String name, String phone, @Email @NotNull String mail, String description, @NotNull Role role, @NotNull @Size(min = 4, max = 20) String login, @NotNull String password, String homeAddress, Qualification qualification, String education, List<Outfit> outfits) {
  super(id, name, phone, mail, description, role, login, password, homeAddress, qualification, education);
  this.outfits = outfits;
 }

}
