package com.bhc.startstop.exceptions;

/**
 * InvalidTimeslotException is used when a click timeslot is returned from CIS that doesn't map to AppointmentTimeslots
 * It should never happen....
 * @author bblom
 *
 */
public class InvalidTimeslotException extends Exception {

	private static final long serialVersionUID = 4748496002787826818L;

	public InvalidTimeslotException() {
		super();
	}
	
	public InvalidTimeslotException(Exception e) {
		super(e);
	}

	public InvalidTimeslotException(String message) {
		super(message);
	}
}
