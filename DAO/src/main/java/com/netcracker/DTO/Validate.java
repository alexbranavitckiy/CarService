package com.netcracker.DTO;

public interface Validate {

 interface Admin extends Details {
 }

 interface Details extends New,Edit,EditValue{
 }


 interface New extends Edit{
 }


 interface EditValue extends Edit{

 }

 interface Edit  {
 }



 interface UiCrossFieldChecks {

 }


}
