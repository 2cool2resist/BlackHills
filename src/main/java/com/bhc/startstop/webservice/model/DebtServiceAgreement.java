package com.bhc.startstop.webservice.model;

import com.bhc.startstop.cis.constants.UtilityTypeCode;

public class DebtServiceAgreement {

    private String utilityType;
    private Double arrears;
    private Double writeOff;
    private Double writeOffRetired;
    
    public DebtServiceAgreement() {}
    
    public DebtServiceAgreement(DebtServiceAgreement copy) {
    	this.utilityType = copy.getUtilityType();
    	this.arrears = copy.getArrears();
    	this.writeOff = copy.getWriteOff();
    	this.writeOffRetired = copy.getWriteOffRetired();
    }

    public String getUtilityType() {
        return utilityType;
    }
    public void setUtilityType(String utilityType) {
        this.utilityType = utilityType;
    }
    public UtilityTypeCode getUtilityTypeCode() {
        return UtilityTypeCode.valueOf(utilityType);
    }

    public Double getArrears() {
        return arrears;
    }
    public void setArrears(Double arrears) {
        this.arrears = arrears;
    }
    public void setArrears(String arrears) throws NumberFormatException {
        this.arrears = Double.valueOf(arrears);
    }

    public Double getWriteOff() {
        return writeOff;
    }
    public void setWriteOff(Double writeOff) {
        this.writeOff = writeOff;
    }
    public void setWriteOff(String writeOff) throws NumberFormatException {
        this.writeOff = Double.valueOf(writeOff);
    }

    public Double getWriteOffRetired() {
        return writeOffRetired;
    }
    public void setWriteOffRetired(Double writeOffRetired) {
        this.writeOffRetired = writeOffRetired;
    }
    public void setWriteOffRetired(String writeOffRetired) throws NumberFormatException {
        this.writeOffRetired = Double.valueOf(writeOffRetired);
    }
}