package com.shirtmoxy.app.exception;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Here's an explanation of each element:
 * 
 * "errors": The top-level key representing an array of error objects. It allows
 * for multiple errors to be returned if needed.
 * 
 * "status": The HTTP status code or a custom error status code indicating the
 * nature of the error. Examples include "400" for Bad Request or "404" for Not
 * Found.
 * 
 * "code": A unique error code or identifier for the specific error. This can be
 * useful for programmatically identifying and handling different types of
 * errors.
 * 
 * "title": A brief, human-readable summary or title for the error. It provides
 * a concise description of the error type.
 * 
 * "detail": A detailed error message that provides additional information or
 * context about the specific error. It can include specific details about what
 * caused the error or how to resolve it.
 * 
 **/
public class ErrorResponse {
	
	@JsonProperty("errors")
	private List<ErrorInfo> errors;

	public ErrorResponse() {
		this.errors = new ArrayList<>();
	}

	public void addError(ErrorInfo errorInfo) {
		this.errors.add(errorInfo);
	}

	// Getters and setters

	public static class ErrorInfo {
		private String status;
		private String code;
		private String title;
		private String detail;

		public ErrorInfo() {
		}

		public ErrorInfo(String status, String code, String title, String detail) {
			this.status = status;
			this.code = code;
			this.title = title;
			this.detail = detail;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getDetail() {
			return detail;
		}

		public void setDetail(String detail) {
			this.detail = detail;
		}

		@Override
		public String toString() {
			return "ErrorInfo [status=" + status + ", code=" + code + ", title=" + title + ", detail=" + detail + "]";
		}

	}

	@Override
	public String toString() {
		return "ErrorResponse [errors=" + errors + "]";
	}

}
