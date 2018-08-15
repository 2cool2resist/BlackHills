package com.bhc.startstop.exceptions;

public class DuplicateUserNameException extends Exception {

	private static final long serialVersionUID = 5385788525921204770L;
	
	public DuplicateUserNameException() {}
	
	public DuplicateUserNameException(String message) {
		super(message);
	}
	
	public DuplicateUserNameException(Exception e) {
		super(e);
	}

}
