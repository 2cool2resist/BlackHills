package com.bhc.startstop.user.exceptions;

public class InvalidCisAccountException extends RuntimeException {

    private static final long serialVersionUID = -1214676370164813467L;

    public InvalidCisAccountException() {

        super();
    }

    public InvalidCisAccountException(String message) {

        super(message);
    }

}
