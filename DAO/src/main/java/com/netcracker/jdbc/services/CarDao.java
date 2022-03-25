package com.netcracker.jdbc.services;

import com.netcracker.errors.PersistException;
import com.netcracker.marka.CarClient;

import java.util.List;
import java.util.UUID;

public interface CarDao extends CrudDao<CarClient, UUID> {

    List<CarClient> getAllCarClientByIdClient(UUID uuidClient) throws PersistException;


}
