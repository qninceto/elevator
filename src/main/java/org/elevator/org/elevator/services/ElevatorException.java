package org.elevator.org.elevator.services;

public class ElevatorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2367218713235938859L;

	public ElevatorException() {
		super();
	}

	public ElevatorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ElevatorException(String message, Throwable cause) {
		super(message, cause);
	}

	public ElevatorException(String message) {
		super(message);
	}

	public ElevatorException(Throwable cause) {
		super(cause);
	}

}
