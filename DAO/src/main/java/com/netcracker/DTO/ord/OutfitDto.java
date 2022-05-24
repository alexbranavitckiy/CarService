package com.netcracker.DTO.ord;

import com.fasterxml.jackson.annotation.*;
import com.netcracker.DTO.basicValidation.ValidOutfit;
import com.netcracker.DTO.car.ValidateCar;
import com.netcracker.outfit.State;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.Null;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ValidOutfit(groups = {ValidateCar.Edit.class})
public class OutfitDto {

 @JsonView({ValidateOrd.Details.class})
 @Null(groups = {ValidateCar.Edit.class}, message = "Invalid ID entered")
 @ApiModelProperty(name = "id", required = false, value = "Outfit unique identifier.", position = 1)
 private UUID id;

 @JsonView({ValidateOrd.Details.class, ValidateCar.Edit.class, ValidateCar.NewAdmin.class})
 private String name;

 @JsonProperty("descriptionOutfit")
 @JsonView({ValidateOrd.Details.class, ValidateCar.Edit.class, ValidateCar.NewAdmin.class})
 private String description;

 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
 @JsonView({ValidateOrd.Details.class, ValidateCar.Edit.class, ValidateCar.NewAdmin.class})
 private Date dateStart;

 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
 @JsonView({ValidateOrd.Details.class, ValidateCar.Edit.class, ValidateCar.NewAdmin.class})
 private Date dateEnd;

 @JsonView({ValidateOrd.Details.class, ValidateCar.Edit.class})
 private State stateOutfit;

 @JsonView({ValidateOrd.Details.class, ValidateCar.NewAdmin.class})
 private UUID idMaster;

 @JsonView({ValidateCar.NewAdmin.class})
 private UUID order;

}
