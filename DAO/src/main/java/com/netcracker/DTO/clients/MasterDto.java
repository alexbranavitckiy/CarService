package com.netcracker.DTO.clients;

import com.fasterxml.jackson.annotation.JsonView;
import com.netcracker.DTO.basicValidation.ValidMaster;
import com.netcracker.DTO.car.ValidateCar;
import com.netcracker.user.Qualification;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ValidMaster(groups = {ValidateCar.UiCrossFieldChecks.class})
public class MasterDto {

 @JsonView(ValidateClient.Details.class)
 private UUID id;

 @ApiModelProperty(name = "name", required = true, value = "The username of the user who is registering")
 @JsonView({ValidateClient.Details.class, ValidateClient.Edit.class})
 @NotBlank(groups = {ValidateClient.New.class}, message = "Size violation. It must be between 4 and 30 characters")
 @Size(groups = {ValidateClient.Edit.class}, min = 4, max = 30)
 private String name;

 @JsonView({ValidateClient.Details.class})
 private String phone;

 @JsonView({ValidateClient.Details.class})
 private String mail;

 @NotNull(groups = {ValidateClient.Edit.class})
 @Size(groups = {ValidateCar.Edit.class}, min = 0, max = 250, message = "Size violation. It must be between 0 and 250 characters")
 @JsonView({ValidateClient.Details.class, ValidateClient.Edit.class})
 private String description;

 @NotNull(groups = {ValidateClient.Edit.class})
 @Size(groups = {ValidateCar.Edit.class}, min = 0, max = 250, message = "Size violation. It must be between 0 and 250 characters")
 @JsonView({ValidateClient.Details.class, ValidateClient.Edit.class})
 private String homeAddress;

 @JsonView({ValidateClient.Details.class})
 private Qualification qualification;

 @NotNull(groups = {ValidateClient.Edit.class})
 @Size(groups = {ValidateCar.Edit.class}, min = 0, max = 250, message = "Size violation. It must be between 0 and 250 characters")
 @JsonView({ValidateClient.Details.class, ValidateClient.Edit.class})
 private String education;

 @JsonView({ValidateClient.Details.class})
 private String login;

 @JsonView({ValidateClient.Admin.class})
 private String password;

}
