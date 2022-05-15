package com.netcracker.DTO.errs;

import com.fasterxml.jackson.annotation.JsonInclude;

public class SaveSearchErrorException extends Exception {

 @JsonInclude(JsonInclude.Include.NON_NULL)
 private String field="field";

 public String getField() {
  return field;
 }

 public void setField(String field) {
  this.field = field;
 }

 public SaveSearchErrorException(String message) {
  super(message);
 }

 public SaveSearchErrorException(String message, String field) {
  super(message);
  this.field = field;
 }
}
