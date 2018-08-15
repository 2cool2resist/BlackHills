package com.bhc.startstop.exceptions;

/**
 * UserAccountLookupException is used when we are unable to lookup a user account for the user.
 * Since we are grabbing the username from the security principal, this error
 * should never occur - we should only populate the security principal with a 
 * legit user - this exception isn't explicitly handled.  We are going to extend
 * RuntimeException so that it is handled by the default error handler set up
 * in web.xml
 * @author bblom
 *
 */
public class UserAccountLookupException extends RuntimeException {

	private static final long serialVersionUID = -1898731348412735676L;

	public UserAccountLookupException() {
		super();
	}

	public UserAccountLookupException(String message) {
		super(message);
	}
}
