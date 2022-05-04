package com.netcracker.DTO.car;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonView;
import com.netcracker.DTO.basicValidation.ValidCarClient;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ValidCarClient(groups = {ValidateCar.UiCrossFieldChecks.class})
@JsonRootName(value = "Car")
public class CarClientDto {

 @JsonView({ValidateCar.Details.class, ValidateCar.Edit.class, ValidateCar.EditValue.class})
 @NotNull(groups = {ValidateCar.EditValue.class}, message = "Invalid ID entered")
 @Null(groups = {ValidateCar.New.class}, message = "Invalid ID entered")
 @ApiModelProperty(name = "id", required = false, value = "Machine unique identifier.", position = 1)
 private UUID id;

 @JsonView({ValidateCar.Admin.class, ValidateCar.NewAdmin.class})
 @Size(groups = {ValidateCar.Admin.class}, min = 0, max = 250, message = "Size violation. It must be between 0 and 250 characters")
 @Null(groups = {ValidateCar.Edit.class})
 @ApiModelProperty(name = "summary", required = false, value = "Description of the breakdown and data for the receiver master. Data is hidden from the client", position = 2)
 private String summary;

 @JsonView({ValidateCar.New.class, ValidateCar.Edit.class, ValidateCar.NewAdmin.class})
 @Size(groups = {ValidateCar.New.class, ValidateCar.Edit.class}, min = 0, max = 250, message = "Size violation. It must be between 0 and 250 characters")
 @ApiModelProperty(name = "description", required = false, value = "Description of the breakdown and data for the Client.", position = 3)
 private String description;

 @JsonView({ValidateCar.New.class, ValidateCar.Edit.class, ValidateCar.NewAdmin.class})
 @NotNull(groups = {ValidateCar.New.class, ValidateCar.Edit.class}, message = "Invalid car year entered")
 @ApiModelProperty(name = "ear", required = true, value = "Production year.", position = 4)
 private Date ear;

 @JsonView({ValidateCar.EditValue.class, ValidateCar.New.class, ValidateCar.NewAdmin.class})
 @Size(groups = {ValidateCar.EditValue.class}, min = 4, max = 20, message = "Size violation. It must be between 4 and 20 characters")
 @Null(groups = {ValidateCar.Edit.class})
 @ApiModelProperty(name = "metadataCar", required = true, value = "Vehicle number.", position = 5)
 private String metadataCar;

 @Max(value = 1000000, groups = {ValidateCar.New.class, ValidateCar.Edit.class}, message = "Size violation. It must be between 0 and 20 characters")
 @JsonView({ValidateCar.Edit.class, ValidateCar.New.class})
 @ApiModelProperty(name = "run", required = true, value = "Car mileage.", position = 6)
 private int run;

 @JsonView({ValidateCar.Admin.class, ValidateCar.NewAdmin.class})
 @Null(groups = {ValidateCar.Edit.class})
 @NotNull(groups = {ValidateCar.NewAdmin.class})
 @ApiModelProperty(name = "idClient", required = false, value = "Unique user ID.", position = 7)
 private UUID idClient;

 @JsonView({ValidateCar.New.class, ValidateCar.Edit.class, ValidateCar.NewAdmin.class})
 @ApiModelProperty(name = "mark", required = true, value = "Mark.", position = 8)
 private MarkDto mark;

}
