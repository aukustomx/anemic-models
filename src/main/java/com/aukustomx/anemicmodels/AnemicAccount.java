package com.aukustomx.anemicmodels;

import java.util.List;

public class AnemicAccount {

    private String accountNumber;
    private String accountType;
    private String creationDate;
    private List<AnemicCard> cards;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public List<AnemicCard> getCards() {
        return cards;
    }

    public void setCards(List<AnemicCard> cards) {
        this.cards = cards;
    }
}
