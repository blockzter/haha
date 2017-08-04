package org.blockzter.mqservice.exceptions;

/**
 * Created by blockm on 9/10/16.
 *
 */
public class PropertyNotFoundException extends Exception {
	public PropertyNotFoundException() {
		super();
	}

	public PropertyNotFoundException(String message) {
		super(message);
	}

	public PropertyNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public PropertyNotFoundException(Throwable cause) {
		super(cause);
	}

	protected PropertyNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
