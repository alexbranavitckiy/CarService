package com.netcracker.menu.validator;

import com.netcracker.menu.errors.InvalidValuesException;
import com.netcracker.user.Qualification;
import java.util.Scanner;


public interface ValidatorInstruments {

     boolean inEmail(String email) throws InvalidValuesException;

     Qualification qualificationEnum(Scanner in);

     String getLogin(Scanner in);

     String getPhone(Scanner in);

     String getNameUser(Scanner in);

     String getDescription(Scanner in);

     String getEducation(Scanner in);

     String getMail(Scanner in) throws InvalidValuesException;

     String getHomeAddress(Scanner in);

     String getPassword(Scanner in);

}
