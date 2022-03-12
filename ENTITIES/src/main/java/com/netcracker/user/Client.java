package com.netcracker.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.netcracker.marka.CarClient;
import com.netcracker.order.Order;
import java.util.ArrayList;
import java.util.List;
import lombok.*;


import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Data
@Getter
@Setter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Client extends User implements Serializable {

  private Set<UUID> orders;

  private List<CarClient> carClients;

  public Client() {
    super();
  }

  @Builder
  public Client(UUID id, String name, String phone, String email, String description, String login,
      String password, RoleUser roleuser, Set<UUID> orders, List<CarClient> carClients) {
    super(id, name, phone, email, description, login, password, roleuser);
    this.orders = orders;
    this.carClients = carClients;
  }

  @Override
  public String toString() {
    return super.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    Client client = (Client) o;
    return Objects.equals(orders, client.orders) && Objects.equals(carClients, client.carClients);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode());
  }


}