package com.netcracker.DTO.ord;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.netcracker.DTO.errs.SaveSearchErrorException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderForm {

 @NotNull(groups = {ValidateOrd.EditValue.class})
 @JsonView({ValidateOrd.EditValue.class})
 private UUID idOrder;

 @NotNull(groups = {ValidateOrd.NewAdmin.class, ValidateOrd.EditValue.class})
 @JsonView({ValidateOrd.NewAdmin.class, ValidateOrd.EditValue.class})
 private String description;

 @NotNull(groups = {ValidateOrd.NewAdmin.class})
 @JsonView({ValidateOrd.NewAdmin.class})
 private UUID carClient;

 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
 @NotNull(groups = {ValidateOrd.NewAdmin.class, ValidateOrd.EditValue.class})
 @JsonView({ValidateOrd.NewAdmin.class, ValidateOrd.EditValue.class})
 private Date dateEndOutfit;

 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
 @NotNull(groups = {ValidateOrd.NewAdmin.class, ValidateOrd.EditValue.class})
 @JsonView({ValidateOrd.NewAdmin.class, ValidateOrd.EditValue.class})
 private Date dateStartOutfit;

 @NotNull(groups = {ValidateOrd.NewAdmin.class, ValidateOrd.EditValue.class})
 @JsonView({ValidateOrd.NewAdmin.class, ValidateOrd.EditValue.class})
 private UUID idMasterOutfit;

 @NotNull(groups = {ValidateOrd.NewAdmin.class, ValidateOrd.EditValue.class})
 @JsonView({ValidateOrd.NewAdmin.class, ValidateOrd.EditValue.class})
 private String nameOutfit;

 @NotNull(groups = {ValidateOrd.NewAdmin.class, ValidateOrd.EditValue.class})
 @JsonView({ValidateOrd.NewAdmin.class, ValidateOrd.EditValue.class})
 private double priceBreakdown;

 @NotNull(groups = {ValidateOrd.NewAdmin.class, ValidateOrd.EditValue.class})
 @JsonView({ValidateOrd.NewAdmin.class, ValidateOrd.EditValue.class})
 private int run;

 @JsonSetter("dateEndOutfit")
 public void setDateEndOutfit(Date dateEndOutfit) throws SaveSearchErrorException {
  if (dateEndOutfit.before(Date.from(new Date().toInstant())))
   throw new SaveSearchErrorException("Invalid data entered", "Start date");
  this.dateEndOutfit = dateEndOutfit;
 }

 @JsonSetter("dateStartOutfit")
 public void setDateStartOutfit(Date dateStartOutfit) throws SaveSearchErrorException {
  if (!dateStartOutfit.before(Date.from(new Date().toInstant())) && dateStartOutfit.before(this.dateEndOutfit))
   this.dateStartOutfit = dateStartOutfit;
  else
   throw new SaveSearchErrorException("Invalid data entered", "End date");
 }

}
