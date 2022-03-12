package com.netcracker.servisec;

import com.netcracker.errors.EmptySearchException;
import com.netcracker.marka.CarClient;
import com.netcracker.user.Client;

import java.io.IOException;
import java.util.List;

public interface ClientServices {

  List<Client> getAllClient() throws EmptySearchException;

  boolean addObjectInClient(Client client) throws IOException;

  boolean addObjectInClientNotOnline(Client client) throws IOException;

  boolean updateClient(Client client) throws IOException;

  boolean updateClientCar(CarClient carClient);

}
