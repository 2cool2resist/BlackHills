package com.bhc.startstop.webservice.model;

/**
 * Defines debt totals by LOE
 * Debt is grouped by:
 * Residential / Non-Residential
 * Utility / Non-Utility : utility is gas/electric service, non-utility includes service guard, loans, etc
 * Write-Off / Write-Off Retired: write-off is amount still within the statute of limitations, retired amount is
 * what is past the statute
 */
@Deprecated  // CIS will drop this part of the webservice at some point
public class DebtLoe {

    private String loe;
    private Double residentialUtilityArrears;
    private Double residentialNonUtilArrears;

    private Double residentialUtilityWriteOff;
    private Double residentialNonUtilWriteOff;

    private Double residentialUtilityWriteOffRetired;
    private Double residentialNonUtilWriteOffRetired;

    private Double nonResUtilityArrears;
    private Double nonResNonUtilArrears;

    private Double nonResUtilityWriteOff;
    private Double nonResNonUtilWriteOff;

    private Double nonResUtilityWriteOffRetired;
    private Double nonResNonUtilWriteOffRetired;

    public DebtLoe() {
    }
    
    public DebtLoe(DebtLoe copy) {
    	this.loe = copy.getLoe();
    	this.residentialUtilityArrears = copy.getResidentialUtilityArrears();
    	this.residentialNonUtilArrears = copy.getResidentialNonUtilArrears();
    	this.residentialUtilityWriteOff = copy.getResidentialUtilityWriteOff();
    	this.residentialNonUtilWriteOff = copy.getResidentialNonUtilWriteOff();
    	this.residentialUtilityWriteOffRetired = copy.getResidentialUtilityWriteOffRetired();
    	this.residentialNonUtilWriteOffRetired = copy.getResidentialNonUtilWriteOffRetired();

    	this.nonResUtilityArrears = copy.getNonResUtilityArrears();
    	this.nonResNonUtilArrears = copy.getNonResNonUtilArrears();
    	this.nonResUtilityWriteOff = copy.getNonResUtilityWriteOff();
    	this.nonResNonUtilWriteOff = copy.getNonResNonUtilWriteOff();
    	this.nonResUtilityWriteOffRetired = copy.getNonResUtilityWriteOffRetired();
    	this.nonResNonUtilWriteOffRetired = copy.getNonResNonUtilWriteOffRetired();
    }

    public Boolean hasDebt() {
        return (hasResidentialDebt() || hasNonResidentialDebt());
    }

    public Boolean hasResidentialDebt() {
        if ((residentialUtilityArrears != null && residentialUtilityArrears > 0.0)
                || (residentialNonUtilArrears != null && residentialNonUtilArrears > 0.0)
                || (residentialUtilityWriteOff != null && residentialUtilityWriteOff > 0.0)
                || (residentialNonUtilWriteOff != null && residentialNonUtilWriteOff > 0.0)
                || (residentialUtilityWriteOffRetired != null && residentialUtilityWriteOffRetired > 0.0)
                || (residentialNonUtilWriteOffRetired != null && residentialNonUtilWriteOffRetired > 0.0))
            return true;
        else
            return false;
    }

    public Boolean hasNonResidentialDebt() {
        if ((nonResUtilityArrears != null && nonResUtilityArrears > 0.0)
                || (nonResNonUtilArrears != null && nonResNonUtilArrears > 0.0)
                || (nonResUtilityWriteOff != null && nonResUtilityWriteOff > 0.0)
                || (nonResNonUtilWriteOff != null && nonResNonUtilWriteOff > 0.0)
                || (nonResUtilityWriteOffRetired != null && nonResUtilityWriteOffRetired > 0.0)
                || (nonResNonUtilWriteOffRetired != null && nonResNonUtilWriteOffRetired > 0.0))
            return true;
        else
            return false;
    }

