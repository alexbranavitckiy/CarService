package com.netcracker.DTO.response;

import com.netcracker.DTO.Validate;
import com.netcracker.DTO.basicValidation.ValidClients;
import com.netcracker.DTO.basicValidation.ValidPasswordLogin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ValidPasswordLogin(groups = {Validate.UiCrossFieldChecks.class})
public class ContactConfirmationPayload {

 @NotNull(groups = {Validate.UiCrossFieldChecks.class})
 @Size(groups = {Validate.UiCrossFieldChecks.class}, min = 4, max = 12, message = "Password must be between 4 to 15 characters")
 private String password;

 @NotNull(groups = {Validate.UiCrossFieldChecks.class})
 @Size(groups = {Validate.UiCrossFieldChecks.class}, min = 4, max = 30,message = "login must be between 4 to 15 characters")
 private String login;

}
