package com.netcracker.DTO.ord;


import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonView;
import com.netcracker.DTO.basicValidation.ValidCarClient;
import com.netcracker.DTO.basicValidation.ValidOrder;
import com.netcracker.DTO.car.ValidateCar;
import com.netcracker.DTO.clients.ValidateClient;
import com.netcracker.order.State;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ValidOrder(groups = {ValidateOrd.EditValue.class,ValidateOrd.New.class})
public class OrderDto {

 @Null(groups = {ValidateOrd.New.class})
 @NotNull(groups = {ValidateOrd.EditValue.class})
 @JsonView({ValidateOrd.Details.class,ValidateOrd.EditValue.class})
 private UUID id;

 @NotNull(groups = {ValidateOrd.New.class})
 @JsonView({ValidateOrd.Details.class, ValidateOrd.New.class})
 private String description;

 @NotNull(groups = {ValidateOrd.New.class})
 @JsonView({ValidateOrd.Details.class, ValidateOrd.New.class})
 private UUID carClient;

 @Null(groups = {ValidateOrd.New.class})
 @JsonView({ValidateOrd.Details.class})
 private State state;

 @Null(groups = {ValidateOrd.New.class})
 @JsonView({ValidateOrd.Details.class})
 private Date createdDate;

 @Null(groups = {ValidateOrd.New.class})
 @JsonView({ValidateOrd.Details.class})
 private Date updatedDate;

}
