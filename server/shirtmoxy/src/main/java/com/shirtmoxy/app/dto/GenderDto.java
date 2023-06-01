package com.shirtmoxy.app.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class GenderDto {

	private int id;

	@NotEmpty(message = "Type is required and must not be empty")
	@Size(min = 1, max = 1, message = "Type must have a length of 1")
	private char type;

	public GenderDto() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}

}

