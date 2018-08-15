package com.bhc.startstop.exceptions;

/**
 * InvalidScheduleException is used when the user's schedule selection is invalid
 * The most common case is when gas service start is not scheduled after electric service start
 * 
 * @author bblom
 *
 */
public class InvalidScheduleException extends Exception {

    private static final long serialVersionUID = -2711216966439945938L;

    public InvalidScheduleException() {
        super();
    }

    public InvalidScheduleException(Exception e) {
        super(e);
    }

    public InvalidScheduleException(String message) {
        super(message);
    }
}
