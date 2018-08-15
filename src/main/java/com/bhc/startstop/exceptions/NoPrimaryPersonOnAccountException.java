package com.bhc.startstop.exceptions;

/**
 * NoPrimaryPersonOnAccountException is used when an account does not have a primary person
 * It should never happen....
 * @author bblom
 *
 */
public class NoPrimaryPersonOnAccountException extends RuntimeException {

	private static final long serialVersionUID = -5322365956895749522L;

	public NoPrimaryPersonOnAccountException() {
		super();
	}
	
	public NoPrimaryPersonOnAccountException(Exception e) {
		super(e);
	}

	public NoPrimaryPersonOnAccountException(String message) {
		super(message);
	}
}
