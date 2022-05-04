package com.netcracker.DTO.car;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.netcracker.DTO.ord.OutfitDto;
import com.netcracker.breakdown.State;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Entity of car breakdowns")
public class CarBreakdownDto {

 @JsonView({ValidateBreakdown.Details.class, ValidateBreakdown.Edit.class})
 @ApiModelProperty(name = "id", required = false, value = "Unique breakdown identifier.", position = 1)
 private UUID id;

 @JsonView({ValidateBreakdown.Details.class, ValidateBreakdown.Edit.class,ValidateBreakdown.New.class})
 @Size(min = 0, max = 250, message = "Maximum length 250")
 @ApiModelProperty(name = "description", required = false, value = "Breakdown descriptions.", position = 2)
 private String description;

 @JsonView({ValidateBreakdown.Details.class, ValidateBreakdown.Edit.class})
 @Min(value = 0, message = "Minimum mileage 1")
 @Max(value = 1000000, message = "Max mileage 1")
 @ApiModelProperty(name = "runCarSize", required = false, value = "Vehicle mileage during repair.", position = 3)
 private int runCarSize;

 @JsonView({ValidateBreakdown.Details.class})
 @ApiModelProperty(name = "updateDate", required = false, value = "Update time.", position = 4)
 private Date updateDate;

 @JsonView({ValidateBreakdown.Details.class, ValidateBreakdown.Edit.class})
 @ApiModelProperty(name = "State", required = false, value = "Breakdown condition.", position = 5)
 private State state;

 @JsonView({ValidateBreakdown.Details.class, ValidateBreakdown.Edit.class,ValidateBreakdown.New.class})
 @Size(min = 0, max = 250, message = "Maximum length 250")
 @ApiModelProperty(name = "location", required = false, value = "Breakdown location.", position = 6)
 private String location;

 @JsonView({ValidateBreakdown.Details.class})
 @ApiModelProperty(name = "IdCar", required = false, value = "Unique breakdown identifier.", position = 7)
 private UUID idCar;

 @JsonIgnore
 private OutfitDto outfitDto;

 @JsonView({ValidateBreakdown.Details.class, ValidateBreakdown.Edit.class,ValidateBreakdown.New.class})
 private double price;
}

