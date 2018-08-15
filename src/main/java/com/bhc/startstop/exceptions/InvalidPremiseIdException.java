package com.bhc.startstop.exceptions;
/**
 * Invalid PremiseId is used when Premise Id is less than or equal to 0
 * @author detallur
 *
 */

public class InvalidPremiseIdException extends Exception {
	
	public InvalidPremiseIdException() {
		super();
	}
	
	public InvalidPremiseIdException(Exception e) {
		super(e);
	}
	
	public InvalidPremiseIdException(String message) {
		super(message);
	}
}