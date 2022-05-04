package com.netcracker.DTO.ord;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
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
public class OrderForm {

 @NotNull(groups = {ValidateOrd.NewAdmin.class})
 @JsonView({ValidateOrd.NewAdmin.class})
 private String description;

 @NotNull(groups = {ValidateOrd.NewAdmin.class})
 @JsonView({ValidateOrd.NewAdmin.class})
 private UUID carClient;

 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm")
 @NotNull(groups = {ValidateOrd.NewAdmin.class})
 @JsonView({ValidateOrd.NewAdmin.class})
 private Date dateEntOutfit;

 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm")
 @NotNull(groups = {ValidateOrd.NewAdmin.class})
 @JsonView({ValidateOrd.NewAdmin.class})
 private Date dateStartOutfit;

 @NotNull(groups = {ValidateOrd.NewAdmin.class})
 @JsonView({ValidateOrd.NewAdmin.class})
 private UUID idMasterOutfit;

 @NotNull(groups = {ValidateOrd.NewAdmin.class})
 @JsonView({ValidateOrd.NewAdmin.class})
 private String nameOutfit;

 @NotNull(groups = {ValidateOrd.NewAdmin.class})
 @JsonView({ValidateOrd.NewAdmin.class})
 private double priceBreakdown;

 @NotNull(groups = {ValidateOrd.NewAdmin.class})
 @JsonView({ValidateOrd.NewAdmin.class})
 private int run;
}
