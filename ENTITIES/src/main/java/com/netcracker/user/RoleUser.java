package com.netcracker.user;

import com.netcracker.EntityId;
import java.util.UUID;

public enum RoleUser implements EntityId<UUID> {
  REGISTERED("REGISTERED"),
  UNREGISTERED("UNREGISTERED");

  RoleUser(String name) {
    this.name = name;
  }

  private final String name;
  private UUID id;

  public String getName() {
    return name;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }
}
