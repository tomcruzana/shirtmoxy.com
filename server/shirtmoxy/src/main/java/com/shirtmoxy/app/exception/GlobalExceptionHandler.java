package com.shirtmoxy.app.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * This global exception handler provides a standard way of handling exceptions
 * throughout the application. In addition, it considerably reduces the amount
 * of code written for exception handling.
 * 
 * The {@code @ControllerAdvice} annotation in Line 1 consolidates multiple
 * {@code @ExceptionHandler} into a single, global exception handling component.
 *
 * The {@code @Value} annotation injects exception messages specified in the
 * application.properties file into the fields.
 */

@ControllerAdvice
public class GlobalExceptionHandler {
	@Value(value = "${API.EMAIL_CREATE_ERROR}")
	private String emailCreateError;

	@Value(value = "${DATABASE.CONNECTION_ERROR}")
	private String databaseConnectionError;

	/**
	 * Doc:
	 * https://docs.spring.io/spring-framework/docs/2.0.x/javadoc-api/org/springframework/dao/DataIntegrityViolationException.html
	 **/
	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ResponseEntity<String> emailAlreadyExists(DataIntegrityViolationException ex) {
		return new ResponseEntity<>(emailCreateError, HttpStatus.CONFLICT);
	}
}