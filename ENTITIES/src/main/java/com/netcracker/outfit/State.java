package com.netcracker.outfit;


import com.netcracker.EntityId;
import java.util.UUID;

public enum State implements EntityId<UUID> {
  WORK("WORK"),
  END("END"),
  NO_STATE("NO_STATE");

  private UUID id;

  private final String name;

  State(String name) {
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
