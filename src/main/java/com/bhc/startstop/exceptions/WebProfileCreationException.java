package com.bhc.startstop.exceptions;

public class WebProfileCreationException extends Exception {

	private static final long serialVersionUID = 2344558038299440208L;

	public WebProfileCreationException() {}
	
	public WebProfileCreationException(String message) {
		super(message);
	}
	
	public WebProfileCreationException(Exception e) {
		super(e);
	}

}
