package com.bhc.startstop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

// Aggregates DebtInfo for a person into a structure that summarizes debt info for the web
public class DebtSnapshot implements Serializable {
	private static final long serialVersionUID = -3420825025685796655L;

	private Long requestId;
	private Long personId;
	private String personName;
	private BigDecimal pendingPayment;
	private BigDecimal totalAccounts;
	private BigDecimal totalDeposit;
	private BigDecimal requiredPayment;
	private List<DebtDetail> accountDetails;
	
	public DebtSnapshot() {
		this.accountDetails = new ArrayList<DebtDetail>();
		pendingPayment = BigDecimal.ZERO;
		totalAccounts = BigDecimal.ZERO;
		totalDeposit = BigDecimal.ZERO;
		requiredPayment = BigDecimal.ZERO;
	}
	
	public BigDecimal calcTotalPayment() {
		BigDecimal total = BigDecimal.ZERO;
		total = total.add(totalAccounts);
		total = total.add(totalDeposit);
		return total;
	}

	public Long getRequestId() {
		return requestId;
	}
	
	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}
	
	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}
	
	public BigDecimal getPendingPayment() {
		return pendingPayment;
	}
	public void setPendingPayment(BigDecimal pendingPayment) {
		this.pendingPayment = pendingPayment;
	}
	public void setPendingPayment(String pendingPayment) {
		this.pendingPayment = new BigDecimal(pendingPayment);
	}

	public BigDecimal getTotalAccounts() {
		return totalAccounts;
	}
	public void setTotalAccounts(BigDecimal total) {
		this.totalAccounts = total;
	}
	public void setTotalAccounts(String total) {
		this.totalAccounts = new BigDecimal(total);
	}
	
	public BigDecimal getTotalDeposit() {
		return totalDeposit;
	}
	public void setTotalDeposit(BigDecimal totalDeposit) {
		this.totalDeposit = totalDeposit;
	}
	public void setTotalDeposit(String totalDeposit) {
		this.totalDeposit = new BigDecimal(totalDeposit);
	}

	public BigDecimal getRequiredPayment() {
		return requiredPayment;
	}
	public void setRequiredPayment(BigDecimal requiredPayment) {
		this.requiredPayment = requiredPayment;
	}
	public void setRequiredPayment(String requiredPayment) {
		this.requiredPayment = new BigDecimal(requiredPayment);
	}

	public List<DebtDetail> getAccountDetails() {
		return accountDetails;
	}

}