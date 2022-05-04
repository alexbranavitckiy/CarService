package com.netcracker.DTO.user;

import com.fasterxml.jackson.annotation.JsonView;
import com.netcracker.DTO.basicValidation.ValidMaster;
import com.netcracker.DTO.car.ValidateCar;
import com.netcracker.user.Qualification;
import com.netcracker.user.Role;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ValidMaster(groups = {ValidateCar.UiCrossFieldChecks.class,ValidateClient.NewAdmin.class})
public class MasterDto {

 @JsonView(ValidateClient.Details.class)
 private UUID id;

 @ApiModelProperty(name = "name", required = true, value = "The username of the user who is registering")
 @JsonView({ValidateClient.Details.class, ValidateClient.Edit.class,ValidateClient.NewAdmin.class})
 @NotBlank(groups = {ValidateClient.New.class,ValidateClient.NewAdmin.class}, message = "Size violation. It must be between 4 and 30 characters")
 @Size(groups = {ValidateClient.Edit.class,ValidateClient.NewAdmin.class}, min = 4, max = 30, message = "Size violation. It must be between 4 and 30 characters")
 private String name;

 @Pattern(groups = {ValidateClient.NewAdmin.class}, regexp = "^(\\+375|80)(29|25|44|33)(\\d{3})(\\d{2})(\\d{2})$", message = "Phone should be valid")
 @JsonView({ValidateClient.Details.class,ValidateClient.NewAdmin.class})
 private String phone;

 @Pattern(groups = {ValidateClient.NewAdmin.class}, regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$", message = "Email should be valid")
 @JsonView({ValidateClient.Details.class,ValidateClient.NewAdmin.class})
 private String mail;

 @NotNull(groups = {ValidateClient.Edit.class})
 @Size(groups = {ValidateCar.Edit.class}, min = 0, max = 250, message = "Size violation. It must be between 0 and 250 characters")
 @JsonView({ValidateClient.Details.class, ValidateClient.Edit.class,ValidateClient.NewAdmin.class})
 private String description;

 @NotNull(groups = {ValidateClient.Edit.class})
 @Size(groups = {ValidateCar.Edit.class}, min = 0, max = 250, message = "Size violation. It must be between 0 and 250 characters")
 @JsonView({ValidateClient.Details.class, ValidateClient.Edit.class,ValidateClient.NewAdmin.class})
 private String homeAddress;

 @JsonView({ValidateClient.Details.class,ValidateClient.NewAdmin.class})
 private Qualification qualification;

 @NotNull(groups = {ValidateClient.Edit.class,ValidateClient.NewAdmin.class})
 @Size(groups = {ValidateCar.Edit.class,ValidateClient.NewAdmin.class}, min = 0, max = 250, message = "Size violation. It must be between 0 and 250 characters")
 @JsonView({ValidateClient.Details.class, ValidateClient.Edit.class,ValidateClient.NewAdmin.class})
 private String education;

 @JsonView({ValidateClient.Details.class,ValidateClient.NewAdmin.class})
 private String login;

 @Size(groups = {ValidateClient.NewAdmin.class}, min = 4, max = 20, message = "Size violation. It must be between 4 and 20 characters")
 @JsonView({ValidateClient.Admin.class,ValidateClient.NewAdmin.class})
 private String password;

 @NotNull(groups = {ValidateClient.NewAdmin.class})
 @JsonView({ValidateClient.Admin.class,ValidateClient.NewAdmin.class,ValidateClient.Details.class})
 private Role role;

}
