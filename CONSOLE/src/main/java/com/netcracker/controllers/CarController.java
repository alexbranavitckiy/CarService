package com.netcracker.controllers;

import com.netcracker.services.CarServices;
import org.springframework.beans.factory.annotation.Autowired;

public class CarController {

 private final CarServices carServices;


 @Autowired
 private CarController(CarServices carServices) {
  this.carServices = carServices;
 }


}
