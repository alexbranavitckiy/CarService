package com.netcracker.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.netcracker.order.Order;
import lombok.*;


import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MasterReceiver extends Employer {


  private List<UUID> orders;

  @Builder
  public MasterReceiver(UUID id, String name, String phone, String mail, String description,
      Role role, String login, String password, String homeAddress, Qualification qualificationEnum,
      String education, List<UUID> orders) {
    super(id, name, phone, mail, description, role, login, password, homeAddress, qualificationEnum,
        education);
    this.orders = orders;
  }

  @Override
  public String toString() {
    return "MasterReceiver{" +
        "orders=" + orders +
        "} " + super.toString();
  }


}

