package com.netcracker.user;

public enum RoleUser {
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

