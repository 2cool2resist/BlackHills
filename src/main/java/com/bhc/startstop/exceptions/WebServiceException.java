package com.bhc.startstop.exceptions;

/**
 * UserAccountLookupException is used to capture exceptions thrown from webservices
 * The exception should contain the message returned by the webservice
 * @author bblom
 *
 */
public class WebServiceException extends RuntimeException {

	private static final long serialVersionUID = -2454914055191141731L;

	public WebServiceException() {
		super();
	}
	
	public WebServiceException(Exception e) {
		super(e);
	}

	public WebServiceException(String message) {
		super(message);
	}
}
