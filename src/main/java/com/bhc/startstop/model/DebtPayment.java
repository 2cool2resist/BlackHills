package com.bhc.startstop.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class DebtPayment implements Serializable {

	private static final long serialVersionUID = -1917924295722129426L;

	public static final String SOURCE_EFT = "eft";
	
	private Long confirmationNumber;
	private BigDecimal paymentAmount;
	
	public DebtPayment() {}
	
	public DebtPayment(Long confirmationNumber, BigDecimal amount) {
		this.confirmationNumber = confirmationNumber;
		this.paymentAmount = amount;
	}

	public Long getConfirmationNumber() {
		return confirmationNumber;
	}

	public void setConfirmationNumber(Long confirmationNumber) {
		this.confirmationNumber = confirmationNumber;
	}

	public BigDecimal getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	
}