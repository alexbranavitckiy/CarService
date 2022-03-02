package com.netcracker.servisec;

public class UserSession {

    private static final UserSession INSTANCE = new UserSession();

    private UserSession() {
    }

    public static UserSession getInstance() {
        return INSTANCE;
    }



}
