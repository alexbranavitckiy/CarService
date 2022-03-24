package com.netcracker.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.netcracker.EntityId;
import java.util.List;
import lombok.*;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Data
@Getter
@Setter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Client extends User  implements EntityId<UUID> {

  private Set<UUID> orders;

  private List<UUID> carClients;

  public Client() {
    super();
  }

  @Builder
  public Client(UUID id, String name, String phone, String email, String description, String login,
      String password, UUID roleUser, Set<UUID> orders, List<UUID> carClients) {
    super(id,name, phone, email, description, login, password, roleUser);
    this.orders = orders;
    this.carClients = carClients;
  }

  @Override
  public String toString() {
    return super.toString();
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), getOrders());
  }


}