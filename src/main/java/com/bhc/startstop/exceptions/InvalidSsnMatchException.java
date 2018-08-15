package com.bhc.startstop.exceptions;

/**
 * Indicates a name mismatch for a person record found by SSN
 *
 */
public class InvalidSsnMatchException extends Exception {

    private static final long serialVersionUID = 5375378321194549646L;

    public InvalidSsnMatchException() {
        super();
    }

    public InvalidSsnMatchException(String message) {
        super(message);
    }

    public InvalidSsnMatchException(Exception e) {
        super(e);
    }
}
