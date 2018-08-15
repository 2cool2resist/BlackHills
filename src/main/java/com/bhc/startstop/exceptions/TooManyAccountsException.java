package com.bhc.startstop.exceptions;

/**
 * Should never be encountered, included for safety
 *
 */
public class TooManyAccountsException extends RuntimeException {

    private static final long serialVersionUID = 4085053315748977474L;

    public TooManyAccountsException() {
        super();
    }

    public TooManyAccountsException(String message) {
        super(message);
    }

    public TooManyAccountsException(Exception e) {
        super(e);
    }
}