    public Boolean hasResidentialUtilityDebt() {
        if ((residentialUtilityArrears != null && residentialUtilityArrears > 0.0)
                || (residentialUtilityWriteOff != null && residentialUtilityWriteOff > 0.0)
                || (residentialUtilityWriteOffRetired != null && residentialUtilityWriteOffRetired > 0.0))
            return true;
        else
            return false;
    }

    public Boolean hasNonResidentialUtilityDebt() {
        if ((nonResUtilityArrears != null && nonResUtilityArrears > 0.0)
                || (nonResUtilityWriteOff != null && nonResUtilityWriteOff > 0.0)
                || (nonResUtilityWriteOffRetired != null && nonResUtilityWriteOffRetired > 0.0))
            return true;
        else
            return false;
    }

    public Double getTotalDebt() {

        Double total = 0.0;
        if (residentialUtilityArrears != null)
            total += residentialUtilityArrears;
        if (residentialNonUtilArrears != null)
            total += residentialNonUtilArrears;
        if (residentialUtilityWriteOff != null)
            total += residentialUtilityWriteOff;
        if (residentialNonUtilWriteOff != null)
            total += residentialNonUtilWriteOff;
        if (residentialUtilityWriteOffRetired != null)
            total += residentialUtilityWriteOffRetired;
        if (residentialNonUtilWriteOffRetired != null)
            total += residentialNonUtilWriteOffRetired;

        if (nonResUtilityArrears != null)
            total += nonResUtilityArrears;
        if (nonResNonUtilArrears != null)
            total += nonResNonUtilArrears;
        if (nonResUtilityWriteOff != null)
            total += nonResUtilityWriteOff;
        if (nonResNonUtilWriteOff != null)
            total += nonResNonUtilWriteOff;
        if (nonResUtilityWriteOffRetired != null)
            total += nonResUtilityWriteOffRetired;
        if (nonResNonUtilWriteOffRetired != null)
            total += nonResNonUtilWriteOffRetired;
        return total;
    }

    public String getLoe() {
        return loe;
    }
    public void setLoe(String loe) {
        this.loe = loe;
    }

    public Double getResidentialUtilityArrears() {
        return residentialUtilityArrears;
    }
    public void setResidentialUtilityArrears(Double residentialUtilityArrears) {
        this.residentialUtilityArrears = residentialUtilityArrears;
    }
    public void setResidentialUtilityArrears(String residentialUtilityArrears) throws NumberFormatException {
        this.residentialUtilityArrears = Double.valueOf(residentialUtilityArrears);
    }

    public Double getResidentialNonUtilArrears() {
        return residentialNonUtilArrears;
    }
    public void setResidentialNonUtilArrears(Double residentialNonUtilArrears) {
        this.residentialNonUtilArrears = residentialNonUtilArrears;
    }
    public void setResidentialNonUtilArrears(String residentialNonUtilArrears) throws NumberFormatException {
        this.residentialNonUtilArrears = Double.valueOf(residentialNonUtilArrears);
    }

    public Double getResidentialUtilityWriteOff() {
        return residentialUtilityWriteOff;
    }
    public void setResidentialUtilityWriteOff(Double residentialUtilityWriteOff) {
        this.residentialUtilityWriteOff = residentialUtilityWriteOff;
    }
    public void setResidentialUtilityWriteOff(String residentialUtilityWriteOff) throws NumberFormatException {
        this.residentialUtilityWriteOff = Double.valueOf(residentialUtilityWriteOff);
    }

    public Double getResidentialNonUtilWriteOff() {
        return residentialNonUtilWriteOff;
    }
    public void setResidentialNonUtilWriteOff(Double residentialNonUtilWriteOff) {
        this.residentialNonUtilWriteOff = residentialNonUtilWriteOff;
    }
    public void setResidentialNonUtilWriteOff(String residentialNonUtilWriteOff) throws NumberFormatException {
        this.residentialNonUtilWriteOff = Double.valueOf(residentialNonUtilWriteOff);
    }

