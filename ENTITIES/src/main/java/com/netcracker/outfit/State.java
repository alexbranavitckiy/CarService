package com.netcracker.outfit;


public enum State {
 WORK("WORK"),
 END("END"),
 RECORDED("RECORDED"),
 NO_STATE("NO_STATE");

 private String code;

 State(String code) {
  this.code = code;
 }

 public String getCode() {
  return code;
 }
}

