package com.netcracker.DTO.car;

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

 @ApiModelProperty(name = "id", required = false, value = "Unique breakdown identifier.", position = 1)
 private UUID id;

 @Size(min = 0, max = 250, message = "Maximum length 250")
 @ApiModelProperty(name = "description", required = false, value = "Breakdown descriptions.", position = 2)
 private String description;

 @Min(value = 0, message = "Minimum mileage 1")
 @Max(value = 1000000, message = "Max mileage 1")
 @ApiModelProperty(name = "runCarSize", required = false, value = "Vehicle mileage during repair.", position = 3)
 private int runCarSize;

 @ApiModelProperty(name = "updateDate", required = false, value = "Update time.", position = 4)
 private Date updateDate;

 @ApiModelProperty(name = "State", required = false, value = "Breakdown condition.", position = 5)
 private State state;

 @Size(min = 0, max = 250, message = "Maximum length 250")
 @ApiModelProperty(name = "location", required = false, value = "Breakdown location.", position = 6)
 private String location;

 @ApiModelProperty(name = "IdCar", required = false, value = "Unique breakdown identifier.", position = 7)
 private UUID idCar;

}

