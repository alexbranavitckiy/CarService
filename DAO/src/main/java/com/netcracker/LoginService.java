package com.netcracker;


import java.io.IOException;


public interface LoginService {

  boolean searchByUserLoginAndPassword(String login, String password) throws IOException;
}
