package com.bhc.startstop.exceptions;

/**
 * NoPremiseException indicates that a premise list is not set when we expect it to be
 * It should never happen....
 * @author bblom
 *
 */
public class NoServicePointException extends Exception {

	private static final long serialVersionUID = 7618048888110555484L;

	public NoServicePointException() {
		super();
	}
	
	public NoServicePointException(Exception e) {
		super(e);
	}

	public NoServicePointException(String message) {
		super(message);
	}
}
