package com.netcracker.dto.modelDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.netcracker.EntityId;
import com.netcracker.marka.CarClient;
import com.netcracker.user.RoleUser;

import com.netcracker.user.User;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientDto extends User implements EntityId<UUID> {

  private Set<UUID> orders;

  private List<CarClient> carClients;

  public ClientDto() {
    super();
  }

  @Builder
  public ClientDto(UUID id, String name, String phone, String email, String description, String login,
    String password, UUID roleUser, Set<UUID> orders, List<CarClient> carClients) {
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