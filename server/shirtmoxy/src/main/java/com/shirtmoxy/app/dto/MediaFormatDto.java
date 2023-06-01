package com.shirtmoxy.app.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class MediaFormatDto {

	private int id;

	@NotEmpty(message = "Type is required and must not be empty")
	@Size(max = 50, message = "Type cannot exceed 50 characters")
	private String type;

	public MediaFormatDto() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
