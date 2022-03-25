package com.netcracker.outfit;


import com.netcracker.EntityId;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public enum State implements EntityId<UUID> {
    WORK(0),
    END(1),
    NO_STATE(2);

    State(int id){

    }

    @Override
    public UUID getId() {
        return UUID.nameUUIDFromBytes(this.name().getBytes(
                StandardCharsets.UTF_8));
    }
}
