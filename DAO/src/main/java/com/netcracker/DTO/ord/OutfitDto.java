package com.netcracker.DTO.ord;

import com.netcracker.outfit.State;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OutfitDto {

 private UUID id;

 private String name;

 private String description;

 private Date dateStart;

 private Date dateEnt;

 private double price;

 private State stateOutfit;

}
