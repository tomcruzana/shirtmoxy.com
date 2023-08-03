package com.shirtmoxy.app.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * This global exception handler provides a standard way of handling exceptions
 * throughout the application. In addition, it considerably reduces the amount
 * of code written for exception handling.
 * 
 * The @ControllerAdvice annotation in Line 1 consolidates
 * multiple @ExceptionHandlers into a single, global exception handling
 * component.
 * 
 * The @Value annotation injects exception messages specified in the
 * application.properties file into the fields.
 **/

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Value(value = "${API.EMAIL_CREATE_ERROR}")
	private String emailCreationError;

	/**
	 * Doc:
	 * https://docs.spring.io/spring-framework/docs/2.0.x/javadoc-api/org/springframework/dao/DataIntegrityViolationException.html
	 * 
	 * @throws JsonProcessingException
	 **/
	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ResponseEntity<String> blogNotFoundException(DataIntegrityViolationException dataIntegrityViolationException)
			throws JsonProcessingException {

		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.addError(new ErrorResponse.ErrorInfo(HttpStatus.CONFLICT.toString(), "DATA_INTEGRITY_ERROR",
				"Data integrity violation", "There was a violation of data integrity."));

		return new ResponseEntity<String>(convertStrToJson(errorResponse), HttpStatus.CONFLICT);
	}

	/* Helper methods */
	public String convertStrToJson(ErrorResponse errorResponse) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		String JsonErrorResponse = objectMapper.writeValueAsString(errorResponse);
		return JsonErrorResponse;
	}
}