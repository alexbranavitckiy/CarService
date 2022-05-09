package com.netcracker.DTO.user;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonView;
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
@ValidClients(groups = {ValidateClient.UiCrossFieldChecks.class})
@JsonRootName(value = " Client")
public class ClientDto {

 @JsonView({ValidateClient.Details.class})
 private UUID id;

 @NotBlank(groups = {ValidateClient.Name.class, ValidateClient.masterRequest.class}, message = "Name must not be blank")
 @Size(groups = {ValidateClient.Name.class}, min = 4, max = 30)
 @JsonView({ValidateClient.Name.class, ValidateClient.Details.class, ValidateClient.masterRequest.class})
 @ApiModelProperty(name = "name", required = true, value = "The username of the user who is registering")
 private String name;

 @NotNull(groups = {ValidateClient.Phone.class} , message = "Name must not be blank")
 @Size(groups = {ValidateClient.Phone.class, ValidateClient.masterRequest.class}, min = 4, max = 30)
 @JsonView({ValidateClient.Phone.class, ValidateClient.Details.class, ValidateClient.masterRequest.class})
 @Pattern(groups = {ValidateClient.Phone.class, ValidateClient.masterRequest.class}, regexp = "^(\\+375|80)(29|25|44|33)(\\d{3})(\\d{2})(\\d{2})$", message = "Phone should be valid")
 @ApiModelProperty(name = "phone", required = true, value = "User phone")
 private String phone;

 @NotNull(groups = {ValidateClient.Email.class}  , message = "Must not be blank")
 @Size(groups = {ValidateClient.Email.class}, min = 1, max = 100)
 @JsonView({ValidateClient.Email.class, ValidateClient.Details.class})
 @ApiModelProperty(name = "email", required = true, value = "User mail")
 @Pattern(groups = {ValidateClient.Email.class}, regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$", message = "Email should be valid")
 private String email;

 @ApiParam(value = "Default value for note")
 @JsonView({ValidateClient.Description.class,ValidateClient.Admin.class, ValidateClient.masterRequest.class})
 private String description;

 @NotNull(groups = ValidateClient.Login.class , message = "Must not be blank")
 @Size(groups = ValidateClient.Login.class, min = 4, max = 30)
 @JsonView({ValidateClient.Login.class, ValidateClient.Details.class, ValidateClient.masterRequest.class})
 @ApiModelProperty(name = "email", required = true, value = "Login is used to login")
 private String login;

 @NotNull(groups = ValidateClient.Password.class , message = "Must not be blank")
 @Size(groups = ValidateClient.Password.class, min = 4, max = 12, message = "Password must be between 4 to 15 characters")
 @JsonView({ValidateClient.Admin.class, ValidateClient.Password.class})
 private String password;

 @ApiParam(value = "Default value for note")
 @JsonView({ValidateClient.Details.class})
 private RoleUser roleUser;

}
