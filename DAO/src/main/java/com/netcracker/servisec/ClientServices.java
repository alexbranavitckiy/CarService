package com.netcracker.servisec;

import com.netcracker.user.Client;

import java.io.IOException;

public interface ClientServices {

    boolean addObjectInClient(Client client) throws IOException;

    boolean updateClient(Client client)throws IOException;

}
