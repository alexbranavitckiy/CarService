package com.netcracker;

import com.netcracker.dto.model.ClientDto;
import com.netcracker.errors.EmptySearchException;
import com.netcracker.marka.CarClient;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface ClientServices {

  List<CarClient> getCarByIdClient(UUID uuidClient) throws EmptySearchException;

  List<ClientDto> getAllClient() throws EmptySearchException;

  boolean addObjectInClient(ClientDto client) throws IOException;

  boolean addObjectInClientNotOnline(ClientDto client) throws IOException;

  boolean updateClient(ClientDto client) throws IOException;

  boolean updateClientNotSession(ClientDto client) throws IOException;

  boolean updateClientCar(CarClient carClient);

}
