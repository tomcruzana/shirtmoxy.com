package com.shirtmoxy.app.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ColorDto {

	private int id;

	@NotBlank(message = "Color is required")
	@Size(max = 255, message = "Color cannot exceed 255 characters")
	private String color;

	private List<VariantDto> variantList;

	public ColorDto() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public List<VariantDto> getVariantList() {
		return variantList;
	}

	public void setVariantList(List<VariantDto> variantList) {
		this.variantList = variantList;
	}

}
