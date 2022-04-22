package com.netcracker.DTO.car;

import com.netcracker.breakdown.State;
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
public class CarBreakdownForm {

 private UUID id;

 private String description;

 private int runCarSize;

 private Date updateDate;

 private State state;

 private String location;

 private UUID idCar;

}
