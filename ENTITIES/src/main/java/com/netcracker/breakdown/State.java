package com.netcracker.breakdown;


import com.netcracker.EntityId;
import java.util.UUID;

public enum State implements EntityId<UUID> {
  CORRECTED("CORRECTED"),
  NOT_FIXED("NOT_FIXED");

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
