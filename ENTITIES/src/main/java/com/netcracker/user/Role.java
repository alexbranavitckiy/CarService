package com.netcracker.user;

import com.netcracker.EntityId;
import java.util.UUID;

public enum Role implements EntityId<UUID> {

  MASTER("MASTER"),
  RECEPTIONIST("RECEPTIONIST");

  private final String name;

  private UUID id;

  Role(String name) {
    this.name = name;
  }

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
