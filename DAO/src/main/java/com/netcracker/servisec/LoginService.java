package com.netcracker.servisec;


import java.io.IOException;


public interface LoginService {
     boolean searchByUserLoginAndPassword(String login, String password) throws IOException ;
}
