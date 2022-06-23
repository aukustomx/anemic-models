# Anemic domain models

## Modeling beyond getters and setters

## Everythig we do is about clients... and the domain
It's always about the client. Everything that we did before, what we are doing now, and everything that we are going to
do in the future, in this industry, is about the client. We and our companies want to the client to be satisfied with
our work and with the goods and services that we offer to them.

Every decision that we make should be made with the client as the objective. In each phase of the SDLC (analysis, design,
coding, tests, etc.), must have clients into account. What kind of experience do we want to offer to them?

But, as the title of this section says, in our industry it's also about the domain. What is the domain? Let's see.

## Domain
The domain is the main topic or subject that you are going to model and solve. If your company is a bank, some example of 
domains are:
- **Notifications** you send to the clients
- Types of **accounts** you offer.
- Investment
- Promotions
- Etcetera

## Let's make everything driven by the domain
If the domain is so important, we need to make all decisions following the domain:
- Abstractions
- Entities
- Main and alternatives flows
- Participants
- Tech stack
- Patterns
- Infrastructure
- Etcetera

Again, we are supposed to model the real world.

### Subdomains
It's posible that inside a domain we can find sub-domains. For example, if we talk about bank cards, we can see Debit
and Credit card. Let's say that, I'm not an expert in bank business, the business rules of both kind of card are very
complex to be managed with just one domain. Then we can say that we have the cards domain and debit and credit 
cards sub-domains. Something like that. Or in an e-retailer company; we can hace Orders, Payment, Shipping, etc.

Once we identify those sub-domains, we can treat each like a separated domain itself and apply the principles that we
have talked about so far.

## A little about DDD
This material is not about DDD. Ok, just a little. So, I want to say few words about it. 
Domain Driven Design or DDD, is an approach or a way of facing the design of complex software solutions. It's important
to notice the word *complex*. This design must take the domain into account, it must be guided by the topic we are
trying to solve.

It sounds pretty obvious, but it doesn't happen this way always.

DDD is a complex topic itself. It has concepts and techniques in charge of business and tech areas. Things like:
- Ubiquitous language: everybody(business and tech) need to talk the same language, use the same terminology
- Bounded contexts: Identify the edges of a domain/sub-domain
- Models: entities, value objects, aggregations, etc.
- Many other concepts

## DDD models are tightly related to OOP
In OOP the king is the object and in DDD models are a crucial part. These two concepts are very related each other.
I could say that they are kinf of the same thing. Using both we want to create a real world abstraction.

But OOP is not just about data, or attribute of the object. OOP takes care of objects' behavior. Think about a car. If
we only design looking at car's attributes, our design will never be able to use the car, that is, to ask it to drive,
or stop or notifying us its mechanical state. We will be able just to ask what his color is, what is his brand, etc.

With this in mind, we could say (the industry say that), OOP is about state and behavior.

## We already create models in some ways
With a little of differences, we use something like the next java package structures to put in out models. We call them
requests, responses, entity, value object, etc. But in any case, we are trying to representate real world's objects.
```java
/project/app/web/model
/project/app/domain/entity
```

## Are the next models familiar to us?
Let's suppose that we want to design a solution to manage the account a bank client can have. I'll show you our first
anemic domain models. I'll use the class name prefix *Anemic* to distinguish those models:

AnemicCard.java
```java
public class AnemicCard {

    private String cardNumber;
    private String cvv;
    private Date expirationDate;
    
    //getters and setter...
}
```
AnemicAccount.java
```java
public class AnemicAccount {

    private String accountNumber;
    private String accountType;
    private String creationDate;
    private List<AnemicCard> cards;

    //getters and setters...
}
```

AnemicClient.java
```java
public class AnemicClient {

    private String name;
    private String clientId;
    private String email;
    private List<AnemicAccount> accounts;
    
    //getters and setters...
}
```

## Why are those models considered anemic?
We can find many problems with that models. 
- Where is the behavior?
- Vulnerable state
- Primitive obsession
- Duplicated code
- Imperative object creation steps
- Imperative code in general

Let's decompose each of it problems one by one. The party is just starting.

## Primitive obssesion
If we are using an Object Oriented Language, why are we so obssesed with using primitives and not rich and complex
objects? 

In our code, let's review the class `AnemicAccount` and its `accountType` field. Why are we using and `String`? Maybe
the account types in the bank are well-defined, that is, it might be a catalog. If it is the problem, someone could say:
let's use a constant class to define all possible values.

It could looks like:
```java
public class Constants {
    
    public static final String accountType1 = "type1";
    public static final String accountType2 = "type2";
    public static final String accountType3 = "type3";
}
```
This solution is worse than the previous one. It spread the state out of the class who is interested with that types,
and, from the Java class design perspective, it's not taking care of things like utility classes, and inheritance.

## Don't be affraid, let's use rich objects
If account types is a fixed catalog we can use a Java `enum`:

With this, we can add attributes and behavior to the `AccountType`, like description, tax regime, etc.

This design gives us the posibility of getting all of the `AccountType`s designed for People and not for companies?
```java
public enum AccountType {

    CHECK("Bank checks emissions", "PFAE", "Companies"),
    SAVING("Client saving account", "PF", "People"),
    CHILDREN_SAVING("For children money saving", "No regime", "People");

    private String description;

    //This field could be a complex object
    // or an enum by itself
    private String taxRegime;

    //This field could be a complex object
    // or an enum by itself
    private String segment;

    AccountType(String description, String taxRegime,
                String segment) {
        this.description = description;
        this.taxRegime = taxRegime;
        this.segment = segment;
    }
    
    //only getters...
    
    //a convenient method to get all types of
    // account intended for a specific segment
    public static List<AccountType> bySegment(String segment) {
        return Arrays.stream(values())
                .filter(accountType -> accountType.getSegment().equals(segment))
                .collect(Collectors.toList());
    }
}
```

Let's think about the need of apply validation to the `accountNumber` at the `Account` object creation time. To achieve 
this in a correct OOP way, we could transform `accountNumber` from a `String` to a rich model/complex object, something
like this:

```java
//This is a value object. It hasn't an identity. It just holds a value. 
public class AccountNumber {

    private final String number;

    private AccountNumber(String number) {
        this.number = number;
    }

    //Using a factory method. It allows us many advantages such as validations, reuse of
    // heavy object creation, etc. It saves us to write the new keyword
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

    //Only accesors. When an accountNumber born, it's supposed to never change.
    public String getNumber() {
        return number;
    }
}
```

Let's see how it looks like creating and validating an AccountNumber usign both anemic vs rich domain models:

```java
import com.aukustomx.welldesigneddomain.AccountNumber;

public class AccountService {

    //Anemic way. All validations logic within domain service
    public Object anemicCreateAccount(String numer) {
        //Validate account number param before we use ot to create an AnemicAccount
        var number = "12343";
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

        //Once we have validated everything is needed to, we proceed to create 
        // our anemic object using our recently validated number.
        var anemicAccount = new AnemicAccount();
        anemicAccount.setAccountNumber(number);
        var accountNumber = AccountNumber.of("ABCD");
        //...
    }

    //Rich domain models way. All validations are within the object itself.
    public Object richCreateAccount() {
        var accountNumber = AccountNumber.of("12343");
        //...
    }
}
```

