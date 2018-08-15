package com.bhc.startstop.exceptions;

/**
 * PremiseTheftException maps to an error thrown by premSpAuth
 * 
 * @author bblom
 *
 */
public class PremiseTheftException extends WebServiceException {

	private static final long serialVersionUID = -586743554524421138L;

	public PremiseTheftException() {
        super();
    }

    public PremiseTheftException(Exception e) {
        super(e);
    }

    public PremiseTheftException(String message) {
        super(message);
    }
}
