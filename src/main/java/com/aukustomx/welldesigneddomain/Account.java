package com.aukustomx.welldesigneddomain;

import com.aukustomx.anemicdomain.AnemicCard;

import java.util.List;

public class Account {

    private AccountNumber accountNumber;
    private AccountType accountType;
    private String creationDate;
    private List<AnemicCard> cards;

    public AccountNumber getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(AccountNumber accountNumber) {
        this.accountNumber = accountNumber;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
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
