package com.netcracker.breakdown;



public enum State {
 CORRECTED("CORRECTED"),
 NOT_FIXED("NOT_FIXED"),
 IMPORTANT("IMPORTANT"),
 RECORDED("RECORDED"),
 NEEDS_CORRECTED("NEEDS_CORRECTED");

 private String code;

 State(String code) {
  this.code = code;
 }

 public String getCode() {
  return code;
 }
}
