package com.aukustomx.anemicdomain;

import java.util.ArrayList;
import java.util.Date;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class AnemicApp {

    //Let's review our anemic domain models

    //Let's suppose that this main is a domain service method.
    public static void main(String[] args) {

        //The next are the params used for client creation
        var clientName = "John Doe";
        var clientId = "C1234";
        var clientEmail = "john.doe@mail.com";
        var accounts = new ArrayList<AnemicAccount>();

        //Instatiate a new Client Object. Pretty easy.
        var clientOne = new AnemicClient();
        clientOne.setName(clientName);
        clientOne.setClientId(clientId);
        clientOne.setEmail(clientEmail);
        clientOne.setAccounts(accounts);

        //save to the storage (db, file, cloud, remote service, etc)

        //Now, what if we need to validate input params
        //Pretty easy too, we validate those params within this main domain service

        //Let's start with the name. What validation we need to perform: not null, not empty,
        // at least two word separated by a space, not numbers, etc.
        if (isNull(clientName)) {
            //log something
            throw new IllegalArgumentException("The name can't be null");
        }

        if (clientName.isBlank()) {
            throw new IllegalArgumentException("The name can't be an empty string");
        }

        if (clientName.matches("[0-9]*")) {
            throw new IllegalArgumentException("The name can't contain numbers");
        }

        //Etc, etc, etc. Validate here any other rule on name and other params

        //Then, create the Cliente instance with validated params
        var clientTwo = new AnemicClient();
        clientTwo.setName(clientName);
        clientTwo.setClientId(clientId);
        clientTwo.setEmail(clientEmail);
        clientTwo.setAccounts(accounts);

        //We feel a relief because our params are validated.

        //save to the storage (db, file, cloud, remote service, etc)

        //Now, we want to assign one account to our client
        //Here we go again
        //We obtain params
        var accountNumber = "123456789";
        var accountType = "cheques";
        var accountCreation = new Date();
        var cards = new ArrayList<AnemicCard>();

        //Let's suppose that we are validating params here
        //bla, bla, bla

        var accountOne = new AnemicAccount();
        accountOne.setAccountNumber(accountNumber);
        accountOne.setAccountType(accountType);
        accountOne.setCreationDate(accountCreation.toString());
        accountOne.setCards(cards);

        //Let's associate the account to our client
        //In our model as it's designed now, we are able (sadly) to set a completely new List
        // or get its current list of accounts and add one.

        //Getting the current client's list of accounts and adding the new account
        clientOne.getAccounts().add(accountOne);

        //Replacing the current client's account with a totally new one
        var totallyNewAccountList = new ArrayList<AnemicAccount>();
        totallyNewAccountList.add(accountOne);
        clientOne.setAccounts(totallyNewAccountList);

        //The previous behavior is so dangerous to our domain.
        //Where is the problem.

        //The problem is that we are leaving our models unprotected.
        //How is it possible that a developer will be able to replace the list of accounts
        // or changing the creation date.

        //There are some object's properties that should never be allowed to change
        // For example the accountNumber or the creationDate.

        //Let's return to our presentation
    }
}
