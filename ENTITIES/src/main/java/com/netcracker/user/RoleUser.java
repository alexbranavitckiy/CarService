package com.netcracker.user;

import com.netcracker.EnumRole;

public enum RoleUser implements EnumRole {
 REGISTERED("REGISTERED"),
 UNREGISTERED("UNREGISTERED");
 private String code;

 RoleUser(String code) {
  this.code = code;
 }

 public String getCode() {
  return code;
 }
}

