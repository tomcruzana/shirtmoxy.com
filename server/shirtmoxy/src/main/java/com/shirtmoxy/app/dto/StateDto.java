package com.shirtmoxy.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class StateDto {

	private int id;

	@NotBlank(message = "Name is required")
	@Size(max = 255, message = "Name cannot exceed 255 characters")
	private String name;


	private CountryDto countryDto;

	public StateDto() {

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

	public CountryDto getCountry() {
		return countryDto;
	}

	public void setCountry(CountryDto country) {
		this.countryDto = country;
	}

}
