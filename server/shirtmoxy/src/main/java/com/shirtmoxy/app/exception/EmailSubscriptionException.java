package com.shirtmoxy.app.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class EmailSubscriptionException extends DataIntegrityViolationException {
	private String message;

	public EmailSubscriptionException(String message) {
		super(message);
		this.message = message;
	}

}
