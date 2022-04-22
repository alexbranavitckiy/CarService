package com.netcracker.DTO.ord;

import com.netcracker.order.State;
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
public class OrderDto {

 private UUID id;

 private String description;

 private UUID carClient;

 private State state;

 private Date createdDate;

 private Date updatedDate;

}
