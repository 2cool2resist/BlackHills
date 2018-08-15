package com.bhc.startstop.webservice.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DebtAccount {

    private Long accountId;
    private String loe;
    private String debt; // USE is a Y/N flag indicating if debt is owed on account
    private String primary;
    
    private String budget;  // Y/N flag indicating if account is on budget billing
    private String pendPmtArrangement; // Y/N flag indicating if there is a current payment arrangement in place
    
    // orika uses the BigDecimal setters (orika converts to BigDecimal first, then sets)
    private BigDecimal depositCurrentAmountDue;
    private BigDecimal utilityCurrentAmountDue;
    private BigDecimal nonUtilCurrentAmountDue;
    
    private BigDecimal utilityTotalBilled; // current amount due + arrears (no write off) contains actual usage for budget
    private BigDecimal nonUtilTotalBilled; // current amount due + arrears (no write off)

    private BigDecimal residentialUtilityArrears;
    private BigDecimal residentialNonUtilArrears;

    private BigDecimal residentialUtilityWriteOff;
    private BigDecimal residentialNonUtilWriteOff;

    private BigDecimal residentialUtilityWriteOffRetired;
    private BigDecimal residentialNonUtilWriteOffRetired;

    private BigDecimal nonResUtilityArrears;
    private BigDecimal nonResNonUtilArrears;

    private BigDecimal nonResUtilityWriteOff;
    private BigDecimal nonResNonUtilWriteOff;

    private BigDecimal nonResUtilityWriteOffRetired;
    private BigDecimal nonResNonUtilWriteOffRetired;
    private List<DebtServiceAgreement> serviceAgreements;

    public BigDecimal calcUtilityBalance() {
    	BigDecimal total = BigDecimal.ZERO;
    	if(utilityTotalBilled != null)
    		total = total.add(utilityTotalBilled);
    	if(residentialUtilityWriteOff != null)
    		total = total.add(residentialUtilityWriteOff);
    	if(residentialUtilityWriteOffRetired != null)
    		total = total.add(residentialUtilityWriteOffRetired);
    	if(nonResUtilityWriteOff != null)
    		total = total.add(nonResUtilityWriteOff);
    	if(nonResUtilityWriteOffRetired != null)
    		total = total.add(nonResUtilityWriteOffRetired);
    	return total;
    }
    
    public BigDecimal calcNonUtilityBalance() {
    	BigDecimal total = BigDecimal.ZERO;
    	if(nonUtilTotalBilled != null)
    		total = total.add(nonUtilTotalBilled);
    	if(residentialNonUtilWriteOff != null)
    		total = total.add(residentialNonUtilWriteOff);
    	if(residentialNonUtilWriteOffRetired != null)
    		total = total.add(residentialNonUtilWriteOffRetired);
    	if(nonResNonUtilWriteOff != null)
    		total = total.add(nonResNonUtilWriteOff);
    	if(nonResNonUtilWriteOffRetired != null)
    		total = total.add(nonResNonUtilWriteOffRetired);
    	return total;
    }
    
    public BigDecimal calcUtilityRequired(Boolean isResidential) {
    	BigDecimal total = BigDecimal.ZERO;
    	if(isResidential) {
            if(residentialUtilityArrears != null)
            	total = total.add(residentialUtilityArrears);
            if(residentialUtilityWriteOff != null)
            	total = total.add(residentialUtilityWriteOff);
    	}
    	else {
            if(nonResUtilityArrears != null)
            	total = total.add(nonResUtilityArrears);
            if(nonResUtilityWriteOff != null)
            	total = total.add(nonResUtilityWriteOff);
    	}
    	return total;
    }
    
    public DebtAccount() {
        this.serviceAgreements = new ArrayList<DebtServiceAgreement>();
    }
    public DebtAccount(DebtAccount copy) {
    	this.serviceAgreements = new ArrayList<DebtServiceAgreement>();
    	
    	this.accountId = copy.getAccountId();
    	this.loe = copy.getLoe();
    	this.debt = copy.getDebt();
    	this.primary = copy.getPrimary();
    	this.budget = copy.getBudget();
    	this.pendPmtArrangement = copy.getPendPmtArrangement();
    	
    	this.depositCurrentAmountDue = copy.getDepositCurrentAmountDue();
    	this.utilityCurrentAmountDue = copy.getUtilityCurrentAmountDue();
    	this.nonUtilCurrentAmountDue = copy.getNonUtilCurrentAmountDue();
    	
    	this.utilityTotalBilled = copy.getUtilityTotalBilled();
    	this.nonUtilTotalBilled = copy.getNonUtilTotalBilled();
    	
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
    	for(DebtServiceAgreement agreement : copy.getServiceAgreements())
    		this.serviceAgreements.add(new DebtServiceAgreement(agreement));
    }

    public Boolean hasDebt() {
        if (debt.equalsIgnoreCase("Y"))
            return true;
        else
            return false;
    }

    public Boolean isPrimary() {
        if (primary.equalsIgnoreCase("Y"))
            return true;
        else
            return false;
    }
    
    public Boolean isBudget() {
    	if(this.budget.equalsIgnoreCase("Y"))
    		return true;
    	else
    		return false;
    }
    
    public Boolean isPendPaymentArrangement() {
    	if(this.pendPmtArrangement.equalsIgnoreCase("Y"))
    		return true;
    	else
    		return false;
    }

    /*public BigDecimal getTotalDebt() {

        BigDecimal total = 0.0;
        if (residentialUtilityArrears != null)
            total.add(residentialUtilityArrears);
        if (residentialNonUtilArrears != null)
            total.add(residentialNonUtilArrears);
        if (residentialUtilityWriteOff != null)
            total.add(residentialUtilityWriteOff);
        if (residentialNonUtilWriteOff != null)
            total.add(residentialNonUtilWriteOff);
        if (residentialUtilityWriteOffRetired != null)
            total.add(residentialUtilityWriteOffRetired);
        if (residentialNonUtilWriteOffRetired != null)
            total.add(residentialNonUtilWriteOffRetired);

        if (nonResUtilityArrears != null)
            total.add(nonResUtilityArrears);
        if (nonResNonUtilArrears != null)
            total.add(nonResNonUtilArrears);
        if (nonResUtilityWriteOff != null)
            total.add(nonResUtilityWriteOff);
        if (nonResNonUtilWriteOff != null)
            total.add(nonResNonUtilWriteOff);
        if (nonResUtilityWriteOffRetired != null)
            total.add(nonResUtilityWriteOffRetired);
        if (nonResNonUtilWriteOffRetired != null)
            total.add(nonResNonUtilWriteOffRetired);
        return total;
    }*/

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

    public String getDebt() {
        return debt;
    }
    public void setDebt(String debt) {
        this.debt = debt;
    }

    public String getPrimary() {
        return primary;
    }
    public void setPrimary(String primary) {
        this.primary = primary;
    }
    
    public String getBudget() {
    	return budget;
    }
    public void setBudget(String budget) {
    	this.budget = budget;
    }
    
    public String getPendPmtArrangement() {
		return pendPmtArrangement;
	}
	public void setPendPmtArrangement(String pendPmtArrangement) {
		this.pendPmtArrangement = pendPmtArrangement;
	}

	public BigDecimal getDepositCurrentAmountDue() {
		return depositCurrentAmountDue;
	}
	public void setDepositCurrentAmountDue(BigDecimal depositCurrentAmountDue) {
		this.depositCurrentAmountDue = depositCurrentAmountDue;
	}
	public void setDepositCurrentAmountDue(String depositCurrentAmountDue) {
		this.depositCurrentAmountDue = new BigDecimal(depositCurrentAmountDue);
	}

	public BigDecimal getUtilityCurrentAmountDue() {
		return utilityCurrentAmountDue;
	}
	public void setUtilityCurrentAmountDue(BigDecimal utilityCurrentAmountDue) {
		this.utilityCurrentAmountDue = utilityCurrentAmountDue;
	}
	public void setUtilityCurrentAmountDue(String utilityCurrentAmountDue) {
		this.utilityCurrentAmountDue = new BigDecimal(utilityCurrentAmountDue);
	}
	
	public BigDecimal getNonUtilCurrentAmountDue() {
		return nonUtilCurrentAmountDue;
	}
	public void setNonUtilCurrentAmountDue(BigDecimal nonUtilCurrentAmountDue) {
		this.nonUtilCurrentAmountDue = nonUtilCurrentAmountDue;
	}
	public void setNonUtilCurrentAmountDue(String nonUtilCurrentAmountDue) {
		this.nonUtilCurrentAmountDue = new BigDecimal(nonUtilCurrentAmountDue);
	}
	
	public BigDecimal getUtilityTotalBilled() {
		return utilityTotalBilled;
	}
	public void setUtilityTotalBilled(BigDecimal utilityTotalBilled) {
		this.utilityTotalBilled = utilityTotalBilled;
	}
	public void setUtilityTotalBilled(String utilityTotalBilled) {
		this.utilityTotalBilled = new BigDecimal(utilityTotalBilled);
	}

	public BigDecimal getNonUtilTotalBilled() {
		return nonUtilTotalBilled;
	}
	public void setNonUtilTotalBilled(BigDecimal nonUtilTotalBilled) {
		this.nonUtilTotalBilled = nonUtilTotalBilled;
	}
	public void setNonUtilTotalBilled(String nonUtilTotalBilled) {
		this.nonUtilTotalBilled = new BigDecimal(nonUtilTotalBilled);
	}

	public BigDecimal getResidentialUtilityArrears() {
		return residentialUtilityArrears;
	}
	public void setResidentialUtilityArrears(BigDecimal residentialUtilityArrears) {
		this.residentialUtilityArrears = residentialUtilityArrears;
	}
	public void setResidentialUtilityArrears(String residentialUtilityArrears) {
		this.residentialUtilityArrears = new BigDecimal(residentialUtilityArrears);
	}

	public BigDecimal getResidentialNonUtilArrears() {
		return residentialNonUtilArrears;
	}
	public void setResidentialNonUtilArrears(BigDecimal residentialNonUtilArrears) {
		this.residentialNonUtilArrears = residentialNonUtilArrears;
	}
	public void setResidentialNonUtilArrears(String residentialNonUtilArrears) {
		this.residentialNonUtilArrears = new BigDecimal(residentialNonUtilArrears);
	}

	public BigDecimal getResidentialUtilityWriteOff() {
		return residentialUtilityWriteOff;
	}
	public void setResidentialUtilityWriteOff(BigDecimal residentialUtilityWriteOff) {
		this.residentialUtilityWriteOff = residentialUtilityWriteOff;
	}
	public void setResidentialUtilityWriteOff(String residentialUtilityWriteOff) {
		this.residentialUtilityWriteOff = new BigDecimal(residentialUtilityWriteOff);
	}

	public BigDecimal getResidentialNonUtilWriteOff() {
		return residentialNonUtilWriteOff;
	}
	public void setResidentialNonUtilWriteOff(BigDecimal residentialNonUtilWriteOff) {
		this.residentialNonUtilWriteOff = residentialNonUtilWriteOff;
	}
	public void setResidentialNonUtilWriteOff(String residentialNonUtilWriteOff) {
		this.residentialNonUtilWriteOff = new BigDecimal(residentialNonUtilWriteOff);
	}

	public BigDecimal getResidentialUtilityWriteOffRetired() {
		return residentialUtilityWriteOffRetired;
	}
	public void setResidentialUtilityWriteOffRetired(BigDecimal residentialUtilityWriteOffRetired) {
		this.residentialUtilityWriteOffRetired = residentialUtilityWriteOffRetired;
	}
	public void setResidentialUtilityWriteOffRetired(String residentialUtilityWriteOffRetired) {
		this.residentialUtilityWriteOffRetired = new BigDecimal(residentialUtilityWriteOffRetired);
	}

	public BigDecimal getResidentialNonUtilWriteOffRetired() {
		return residentialNonUtilWriteOffRetired;
	}
	public void setResidentialNonUtilWriteOffRetired(BigDecimal residentialNonUtilWriteOffRetired) {
		this.residentialNonUtilWriteOffRetired = residentialNonUtilWriteOffRetired;
	}
	public void setResidentialNonUtilWriteOffRetired(String residentialNonUtilWriteOffRetired) {
		this.residentialNonUtilWriteOffRetired = new BigDecimal(residentialNonUtilWriteOffRetired);
	}

	public BigDecimal getNonResUtilityArrears() {
		return nonResUtilityArrears;
	}
	public void setNonResUtilityArrears(BigDecimal nonResUtilityArrears) {
		this.nonResUtilityArrears = nonResUtilityArrears;
	}
	public void setNonResUtilityArrears(String nonResUtilityArrears) {
		this.nonResUtilityArrears = new BigDecimal(nonResUtilityArrears);
	}

	public BigDecimal getNonResNonUtilArrears() {
		return nonResNonUtilArrears;
	}
	public void setNonResNonUtilArrears(BigDecimal nonResNonUtilArrears) {
		this.nonResNonUtilArrears = nonResNonUtilArrears;
	}
	public void setNonResNonUtilArrears(String nonResNonUtilArrears) {
		this.nonResNonUtilArrears = new BigDecimal(nonResNonUtilArrears);
	}

	public BigDecimal getNonResUtilityWriteOff() {
		return nonResUtilityWriteOff;
	}
	public void setNonResUtilityWriteOff(BigDecimal nonResUtilityWriteOff) {
		this.nonResUtilityWriteOff = nonResUtilityWriteOff;
	}
	public void setNonResUtilityWriteOff(String nonResUtilityWriteOff) {
		this.nonResUtilityWriteOff = new BigDecimal(nonResUtilityWriteOff);
	}

	public BigDecimal getNonResNonUtilWriteOff() {
		return nonResNonUtilWriteOff;
	}
	public void setNonResNonUtilWriteOff(BigDecimal nonResNonUtilWriteOff) {
		this.nonResNonUtilWriteOff = nonResNonUtilWriteOff;
	}
	public void setNonResNonUtilWriteOff(String nonResNonUtilWriteOff) {
		this.nonResNonUtilWriteOff = new BigDecimal(nonResNonUtilWriteOff);
	}

	public BigDecimal getNonResUtilityWriteOffRetired() {
		return nonResUtilityWriteOffRetired;
	}
	public void setNonResUtilityWriteOffRetired(BigDecimal nonResUtilityWriteOffRetired) {
		this.nonResUtilityWriteOffRetired = nonResUtilityWriteOffRetired;
	}
	public void setNonResUtilityWriteOffRetired(String nonResUtilityWriteOffRetired) {
		this.nonResUtilityWriteOffRetired = new BigDecimal(nonResUtilityWriteOffRetired);
	}

	public BigDecimal getNonResNonUtilWriteOffRetired() {
		return nonResNonUtilWriteOffRetired;
	}
	public void setNonResNonUtilWriteOffRetired(BigDecimal nonResNonUtilWriteOffRetired) {
		this.nonResNonUtilWriteOffRetired = nonResNonUtilWriteOffRetired;
	}
	public void setNonResNonUtilWriteOffRetired(String nonResNonUtilWriteOffRetired) {
		this.nonResNonUtilWriteOffRetired = new BigDecimal(nonResNonUtilWriteOffRetired);
	}

    public List<DebtServiceAgreement> getServiceAgreements() {
        return serviceAgreements;
    }
	public void setServiceAgreements(
            List<DebtServiceAgreement> serviceAgreements) {

        if (serviceAgreements == null)
            this.serviceAgreements = new ArrayList<DebtServiceAgreement>();
        this.serviceAgreements.clear();
        this.serviceAgreements.addAll(serviceAgreements);
    }

}