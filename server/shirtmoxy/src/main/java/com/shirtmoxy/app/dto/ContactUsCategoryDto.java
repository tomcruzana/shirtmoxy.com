package com.shirtmoxy.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ContactUsCategoryDto {

	private int id;

	@NotBlank(message = "Contact us category is required")
	@Size(max = 255, message = "Contact us category cannot exceed 255 characters")
	private String name;

	public ContactUsCategoryDto() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
