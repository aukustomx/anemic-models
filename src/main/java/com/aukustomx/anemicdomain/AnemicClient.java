package com.aukustomx.anemicdomain;

import java.util.List;

public class AnemicClient {

    private String name;
    private String clientId;
    private String email;
    private List<AnemicAccount> accounts;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<AnemicAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AnemicAccount> accounts) {
        this.accounts = accounts;
    }
}
