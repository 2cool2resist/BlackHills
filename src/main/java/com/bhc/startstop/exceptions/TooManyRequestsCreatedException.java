package com.bhc.startstop.exceptions;

public class TooManyRequestsCreatedException extends Exception {

	private static final long serialVersionUID = 6869570369784476818L;

	public TooManyRequestsCreatedException() {
		super();
	}

	public TooManyRequestsCreatedException(String message) {
		super(message);
	}
}
