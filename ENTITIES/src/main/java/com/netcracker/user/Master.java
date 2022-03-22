package com.netcracker.user;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.netcracker.EntityId;
import com.netcracker.outfit.Outfit;
import lombok.*;

import java.util.List;
import java.util.UUID;


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Master extends Employer implements EntityId<UUID> {

  private List<UUID> outfits;

  @Builder
  public Master(UUID id, String name, String phone, String mail, String description, Role role,
      String login, String password, String homeAddress, Qualification qualificationEnum,
      String education, List<UUID> outfits) {
    super(id, name, phone, mail, description, role, login, password, homeAddress, qualificationEnum,
        education);
    this.outfits = outfits;
  }

  @Override
  public String toString() {
    return "Master{" +
        "outfits=" + outfits +
        "} " + super.toString();
  }
}
