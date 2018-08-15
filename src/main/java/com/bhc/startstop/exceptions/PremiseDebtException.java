package com.bhc.startstop.exceptions;

/**
 * PremiseDebtException maps to an error thrown by premSpAuth
 * 
 * @author bblom
 *
 */
public class PremiseDebtException extends WebServiceException {

	private static final long serialVersionUID = 7268359980422744061L;

	public PremiseDebtException() {
        super();
    }

    public PremiseDebtException(Exception e) {
        super(e);
    }

    public PremiseDebtException(String message) {
        super(message);
    }
}
