package com.netcracker.breakdown;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.netcracker.EntityId;
import com.netcracker.marka.CarClient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;
import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class CarBreakdown implements EntityId<UUID> {

 private UUID id;

 private CarClient carClient;

 private List<UUID> typeCarBreakdowns;

 private String descriptions;

 private Double runCarSize;

 private State state;

}
