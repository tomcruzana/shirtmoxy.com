package com.shirtmoxy.app.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class BarcodeDto {

	private int id;

	@NotEmpty(message = "Type is required")
	@Size(max = 128, message = "Type cannot exceed 128 characters")
	private String type;

	public BarcodeDto() {
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
