package com.netcracker.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.netcracker.user.Client;
import com.netcracker.outfit.Outfit;
import com.netcracker.time.Entry;
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

  private double priceSum;

  private UUID clientUUID;

  private State stateOrder;

  private List<UUID> entry;

  private Date dateCreat;

  private Date dateUpdate;

}
