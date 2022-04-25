package com.netcracker.DTO.clients;

import com.fasterxml.jackson.annotation.JsonView;
import com.netcracker.DTO.Validate;
import com.netcracker.DTO.basicValidation.ValidClients;
import com.netcracker.user.RoleUser;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Client entity")
@ValidClients(groups = {Validate.UiCrossFieldChecks.class})
public class ClientDto {

 @Null(groups = Validate.Edit.class)
 @JsonView({Validate.Details.class})
 private UUID id;

 @NotBlank(groups = {Validate.Edit.class}, message = "Name must not be blank")
 @Size(groups = {Validate.Edit.class}, min = 4, max = 30)
 @JsonView({Validate.Edit.class,Validate.Details.class})
 @ApiModelProperty(name = "name", required = true, value = "The username of the user who is registering")
 private String name;

 @NotNull(groups = {Validate.Edit.class})
 @Size(groups = {Validate.Edit.class}, min = 4, max = 30)
 @JsonView({Validate.Edit.class,Validate.Details.class})
 @Pattern(groups = {Validate.Edit.class}, regexp = "^(\\+375|80)(29|25|44|33)(\\d{3})(\\d{2})(\\d{2})$", message = "Phone should be valid")
 @ApiModelProperty(name = "phone", required = true, value = "User phone")
 private String phone;

 @NotNull(groups = {Validate.Edit.class})
 @Size(groups = {Validate.Edit.class}, min = 1, max = 100)
 @JsonView({Validate.Edit.class,Validate.Details.class})
 @ApiModelProperty(name = "email", required = true, value = "User mail")
 @Pattern(groups = {Validate.Edit.class}, regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$", message = "Email should be valid")
 private String email;

 @Null(groups = Validate.Edit.class)
 @ApiParam(value = "Default value for note")
 @JsonView({Validate.Admin.class})
 private String description;

 @NotNull(groups = Validate.New.class)
 @Null(groups = Validate.EditValue.class)
 @Size(groups = Validate.New.class, min = 4, max = 30)
 @JsonView({Validate.New.class,Validate.Details.class})
 @ApiModelProperty(name = "email", required = true, value = "Login is used to login")
 private String login;


 @Null(groups = Validate.EditValue.class)
 @NotNull(groups = Validate.New.class)
 @Size(groups = Validate.New.class, min = 4, max = 12, message = "Password must be between 4 to 15 characters")
 @JsonView({Validate.Admin.class, Validate.New.class})
 private String password;

 @ApiParam(value = "Default value for note")
 @Null(groups = {Validate.Edit.class})
 @JsonView({Validate.Details.class})
 private RoleUser roleUser;

}
