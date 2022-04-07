package com.netcracker.user;

public enum Role  {

 MASTER("MASTER"),
 RECEPTIONIST("RECEPTIONIST");

 private String code;

 Role(String code) {
  this.code = code;
 }

 public String getCode() {
  return code;
 }
}

