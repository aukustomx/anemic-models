package com.aukustomx.richmodels;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum AccountType {

    CHECK("Bank checks emissions", "PFAE", "Companies"),
    SAVING("Client saving account", "PF", "People"),
    CHILDREN_SAVING("Children can save their money in this type of account", "No regime", "People");

    private final String description;

    //This field could be a complex object or an enum by itself
    private final String taxRegime;

    //This field could be a complex object or an enum by itself
    private final String segment;

    AccountType(String description, String taxRegime, String segment) {
        this.description = description;
        this.taxRegime = taxRegime;
        this.segment = segment;
    }

    public String getDescription() {
        return description;
    }

    public String getTaxRegime() {
        return taxRegime;
    }

    public String getSegment() {
        return segment;
    }

    public static List<AccountType> bySegment(String segment) {
        return Arrays.stream(values())
                .filter(accountType -> accountType.getSegment().equals(segment))
                .collect(Collectors.toList());
    }
}
