package com.bhc.startstop.exceptions;

/**
 * Usually indicates a mismatch in LegalOperatingEntity
 *
 */
public class InvalidAccountException extends Exception {

    private static final long serialVersionUID = -6728284849562352562L;

    public InvalidAccountException() {
        super();
    }

    public InvalidAccountException(String message) {
        super(message);
    }

    public InvalidAccountException(Exception e) {
        super(e);
    }
}
