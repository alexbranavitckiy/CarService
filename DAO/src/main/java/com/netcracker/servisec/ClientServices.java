package com.netcracker.servisec;

import com.netcracker.marka.CarClient;
import com.netcracker.user.Client;

import java.io.IOException;

public interface ClientServices {

    boolean addObjectInClient(Client client) throws IOException;

    boolean updateClient(Client client)throws IOException;

    boolean updateClientCar(CarClient carClient);

}
