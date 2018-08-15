package com.bhc.startstop.exceptions;

/**
 * NoServiceAgreementsException maps to an error thrown by premSpAuth
 * 
 * @author bblom
 *
 */
public class NoServiceAgreementsException extends WebServiceException {

    private static final long serialVersionUID = 2786071153173562933L;

    public NoServiceAgreementsException() {
        super();
    }

    public NoServiceAgreementsException(Exception e) {
        super(e);
    }

    public NoServiceAgreementsException(String message) {
        super(message);
    }
}
