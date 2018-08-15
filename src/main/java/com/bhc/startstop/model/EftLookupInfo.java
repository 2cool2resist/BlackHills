package com.bhc.startstop.model;

import java.io.Serializable;

public class EftLookupInfo implements Serializable {

    private static final long serialVersionUID = 748627830075307665L;

    private String customerName;
    private String nameOnBankAccount;
    private String bankRoutingNumber;
    private String bankName;
    private String bankCityState;
    private String bankAccountNumber;
    private String bankAccountLastFour;
    private Long nextBillDate;

    public String getCustomerName() {

        return customerName;
    }

    public void setCustomerName(String customerName) {

        this.customerName = customerName;
    }

    public String getNameOnBankAccount() {

        return nameOnBankAccount;
    }

    public void setNameOnBankAccount(String nameOnBankAccount) {

        this.nameOnBankAccount = nameOnBankAccount;
    }

    public String getBankRoutingNumber() {

        return bankRoutingNumber;
    }

    public void setBankRoutingNumber(String bankRoutingNumber) {

        this.bankRoutingNumber = bankRoutingNumber;
    }

    public String getBankName() {

        return bankName;
    }

    public void setBankName(String bankName) {

        this.bankName = bankName;
    }

    public String getBankCityState() {

        return bankCityState;
    }

    public void setBankCityState(String bankCityState) {

        this.bankCityState = bankCityState;
    }

    public String getBankAccountNumber() {

        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {

        this.bankAccountNumber = bankAccountNumber;
    }

    public String getBankAccountLastFour() {

        return bankAccountLastFour;
    }

    public void setBankAccountLastFour(String bankAccountLastFour) {

        this.bankAccountLastFour = bankAccountLastFour;
    }

    public Long getNextBillDate() {

        return nextBillDate;
    }

    public void setNextBillDate(Long nextBillDate) {

        this.nextBillDate = nextBillDate;
    }

}
