package com.cg.payroll.exceptions;

@SuppressWarnings("serial")
public class AssociateDetailsNotFoundException extends Exception {

	public AssociateDetailsNotFoundException() {
		super();
	}

	public AssociateDetailsNotFoundException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AssociateDetailsNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public AssociateDetailsNotFoundException(String message) {
		super(message);
	}

	public AssociateDetailsNotFoundException(Throwable cause) {
		super(cause);
	}
	
}
