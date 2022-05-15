package com.netcracker.user;


public enum Qualification  {
 ELECTRICIAN("ELECTRICIAN"),
 DISC_EDITING("DISC_EDITING");

 private String code;

 Qualification(String code) {
  this.code = code;
 }

 public String getCode() {
  return code;
 }
}

