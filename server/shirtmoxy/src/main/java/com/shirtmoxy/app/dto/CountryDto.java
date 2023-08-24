package com.shirtmoxy.app.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CountryDto {

	private int id;

	@NotBlank(message = "Code is required")
	@Size(max = 2, message = "Code cannot exceed 2 characters")
	private String code;

	@NotBlank(message = "Name is required")
	@Size(max = 255, message = "Name cannot exceed 255 characters")
	private String name;

	@JsonIgnore
	private List<StateDto> stateList;

	public CountryDto() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<StateDto> getStateList() {
		return stateList;
	}

	public void setStateList(List<StateDto> stateList) {
		this.stateList = stateList;
	}

}
