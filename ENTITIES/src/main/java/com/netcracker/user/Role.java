package com.netcracker.user;

import com.netcracker.EnumRole;

public enum Role  implements EnumRole {

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

