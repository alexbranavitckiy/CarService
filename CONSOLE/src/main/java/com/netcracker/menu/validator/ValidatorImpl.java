package com.netcracker.menu.validator;

import com.netcracker.menu.errors.InvalidValuesException;
import com.netcracker.user.Qualification;
import com.netcracker.user.Role;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class ValidatorImpl implements ValidatorInstruments {

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
        } else throw new InvalidValuesException("Invalid values, please try again. Format: Leha03377@Hotmail.com");
    }


    @Override
    public Qualification qualificationEnum(Scanner in) {//recursion!
        log.info("Enter 1 create: DISC_EDITING");
        log.info("Enter 2 create: ELECTRICIAN");
        String str = in.next();
        if (str.equalsIgnoreCase("1")) return Qualification.DISC_EDITING;
        if (str.equalsIgnoreCase("2")) return Qualification.ELECTRICIAN;
        return qualificationEnum(in);
    }

    @Override
    public String getLogin(Scanner in) {
        stringBuilder.delete(0, stringBuilder.length());
        log.info("Enter login");
        stringBuilder.append(in.next());
        if (stringBuilder.length() > 3 && stringBuilder.length() < 20)
            return stringBuilder.toString();
        log.info("Login must contain from 3 to 20 characters");
        return getLogin(in);
    }

    @Override
    public String getPhone(Scanner in) {
        log.info("Enter phone");
        stringBuilder.delete(0, stringBuilder.length());
        stringBuilder.append(in.next());
        if (stringBuilder.length() == 13 && stringBuilder.charAt(0) == '+' && stringBuilder.charAt(1) == '3')
            return stringBuilder.toString();
        log.info("Enter must contain +375 ## #######");
        return getPhone(in);
    }

    @Override
    public String getMileage(Scanner in) {
        stringBuilder.delete(0, stringBuilder.length());
        log.info("Enter Mileage");
        stringBuilder.append(in.nextLong());
        if (stringBuilder.length() > 0 && stringBuilder.length() < 20)
            return stringBuilder.toString();
        log.info("Mileage must contain from 0 to 20 numbers");
        return getMileage(in);
    }

    @Override
    public String getYear(Scanner in) {
        stringBuilder.delete(0, stringBuilder.length());
        log.info("Enter year of car");
        stringBuilder.append(in.nextLong());
        if (stringBuilder.length() == 4)
            return stringBuilder.toString();
        log.info("Year must contain from 4 numbers");
        return getYear(in);
    }

    @Override
    public String getNumberCar(Scanner in) {
        stringBuilder.delete(0, stringBuilder.length());
        log.info("Enter number of car");
        stringBuilder.append(in.nextLong());
        if (stringBuilder.length() > 5 && stringBuilder.length() < 20)
            return stringBuilder.toString();
        log.info("Vehicle number must contain at least 7 characters");
        return getNumberCar(in);
    }

    @Override
    public String getNameUser(Scanner in) {
        stringBuilder.delete(0, stringBuilder.length());
        log.info("Enter Name");
        stringBuilder.append(in.next());
        if (stringBuilder.length() > 3 && stringBuilder.length() < 20)
            return stringBuilder.toString();
        log.info("Name must contain from 3 to 20 characters");
        return getNameUser(in);
    }

    @Override
    public boolean edit(String fieldName, Scanner in) {
        log.info(fieldName);
        log.info("Enter 1 to skip");
        log.info("Enter 2 to edit");
        stringBuilder.delete(0, stringBuilder.length());
        if (in.next().equalsIgnoreCase("2")) {
            log.info("Enter values");
            stringBuilder.append(in.next());
            return true;
        }
        return false;
    }

    @Override
    public String getDescription(Scanner in) {
        stringBuilder.delete(0, stringBuilder.length());
        log.info("Enter Description");
        stringBuilder.append(in.next());
        if (stringBuilder.length() > 3 && stringBuilder.length() < 50)
            return stringBuilder.toString();
        log.info("Description must contain from 3 to 50 characters");
        return getDescription(in);
    }

    @Override
    public String getEducation(Scanner in) {
        log.info("Enter Education");
        return in.next();
    }

    @Override
    public String getMail(Scanner in) {//recursion!
        try {
            stringBuilder.delete(0, stringBuilder.length());
            log.info("Enter Mail");
            stringBuilder.append(in.next());
            if (this.inEmail(stringBuilder.toString()))
                return stringBuilder.toString();
            log.info("Invalid values, please try again. Format: Leha03377@Hotmail.com");
        } catch (InvalidValuesException e) {
            log.warn("Invalid values entered:{}", e.getLocalizedMessage());
        }
        return this.getMail(in);
    }

    @Override
    public String getHomeAddress(Scanner in) {
        log.info("Enter HomeAddress");
        return in.next();
    }

    @Override
    public String getPassword(Scanner in) {
        stringBuilder.delete(0, stringBuilder.length());
        log.info("Enter password");
        stringBuilder.append(in.next());
        if (stringBuilder.length() > 3 && stringBuilder.length() < 20)
            return stringBuilder.toString();
        log.info("Password must contain from 3 to 20 characters");
        return getPassword(in);

    }
}
