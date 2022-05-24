package com.netcracker.DTO.time;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.netcracker.DTO.errs.SaveSearchErrorException;
import com.netcracker.outfit.State;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimeDto {

 @JsonView({ValidateTime.class})
 private UUID id;

 @JsonView({ValidateTime.class})
 private String name;

 @JsonProperty("descriptionOrder")
 @JsonView({ValidateTime.class})
 private String description;

 @NotNull(groups = ValidateTime.Edit.class)
 @JsonView({ValidateTime.class,ValidateTime.Edit.class})
 private UUID orderId;

 @JsonView({ValidateTime.class})
 private String masterName;

 @NotNull(groups = ValidateTime.Edit.class)
 @JsonView({ValidateTime.class,ValidateTime.Edit.class})
 private UUID masterId;

 @NotNull(groups = ValidateTime.Edit.class)
 @JsonView({ValidateTime.class,ValidateTime.Edit.class})
 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
 private Date dateStart;

 @NotNull(groups = ValidateTime.Edit.class)
 @JsonView({ValidateTime.class,ValidateTime.Edit.class})
 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
 private Date dateEnd;

 @JsonView({ValidateTime.class})
 private State state;

}
