package com.draicon.signatron.security.exception;

import org.aspectj.lang.JoinPoint;

public class NonAuthorizedCallException extends Exception {


	public NonAuthorizedCallException() {
		super();
	}

	public NonAuthorizedCallException(String message) {
		super(message);
	}

	public NonAuthorizedCallException(String message, Throwable cause) {
		super(message, cause);
	}

	public NonAuthorizedCallException(Throwable cause) {
		super(cause);
	}
	
	public NonAuthorizedCallException(JoinPoint joinPoint) {
		super(joinPoint.toString());
	}

	public NonAuthorizedCallException(String message,JoinPoint joinPoint) {
		super(message+"\n"+joinPoint.toString());
	}
	
}
