package com.netcracker.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;


import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

  private UUID id;

  private String descriptions;

  private List<UUID> outfits;

  private UUID idCar;

  private double priceSum;

  private UUID clientUUID;

  private State stateOrder;

  private List<UUID> entry;

  private Date dateCreat;

  private Date dateUpdate;

}
