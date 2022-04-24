package com.netcracker.DTO.errs;

public class SaveErrorException extends Exception {

 private String field;

 public String getField() {
  return field;
 }

 public void setField(String field) {
  this.field = field;
 }

 public SaveErrorException(String message) {
  super(message);
 }

 public SaveErrorException(String message, String field) {
  super(message);
  this.field = field;
 }
}