    public Double getResidentialUtilityWriteOffRetired() {
        return residentialUtilityWriteOffRetired;
    }
    public void setResidentialUtilityWriteOffRetired(Double residentialUtilityWriteOffRetired) {
        this.residentialUtilityWriteOffRetired = residentialUtilityWriteOffRetired;
    }
    public void setResidentialUtilityWriteOffRetired(String residentialUtilityWriteOffRetired)
            throws NumberFormatException {
        this.residentialUtilityWriteOffRetired = Double.valueOf(residentialUtilityWriteOffRetired);
    }

    public Double getResidentialNonUtilWriteOffRetired() {
        return residentialNonUtilWriteOffRetired;
    }
    public void setResidentialNonUtilWriteOffRetired(Double residentialNonUtilWriteOffRetired) {
        this.residentialNonUtilWriteOffRetired = residentialNonUtilWriteOffRetired;
    }
    public void setResidentialNonUtilWriteOffRetired(String residentialNonUtilWriteOffRetired)
            throws NumberFormatException {
        this.residentialNonUtilWriteOffRetired = Double.valueOf(residentialNonUtilWriteOffRetired);
    }

    public Double getNonResUtilityArrears() {
        return nonResUtilityArrears;
    }
    public void setNonResUtilityArrears(Double nonResUtilityArrears) {
        this.nonResUtilityArrears = nonResUtilityArrears;
    }
    public void setNonResUtilityArrears(String nonResUtilityArrears) throws NumberFormatException {
        this.nonResUtilityArrears = Double.valueOf(nonResUtilityArrears);
    }

    public Double getNonResNonUtilArrears() {
        return nonResNonUtilArrears;
    }
    public void setNonResNonUtilArrears(Double nonResNonUtilArrears) {
        this.nonResNonUtilArrears = nonResNonUtilArrears;
    }
    public void setNonResNonUtilArrears(String nonResNonUtilArrears) throws NumberFormatException {
        this.nonResNonUtilArrears = Double.valueOf(nonResNonUtilArrears);
    }

    public Double getNonResUtilityWriteOff() {
        return nonResUtilityWriteOff;
    }
    public void setNonResUtilityWriteOff(Double nonResUtilityWriteOff) {
        this.nonResUtilityWriteOff = nonResUtilityWriteOff;
    }
    public void setNonResUtilityWriteOff(String nonResUtilityWriteOff) throws NumberFormatException {
        this.nonResUtilityWriteOff = Double.valueOf(nonResUtilityWriteOff);
    }

    public Double getNonResNonUtilWriteOff() {
        return nonResNonUtilWriteOff;
    }
    public void setNonResNonUtilWriteOff(Double nonResNonUtilWriteOff) {
        this.nonResNonUtilWriteOff = nonResNonUtilWriteOff;
    }
    public void setNonResNonUtilWriteOff(String nonResNonUtilWriteOff) throws NumberFormatException {
        this.nonResNonUtilWriteOff = Double.valueOf(nonResNonUtilWriteOff);
    }

    public Double getNonResUtilityWriteOffRetired() {
        return nonResUtilityWriteOffRetired;
    }
    public void setNonResUtilityWriteOffRetired(Double nonResUtilityWriteOffRetired) {
        this.nonResUtilityWriteOffRetired = nonResUtilityWriteOffRetired;
    }
    public void setNonResUtilityWriteOffRetired(String nonResUtilityWriteOffRetired) throws NumberFormatException {
        this.nonResUtilityWriteOffRetired = Double.valueOf(nonResUtilityWriteOffRetired);
    }

    public Double getNonResNonUtilWriteOffRetired() {
        return nonResNonUtilWriteOffRetired;
    }
    public void setNonResNonUtilWriteOffRetired(Double nonResNonUtilWriteOffRetired) {
        this.nonResNonUtilWriteOffRetired = nonResNonUtilWriteOffRetired;
    }
    public void setNonResNonUtilWriteOffRetired(String nonResNonUtilWriteOffRetired) throws NumberFormatException {
        this.nonResNonUtilWriteOffRetired = Double.valueOf(nonResNonUtilWriteOffRetired);
    }

}