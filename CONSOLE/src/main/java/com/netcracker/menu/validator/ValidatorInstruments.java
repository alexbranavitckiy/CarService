package com.netcracker.menu.validator;

import com.netcracker.menu.errors.InvalidValuesException;
import com.netcracker.order.State;
import com.netcracker.user.Qualification;
import java.util.Scanner;


public interface ValidatorInstruments {

  boolean inEmail(String email) throws InvalidValuesException;

  Qualification qualificationEnum(Scanner in);

  State orderState(Scanner in);

  String getLogin(Scanner in);

  String getPhone(Scanner in);

  String getMileage(Scanner in);

  String getNameUser(Scanner in);

  String getDescription(Scanner in);

  String getEducation(Scanner in);

  String getMail(Scanner in);

  double getPrice(Scanner in);

  String getNameOutfit(Scanner in);

  String getHomeAddress(Scanner in);

  String getPassword(Scanner in);

  String getYear(Scanner in);

  String getNumberCar(Scanner in);

  boolean edit(String fieldName, Scanner in);

  com.netcracker.outfit.State stateOutfit(Scanner in);

  void successfullyMessages(boolean flag);
}
