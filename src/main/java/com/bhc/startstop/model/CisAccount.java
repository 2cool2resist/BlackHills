package com.bhc.startstop.model;

import java.io.Serializable;

public class CisAccount implements Serializable {

    private static final long serialVersionUID = -5149269324263745842L;
    private Long accountId;               // populated from accountInfo
    private String loe;                   // populated from accountInfo
    private Boolean restricted;           // populated from accountAuthenticate
    private Boolean residential;          // populated from accountAuthenticate
    private Long primaryPersonId;         // populated form accountPersonInfo
    private String primaryPersonName;
    private AddressComponent billingAddress;
    // private AddressComponent firstServiceAddress;
    private String firstServiceAddress;   // populated from accountServiceAgreements

    public CisAccount() {
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getLoe() {
        return loe;
    }

    public void setLoe(String loe) {
        this.loe = loe;
    }

    public Boolean isRestricted() {
    	return restricted;
    }
    public void setRestricted(Boolean isRestricted) {
    	this.restricted = isRestricted;
    }
    
    public Boolean isResidential() {
        return residential;
    }

    public void setResidential(Boolean residential) {
        this.residential = residential;
    }

    public Long getPrimaryPersonId() {
        return primaryPersonId;
    }

    public void setPrimaryPersonId(Long primaryPersonId) {
        this.primaryPersonId = primaryPersonId;
    }

    public void setPrimaryPersonId(String primaryPersonId) {
        this.primaryPersonId = Long.valueOf(primaryPersonId);
    }

    public String getPrimaryPersonName() {
        return primaryPersonName;
    }

    public void setPrimaryPersonName(String primaryPersonName) {
        this.primaryPersonName = primaryPersonName;
    }

    public AddressComponent getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(AddressComponent billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getFirstServiceAddress() {
        return firstServiceAddress;
    }

    public void setFirstServiceAddress(String firstServiceAddress) {
        this.firstServiceAddress = firstServiceAddress;
    }

}