package com.netcracker;

import com.netcracker.errors.EmptySearchException;
import com.netcracker.marka.CarClient;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface CarServices {

 List<CarClient> getCarByIdClient(UUID uuidClient) throws EmptySearchException;

 List<CarClient> getAllCar() throws EmptySearchException;

 boolean addCar(CarClient carClient) throws IOException;

 boolean updateCarClient(CarClient carClient) throws IOException;

}
