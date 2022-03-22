package com.netcracker.marka;

import com.netcracker.EntityId;
import com.netcracker.breakdown.CarBreakdown;
import lombok.*;


import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"id"})
public class CarClient implements EntityId<UUID> {

  private UUID id;

  private String summer;

  private String ear;

  private String metadataCar;

  private String run;

  private List<UUID> carBreakdowns;

  private Mark marka;

  @Override
  public String toString() {
    return "Your cars: summer='" + summer + '\'' +
        ", ear='" + ear + '\'' +
        ", metadataCar='" + metadataCar + '\'' +
        ", run='" + run + '\'' +
        '}';
  }

}
