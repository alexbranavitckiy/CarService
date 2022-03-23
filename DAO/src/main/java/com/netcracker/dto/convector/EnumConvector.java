package com.netcracker.dto.convector;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class EnumConvector {

  public UUID toDto(String entity) {
    return UUID.nameUUIDFromBytes(entity.getBytes(
      StandardCharsets.UTF_8));
  }
}
