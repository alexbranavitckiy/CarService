package com.netcracker.DTO.car;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.netcracker.DTO.Validate;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MarkDto  {

 @Id
 @JsonView({Validate.Edit.class})
 @Size(groups = {Validate.Edit.class}, min = 0, max = 200)
 @ApiModelProperty(name = "idMark", required = false, value = "Mark unique identifier.", position = 8)
 private UUID id;

 @JsonView({Validate.Details.class})
 private String name;

 @JsonView({Validate.Details.class})
 @Column(name = "year_start")
 private Date yearStart;

 @JsonView({Validate.Details.class})
 @Column(name = "year_end")
 private Date yearEnd;

}
