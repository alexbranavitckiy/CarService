package com.netcracker.DTO.car;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonRootName(value = " Mark")
public class MarkDto {

 @NotNull(groups = {ValidateCar.NewAdmin.class})
 @JsonView({ValidateCar.Edit.class, ValidateCar.New.class, ValidateCar.Details.class, ValidateCar.NewAdmin.class})
 @Size(groups = {ValidateCar.New.class, ValidateCar.NewAdmin.class}, min = 0, max = 200)
 @ApiModelProperty(name = "idMark", required = false, value = "Mark unique identifier.", position = 8)
 private UUID id;

 @JsonView({ValidateCar.Details.class})
 private String name;

 @JsonView({ValidateCar.Details.class})
 @Column(name = "year_start")
 private Date yearStart;

 @JsonView({ValidateCar.Details.class})
 @Column(name = "year_end")
 private Date yearEnd;

}
