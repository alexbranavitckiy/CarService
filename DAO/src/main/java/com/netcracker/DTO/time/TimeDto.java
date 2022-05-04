package com.netcracker.DTO.time;

import com.fasterxml.jackson.annotation.JsonView;
import com.netcracker.DTO.car.CarBreakdownDto;
import com.netcracker.DTO.car.ValidateBreakdown;
import com.netcracker.DTO.ord.OrderDto;
import com.netcracker.DTO.user.MasterDto;
import com.netcracker.breakdown.CarBreakdown;
import com.netcracker.order.Order;
import com.netcracker.outfit.State;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimeDto {

 @JsonView({ValidateTime.class})
 private UUID id;

 @JsonView({ValidateTime.class})
 private String name;

 @JsonView({ValidateTime.class})
 private String description;

 @JsonView({ValidateTime.class})
 private UUID orderId;

 @JsonView({ValidateTime.class})
 private String masterName;

 @JsonView({ValidateTime.class})
 private UUID masterId;

 @JsonView({ValidateTime.class})
 private Date dateStart;

 @JsonView({ValidateTime.class})
 private Date dateEnd;

 @JsonView({ValidateTime.class})
 private State state;

}
