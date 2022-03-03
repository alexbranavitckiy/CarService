package com.netcracker.menu.validator;

import com.netcracker.menu.errors.InvalidValuesException;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private Scanner in = new Scanner(System.in);
    private final String EMAIL_PATTERN =
            "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                    + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    private final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    public boolean inEmail(String email) throws InvalidValuesException {
        if (email != null && !email.isBlank()) {
            Matcher matcher = pattern.matcher(email);
            return matcher.matches();
        } else throw new InvalidValuesException("Invalid values, please try again");
    }
}
