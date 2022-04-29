package com.netcracker.DTO.ord;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonView;
import com.netcracker.DTO.car.ValidateCar;
import com.netcracker.outfit.State;
import io.swagger.annotations.ApiModelProperty;
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
@JsonRootName(value = "Outfit")
public class OutfitDto {

 @JsonView({ValidateOrd.Details.class})
 @Null(groups = {ValidateCar.Edit.class}, message = "Invalid ID entered")
 @ApiModelProperty(name = "id", required = false, value = "Outfit unique identifier.", position = 1)
 private UUID id;

 @JsonView({ValidateOrd.Details.class, ValidateCar.Edit.class})
 private String name;

 @JsonView({ValidateOrd.Details.class, ValidateCar.Edit.class})
 private String description;

 @JsonView({ValidateOrd.Details.class, ValidateCar.Edit.class})
 private Date dateStart;

 @JsonView({ValidateOrd.Details.class, ValidateCar.Edit.class})
 private Date dateEnt;

 @JsonView({ValidateOrd.Details.class})
 private double price;

 @JsonView({ValidateOrd.Details.class, ValidateCar.Edit.class})
 private State stateOutfit;

}
