package com.bhc.startstop.model;

import java.io.Serializable;

public class CreatePersonResponse implements Serializable {

    private static final long serialVersionUID = 2421452102235032538L;

    private Long personId;
    private Long accountId;

    public Long getPersonId() {
        return personId;
    }
    public void setPersonId(Long personId) {
        this.personId = personId;
    }
    public void setPersonId(String personId) {
        this.personId = Long.valueOf(personId);
    }

    public Long getAccountId() {
        return accountId;
    }
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
    public void setAccountId(String accountId) {
        this.accountId = Long.valueOf(accountId);
    }

}