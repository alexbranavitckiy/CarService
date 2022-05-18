package com.netcracker.DTO.ord;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.netcracker.order.State;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;
import java.util.UUID;

public interface ValidateOrd {

 interface Details {
 }

 interface New {
 }

 interface EditAdmin extends Description, State {
 }

 interface EditValue {
 }

 interface NewAdmin {
 }


 interface Id {
 }

 interface Description extends Id {
 }

 interface CarClient extends Id {
 }

 interface State extends Id {
 }

 interface CreatedDate extends Id {
 }

 interface UpdatedDate extends Id {
 }


}
