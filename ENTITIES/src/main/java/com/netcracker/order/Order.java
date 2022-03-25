package com.netcracker.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.netcracker.EntityId;
import lombok.*;


import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order implements EntityId<UUID> {

 private UUID id;

 private String descriptions;

 private List<UUID> outfits;

 private UUID masterReceiver;

 private UUID idCar;

 private double priceSum;

 private UUID clientUUID;

 private UUID stateOrder;

 private List<UUID> label;

 private Date createdDate;

 private Date updatedDate;

}
