package com.netcracker.DTO.car;

import com.netcracker.car.Mark;
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
public class CarClientDto {

 private UUID id;

 private String summary;

 private String description;

 private Date ear;

 private String metadataCar;

 private int run;

 private UUID idClient;

 private Mark mark;
}
