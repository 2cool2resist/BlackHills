package com.bhc.startstop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

// Aggregates DebtAccount information
public class DebtDetail implements Serializable {
	private static final long serialVersionUID = -2955114392317474744L;

	private Long accountId;
	private Boolean budget;
	private Boolean pendingPaymentArrangement;
	private BigDecimal utilityBalance; // current + arrears, not write off
	private BigDecimal nonUtilityBalance; // current + arrears, not write offs
	private BigDecimal budgetPayment; 
	//private BigDecimal totalWriteOff;  // unused
	private BigDecimal utilityWriteOff; // used on debt status check, >1000 write off == error
	private BigDecimal utilityRequired; // == util res/nonres: arrears + write off (not retired)
	private BigDecimal currentDeposit; // deposit still owed before this request
	private BigDecimal additionalDeposit;  // deposit for this order
	private BigDecimal depositMonths; // payment arrangment length for this order's deposit
	//private BigDecimal requiredDeposit;
	private List<DebtPayment> payments;
	
	public DebtDetail() {
		budget = false;
		pendingPaymentArrangement = false;
		utilityBalance = BigDecimal.ZERO;
		nonUtilityBalance = BigDecimal.ZERO;
		budgetPayment = BigDecimal.ZERO;
		utilityWriteOff = BigDecimal.ZERO;
		utilityRequired = BigDecimal.ZERO;
		currentDeposit = BigDecimal.ZERO;
		additionalDeposit = BigDecimal.ZERO;
		depositMonths = BigDecimal.ZERO;
		//requiredDeposit = BigDecimal.ZERO;
		payments = new ArrayList<DebtPayment>();
	}
	
	/**
	 * returns utilityBalance + nonUtilityBalance + totalWriteOff
	 * does not include deposit
	 */
			
	public BigDecimal calcAccountTotal() {
		BigDecimal total = BigDecimal.ZERO;
		if(budget && budgetPayment != null) // use budget amount instead of utilityBalance
			total = total.add(budgetPayment);
		else if(utilityBalance != null)
			total = total.add(utilityBalance);

		if(nonUtilityBalance != null)
			total = total.add(nonUtilityBalance);
		BigDecimal payments = calcTotalPayments();
		if(payments != null)
			total = total.subtract(payments);
		return total;
	}
	
	/**
	 * returns utilityRequired + requiredDeposit
	 */
	public BigDecimal calcTotalRequired() {
		BigDecimal total = BigDecimal.ZERO;
		if(pendingPaymentArrangement == null || !pendingPaymentArrangement) {
			if(utilityRequired != null)
				total = total.add(utilityRequired);
			//if(requiredDeposit != null)
				//total = total.add(requiredDeposit);
			BigDecimal payments = calcTotalPayments();
			if(payments != null)
				total = total.subtract(payments);
		}
		return total;
			
	}
	
	public BigDecimal calcTotalPayments() {
		BigDecimal total = BigDecimal.ZERO;
		for(DebtPayment pmt : payments) 
			total = total.add(pmt.getPaymentAmount());
		return total;
	}
	
	public Boolean isSatisfied() {
		if(this.pendingPaymentArrangement == true)
			return true;
		else if(calcTotalRequired().compareTo(BigDecimal.ZERO) == 0)
			return true;
		else if(calcTotalPayments().compareTo(calcTotalRequired()) >= 0)
			return true;
		else
			return false;
	}
	
	
	public Long getAccountId() {
		return accountId;
	}
	
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	
	public Boolean isBudget() {
		return budget;
	}

	public void setBudget(Boolean budget) {
		this.budget = budget;
	}

	public Boolean isPendingPaymentArrangement() {
		return pendingPaymentArrangement;
	}

	public void setPendingPaymentArrangement(Boolean pendingPaymentArrangement) {
		this.pendingPaymentArrangement = pendingPaymentArrangement;
	}

	public BigDecimal getUtilityBalance() {
		return utilityBalance;
	}

	public void setUtilityBalance(BigDecimal utilityBalance) {
		this.utilityBalance = utilityBalance;
	}

	public BigDecimal getNonUtilityBalance() {
		return nonUtilityBalance;
	}

	public void setNonUtilityBalance(BigDecimal nonUtilityBalance) {
		this.nonUtilityBalance = nonUtilityBalance;
	}

	public BigDecimal getBudgetPayment() {
		return budgetPayment;
	}

	public void setBudgetPayment(BigDecimal budgetPayment) {
		this.budgetPayment = budgetPayment;
	}

	public BigDecimal getUtilityWriteOff() {
		return utilityWriteOff;
	}

	public void setUtilityWriteOff(BigDecimal utilityWriteOff) {
		this.utilityWriteOff = utilityWriteOff;
	}

	public BigDecimal getUtilityRequired() {
		return utilityRequired;
	}

	public void setUtilityRequired(BigDecimal utilityRequired) {
		this.utilityRequired = utilityRequired;
	}
	
	public BigDecimal getCurrentDeposit() {
		return currentDeposit;
	}

	public void setCurrentDeposit(BigDecimal currentDeposit) {
		this.currentDeposit = currentDeposit;
	}

	public BigDecimal getAdditionalDeposit() {
		return additionalDeposit;
	}

	public void setAdditionalDeposit(BigDecimal additionalDeposit) {
		this.additionalDeposit = additionalDeposit;
	}

	public BigDecimal getDepositMonths() {
		return depositMonths;
	}

	public void setDepositMonths(BigDecimal depositMonths) {
		this.depositMonths = depositMonths;
	}
	public void setDepositMonths(Integer depositMonths) {
		if(depositMonths != null)
			this.depositMonths = new BigDecimal(depositMonths);
		else
			this.depositMonths = null;
	}

	public List<DebtPayment> getPayments() {
		return this.payments;
	}
}