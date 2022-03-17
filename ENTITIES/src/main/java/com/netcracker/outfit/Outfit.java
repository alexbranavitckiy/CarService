package com.netcracker.outfit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.netcracker.order.Order;
import com.netcracker.time.Entry;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Outfit {

  private UUID id;

  private String name;

  private String descriptions;

  private UUID order;

  private UUID employer;

  private Date dateStart;

  private Date dateEnt;

  private double price;

  private State stateOutfit;

}
