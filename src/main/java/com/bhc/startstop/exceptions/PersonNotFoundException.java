package com.bhc.startstop.exceptions;

public class PersonNotFoundException extends Exception {

	private static final long serialVersionUID = 2126029052683693735L;

	public PersonNotFoundException() {}
	
	public PersonNotFoundException(String message) {
		super(message);
	}
	
	public PersonNotFoundException(Exception e) {
		super(e);
	}

}
