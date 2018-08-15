package com.bhc.startstop.exceptions;

public class BadPasswordException extends RuntimeException {

    private static final long serialVersionUID = 7186509238722599422L;

    public BadPasswordException() {

        super();
    }

    public BadPasswordException(String message) {
        super(message);
    }
    
    public BadPasswordException(Exception e) {
    	super(e);
    }

}
