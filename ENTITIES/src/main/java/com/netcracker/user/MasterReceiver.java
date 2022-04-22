package com.netcracker.user;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.netcracker.order.Order;
import lombok.*;
import springfox.documentation.annotations.ApiIgnore;


import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity(name = "master_receiver")
public class MasterReceiver extends Employer {

 @ManyToMany(mappedBy = "masterReceiver")
 private List<Order> orders;

 @Builder
 public MasterReceiver(UUID id, String name, String phone, @Email @NotNull String mail, String description, @NotNull Role role, @NotNull @Size(min = 4, max = 20) String login, @NotNull String password, String homeAddress, Qualification qualification, String education, List<Order> orders) {
  super(id, name, phone, mail, description, role, login, password, homeAddress, qualification, education);
  this.orders = orders;
 }
}

