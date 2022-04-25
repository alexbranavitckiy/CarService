package com.netcracker.DTO.car;

import com.fasterxml.jackson.annotation.JsonView;
import com.netcracker.DTO.Validate;
import com.netcracker.DTO.basicValidation.ValidCarClient;
import com.netcracker.DTO.basicValidation.ValidClients;
import com.netcracker.car.Mark;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ValidCarClient(groups = {Validate.UiCrossFieldChecks.class})
public class CarClientDto {

 @Null(groups = {Validate.Edit.class})
 @JsonView({Validate.Details.class})
 @ApiModelProperty(name = "id", required = false, value = "Machine unique identifier.", position = 1)
 private UUID id;

 @JsonView({Validate.Admin.class})
 @Size(groups = {Validate.Admin.class}, min = 0, max = 250, message = "Size violation. It must be between 0 and 250 characters")
 @Null(groups = {Validate.Edit.class})
 @ApiModelProperty(name = "summary", required = false, value = "Description of the breakdown and data for the receiver master. Data is hidden from the client", position = 2)
 private String summary;

 @JsonView({Validate.Edit.class})
 @Size(groups = {Validate.Edit.class}, min = 0, max = 250, message = "Size violation. It must be between 0 and 250 characters")
 @ApiModelProperty(name = "description", required = false, value = "Description of the breakdown and data for the Client.", position = 3)
 private String description;

 @JsonView({Validate.Edit.class})
 @NotNull(groups = {Validate.Edit.class}, message = "Invalid car year entered")
 @ApiModelProperty(name = "ear", required = true, value = "Production year.", position = 4)
 private Date ear;

 @JsonView({Validate.Edit.class})
 @Size(groups = {Validate.Edit.class}, min = 4, max = 20, message = "Size violation. It must be between 4 and 20 characters")
 @ApiModelProperty(name = "metadataCar", required = true, value = "Vehicle number.", position = 5)
 private String metadataCar;

 @JsonView({Validate.Edit.class})
 @ApiModelProperty(name = "run", required = true, value = "Car mileage.", position = 6)
 private int run;

 @JsonView({Validate.Admin.class})
 @Null(groups = {Validate.Edit.class})
 @ApiModelProperty(name = "idClient", required = false, value = "Unique user ID.", position = 7)
 private UUID idClient;

 @JsonView({Validate.Edit.class})
 @ApiModelProperty(name = "mark", required = true, value = "Mark.", position = 8)
 private MarkDto mark;


}
