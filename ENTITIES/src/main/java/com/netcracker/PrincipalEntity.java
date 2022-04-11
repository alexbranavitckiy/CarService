package com.netcracker;

public interface PrincipalEntity {

  String getPassword();

  String getLogin();

  void setPassword(String encode);
}
