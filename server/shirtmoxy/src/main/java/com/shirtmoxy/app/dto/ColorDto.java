package com.shirtmoxy.app.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ColorDto {

	private int id;

	@NotBlank(message = "Name is required")
	@Size(max = 255, message = "Name cannot exceed 255 characters")
	private String name;

	@NotBlank(message = "Red is required")
	@Size(min = 1, max = 3, message = "Red cannot exceed 3 characters")
	private String red;

	@NotBlank(message = "Green is required")
	@Size(min = 1, max = 3, message = "Green cannot exceed 3 characters")
	private String green;

	@NotBlank(message = "Blue is required")
	@Size(min = 1, max = 3, message = "Blue cannot exceed 3 characters")
	private String blue;

	private List<ProductDto> productList;

	public ColorDto() {
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

	public String getRed() {
		return red;
	}

	public void setRed(String red) {
		this.red = red;
	}

	public String getGreen() {
		return green;
	}

	public void setGreen(String green) {
		this.green = green;
	}

	public String getBlue() {
		return blue;
	}

	public void setBlue(String blue) {
		this.blue = blue;
	}

	public List<ProductDto> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductDto> productList) {
		this.productList = productList;
	}

}
