package com.netcracker.menu.validator;

import com.netcracker.menu.errors.InvalidValuesException;
import com.netcracker.order.State;
import com.netcracker.user.Qualification;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class ValidatorInstrumentsImpl implements ValidatorInstruments {

  private final StringBuilder stringBuilder = new StringBuilder(20);

  private final String EMAIL_PATTERN =
      "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
          + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

  private final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

  @Override
  public boolean inEmail(String email) throws InvalidValuesException {
    if (email != null && !email.isBlank()) {
      Matcher matcher = pattern.matcher(email);
      return matcher.matches();
    } else {
      throw new InvalidValuesException(
          "Invalid values, please try again. Format: Leha03377@Hotmail.com");
    }
  }

  @Override
  public com.netcracker.outfit.State stateOutfit(Scanner in) {
    stringBuilder.delete(0, stringBuilder.length());
    log.info("Outfit status");
    log.info("Enter 1 create: END");
    log.info("Enter 2 create: WORK");
    log.info("Enter 3 create: NO_STATE");
    stringBuilder.append(in.next());
    switch (stringBuilder.toString()) {
      case "1": {
        return com.netcracker.outfit.State.END;
      }
      case "2": {
        return com.netcracker.outfit.State.WORK;
      }
      case "3": {
        return com.netcracker.outfit.State.NO_STATE;
      }
      default: {
        return stateOutfit(in);
      }
    }
  }

  @Override
  public State orderState(Scanner in) {
    stringBuilder.delete(0, stringBuilder.length());
    log.info("Enter 1 create: TEMPLATE");
    log.info("Enter 2 create: RECORDED");
    log.info("Enter 3 create: IN_WORK");
    log.info("Enter 4 create: CAR_GIVEN");
    log.info("Enter 5 create: CAR_ACCEPTED");
    log.info("Enter 6 create: WAIT_CLIENT");
    log.info("Enter 7 create: BID");
    stringBuilder.append(in.next());
    switch (stringBuilder.toString()) {
      case "1": {
        return State.TEMPLATE;
      }
      case "2": {
        return State.RECORDED;
      }
      case "3": {
        return State.IN_WORK;
      }
      case "4": {
        return State.CAR_GIVEN;
      }
      case "5": {
        return State.CAR_ACCEPTED;
      }
      case "6": {
        return State.WAIT_CLIENT;
      }
      case "7": {
        return State.BID;
      }
      default: {
        return orderState(in);
      }
    }
  }

  @Override
  public void successfullyMessages(boolean flag) {//recursion!
    if (flag) {
      log.info("Data saved successfully");
    } else {
      log.info("Data not saved please try again");
    }
  }


  @Override
  public Qualification qualificationEnum(Scanner in) {//recursion!
    stringBuilder.delete(0, stringBuilder.length());
    log.info("Enter 1 create: DISC_EDITING");
    log.info("Enter 2 create: ELECTRICIAN");
    stringBuilder.append(in.next());
    if (stringBuilder.toString().equalsIgnoreCase("1")) {
      return Qualification.DISC_EDITING;
    }
    if (stringBuilder.toString().equalsIgnoreCase("2")) {
      return Qualification.ELECTRICIAN;
    }
    return qualificationEnum(in);
  }

  @Override
  public String validateLogin(Scanner in) {
    stringBuilder.delete(0, stringBuilder.length());
    log.info("Enter login");
    stringBuilder.append(in.next());
    if (stringBuilder.length() > 3 && stringBuilder.length() < 20) {
      return stringBuilder.toString();
    }
    log.info("Login must contain from 3 to 20 characters");
    return validateLogin(in);
  }

  @Override
  public String validatePhone(Scanner in) {
    log.info("Enter phone");
    stringBuilder.delete(0, stringBuilder.length());
    stringBuilder.append(in.next());
    if (stringBuilder.length() == 13 && stringBuilder.charAt(0) == '+'
        && stringBuilder.charAt(1) == '3') {
      return stringBuilder.toString();
    }
    log.info("Enter must contain +375 ## #######");
    return validatePhone(in);
  }

  @Override
  public String validateMileage(Scanner in) {
    stringBuilder.delete(0, stringBuilder.length());
    log.info("Enter Mileage");
    stringBuilder.append(in.nextLong());
    if (stringBuilder.length() > 0 && stringBuilder.length() < 20) {
      return stringBuilder.toString();
    }
    log.info("Mileage must contain from 0 to 20 numbers");
    return validateMileage(in);
  }

  @Override
  public double validatePrice(Scanner in) {
    log.info("Enter Price");
    return in.nextDouble();
  }

  @Override
  public String validateYear(Scanner in) {
    stringBuilder.delete(0, stringBuilder.length());
    log.info("Enter year of car");
    stringBuilder.append(in.nextLong());
    if (stringBuilder.length() == 4) {
      return stringBuilder.toString();
    }
    log.info("Year must contain from 4 numbers");
    return validateYear(in);
  }

  @Override
  public String validateNumberCar(Scanner in) {
    stringBuilder.delete(0, stringBuilder.length());
    log.info("Enter number of car");
    stringBuilder.append(in.next());
    if (stringBuilder.length() > 5 && stringBuilder.length() < 20) {
      return stringBuilder.toString();
    }
    log.info("Vehicle number must contain at least 7 characters");
    return validateNumberCar(in);
  }

  @Override
  public String validateNameOutfit(Scanner in) {
    stringBuilder.delete(0, stringBuilder.length());
    log.info("Enter Name outfit");
    stringBuilder.append(in.next());
    if (stringBuilder.length() > 1 && stringBuilder.length() < 20) {
      return stringBuilder.toString();
    }
    log.info("Name must contain from 1 to 20 characters");
    return validateNameOutfit(in);
  }


  @Override
  public String validateNameUser(Scanner in) {
    stringBuilder.delete(0, stringBuilder.length());
    log.info("Enter Name");
    stringBuilder.append(in.next());
    if (stringBuilder.length() > 3 && stringBuilder.length() < 20) {
      return stringBuilder.toString();
    }
    log.info("Name must contain from 3 to 20 characters");
    return validateNameUser(in);
  }

  @Override
  public boolean edit(String fieldName, Scanner in) {
    log.info(fieldName);
    log.info("Enter 1 to skip");
    log.info("Enter 2 to edit");
    return in.next().equalsIgnoreCase("2");
  }

  @Override
  public String validateDescription(Scanner in) {
    stringBuilder.delete(0, stringBuilder.length());
    log.info("Enter Description");
    stringBuilder.append(in.next());
    if (stringBuilder.length() > 3 && stringBuilder.length() < 50) {
      return stringBuilder.toString();
    }
    log.info("Description must contain from 4 to 50 characters");
    return validateDescription(in);
  }

  @Override
  public String validateEducation(Scanner in) {
    log.info("Enter Education");
    return in.next();
  }

  @Override
  public String validateMail(Scanner in) {//recursion!
    try {
      stringBuilder.delete(0, stringBuilder.length());
      log.info("Enter Mail");
      stringBuilder.append(in.next());
      if (this.inEmail(stringBuilder.toString())) {
        return stringBuilder.toString();
      }
      log.info("Invalid values, please try again. Format: Leha03377@Hotmail.com");
    } catch (InvalidValuesException e) {
      log.warn("Invalid values entered:{}", e.getLocalizedMessage());
    }
    return this.validateMail(in);
  }

  @Override
  public String validateHomeAddress(Scanner in) {
    log.info("Enter HomeAddress");
    return in.next();
  }


  @Override
  public String validatePassword(Scanner in) {
    stringBuilder.delete(0, stringBuilder.length());
    log.info("Enter password");
    stringBuilder.append(in.next());
    if (stringBuilder.length() > 3 && stringBuilder.length() < 20) {
      return stringBuilder.toString();
    }
    log.info("Password must contain from 3 to 20 characters");
    return validatePassword(in);

  }
}
