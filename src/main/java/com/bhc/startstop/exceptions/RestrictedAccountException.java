package com.bhc.startstop.exceptions;

/**
 * Indicates a CIS account is restricted from use on the web
 * thrown by accountAuthenticate when CIS returns the account as restricted
 *
 */
public class RestrictedAccountException extends Exception {


	private static final long serialVersionUID = -8303031782196147112L;

	public RestrictedAccountException() {
		super();
	}

	public RestrictedAccountException(String message) {
		super(message);
	}
	
	public RestrictedAccountException(Exception e) {
		super(e);
	}
	
}
