package com.shirtmoxy.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class MaterialDto {

	private int id;

	@NotBlank(message = "Material is required")
	@Size(max = 255, message = "Material must not exceed 255 characters")
	private String material;

	public MaterialDto() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

}
