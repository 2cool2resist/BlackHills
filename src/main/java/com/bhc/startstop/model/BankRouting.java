package com.bhc.startstop.model;

import java.io.Serializable;

public class BankRouting implements Serializable {

    private static final long serialVersionUID = 1L;
    private String bankName;
    private String bankRoutingNbr;

    public String getBankName() {

        return bankName;
    }

    public void setBankName(String bankName) {

        this.bankName = bankName;
    }

    public String getBankRoutingNbr() {

        return bankRoutingNbr;
    }

    public void setBankRoutingNbr(String bankRoutingNbr) {

        this.bankRoutingNbr = bankRoutingNbr;
    }

}
