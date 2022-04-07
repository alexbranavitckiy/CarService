package com.netcracker.order;

public enum State {
 TEMPLATE("TEMPLATE"),
 RECORDED("RECORDED"),
 IN_WORK("IN_WORK"),
 CAR_GIVEN("CAR_GIVEN"),
 CAR_ACCEPTED("CAR_ACCEPTED"),
 WAIT_CLIENT("WAIT_CLIENT"),
 REQUEST("REQUEST"),
 BID("BID");

 private String code;

 State(String code) {
  this.code = code;
 }

 public String getCode() {
  return code;
 }
}

