package com.bhc.startstop.exceptions;

/**
 * PremiseCnpException maps to an error thrown by premSpAuth
 * 
 * @author bblom
 *
 */
public class PremiseCnpException extends WebServiceException {

	private static final long serialVersionUID = 7268359980422744061L;

	public PremiseCnpException() {
        super();
    }

    public PremiseCnpException(Exception e) {
        super(e);
    }

    public PremiseCnpException(String message) {
        super(message);
    }
}
