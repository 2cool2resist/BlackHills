package com.bhc.startstop.model;

import java.io.Serializable;
import java.text.DecimalFormat;

import org.apache.commons.lang.StringUtils;

public class EFT implements Serializable {

    private static final long serialVersionUID = 1L;
    private long accountId;
    private String routingNumber;
    private String accountType;
    private String accountNumber;
    private String paymentAmount;
    private String emailAddress;
    private String bankName;
    private String agreement;
    private Double amountDue;
    private String action;
    private String partialAcctNumber;
    private String paymentDate;

    public long getAccountId() {

        return accountId;
    }

    public void setAccountId(long accountId) {

        this.accountId = accountId;
    }

    public String getRoutingNumber() {

        return routingNumber;
    }

    public void setRoutingNumber(String routingNumber) {

        this.routingNumber = routingNumber;
    }

    public String getAccountType() {

        return accountType;
    }

    public void setAccountType(String accountType) {

        this.accountType = accountType;
    }

    public String getAccountNumber() {

        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {

        this.accountNumber = accountNumber;
    }

    public String getPaymentAmount() {

        return paymentAmount;

    }

    public void setPaymentAmount(String paymentAmount) {

        paymentAmount = paymentAmount.replaceAll("[^\\d.-]", "");
        if (StringUtils.isNotEmpty(paymentAmount)) {
            Double amt = Double.parseDouble(paymentAmount);
            this.paymentAmount = getFormattedAmount(amt);
        } else {
            this.paymentAmount = paymentAmount;
        }
    }

    public String getEmailAddress() {

        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {

        this.emailAddress = emailAddress;
    }

    public String getBankName() {

        return bankName;
    }

    public void setBankName(String bankName) {

        this.bankName = bankName;
    }

    public String getAgreement() {

        return agreement;
    }

    public void setAgreement(String agreement) {

        this.agreement = agreement;
    }

    public Double getAmountDue() {

        return amountDue;

    }

    public void setAmountDue(Double amountDue) {

        String amt = getFormattedAmount(amountDue);
        if (StringUtils.isNotEmpty(amt)) {
            this.amountDue = Double.parseDouble(amt);
        } else {
            this.amountDue = amountDue;
        }

    }

    public String getAction() {

        return action;
    }

    public void setAction(String action) {

        this.action = action;
    }

    public String getPartialAcctNumber() {

        if (StringUtils.isNotBlank(accountNumber)) {
            partialAcctNumber = accountNumber.replaceAll(".(?=....)", "\\#");
        }
        return partialAcctNumber;
    }

    public void setPartialAcctNumber(String partialAcctNumber) {

        this.partialAcctNumber = partialAcctNumber;
    }

    public String getFormattedAmount(Double amount) {

        String formatedAmount = null;
        DecimalFormat df = new DecimalFormat("#.00");

        if (amount != null) {
            formatedAmount = df.format(amount);
        }

        return formatedAmount;
    }

    public String getPaymentDate() {

        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {

        this.paymentDate = paymentDate;
    }

}
