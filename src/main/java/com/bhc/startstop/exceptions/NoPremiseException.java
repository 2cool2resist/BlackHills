package com.bhc.startstop.exceptions;

/**
 * NoPremiseException indicates that a premise list is not set when we expect it to be
 * It should never happen....
 * @author bblom
 *
 */
public class NoPremiseException extends Exception {

	private static final long serialVersionUID = -1387899081740338897L;

	public NoPremiseException() {
		super();
	}
	
	public NoPremiseException(Exception e) {
		super(e);
	}

	public NoPremiseException(String message) {
		super(message);
	}
}
