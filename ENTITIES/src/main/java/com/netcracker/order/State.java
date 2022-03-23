package com.netcracker.order;

import com.netcracker.EntityId;
import java.util.UUID;

public enum State implements EntityId<UUID> {
  TEMPLATE("TEMPLATE"),
  RECORDED("RECORDED"),
  IN_WORK("IN_WORK"),
  CAR_GIVEN("CAR_GIVEN"),
  CAR_ACCEPTED("CAR_ACCEPTED"),
  WAIT_CLIENT("WAIT_CLIENT"),
  REQUEST("REQUEST"),
  BID("BID");

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
