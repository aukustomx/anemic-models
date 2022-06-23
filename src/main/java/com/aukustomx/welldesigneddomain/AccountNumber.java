package com.aukustomx.welldesigneddomain;

import static java.util.Objects.isNull;

public class AccountNumber {

    private String number;

    private AccountNumber(String number) {
        this.number = number;
    }

    public static AccountNumber of(String number) {

        //Here we can put validations
        //The number lengh, the content, not numbers, not null, etc.
        valid(number);
        return new AccountNumber(number);
    }

    private static void valid(String number) {
        //Let's start with the name. What validation we need to perform: not null, not empty,
        // at least two word separated by a space, not numbers, etc.
        if (isNull(number)) {
            //log something
            throw new IllegalArgumentException("The account number can't be null");
        }

        if (number.isBlank()) {
            //log something
            throw new IllegalArgumentException("The account number can't be empty");
        }

        if (!number.matches("[0-9]{11}")) {
            //log something
            throw new IllegalArgumentException("The account number must be eleven numbers");
        }

        //Etc, etc, etc. Validate here any other rule on name and other params
    }
}
