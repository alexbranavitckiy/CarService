package com.netcracker.servisec;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.user.Client;
import com.netcracker.user.Master;
import com.netcracker.user.MasterReceiver;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

public interface LoginService {
     String searchByUserLoginAndPassword(String login, String password) throws IOException ;
}
