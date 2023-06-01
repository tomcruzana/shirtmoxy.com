package com.shirtmoxy.app.dto;

import java.util.List;

import jakarta.validation.constraints.Min;

public class VariantDto {

	private int id;

	@Min(value = 0, message = "Inventory count must be greater than or equal to 0")
	private int inventoryCount;

	private ColorDto color;

	private SizeDto size;

	private GenderDto gender;

	private MaterialDto material;

	private List<ProductDto> productList;

	public VariantDto() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getInventoryCount() {
		return inventoryCount;
	}

	public void setInventoryCount(int inventoryCount) {
		this.inventoryCount = inventoryCount;
	}

	public ColorDto getColor() {
		return color;
	}

	public void setColor(ColorDto color) {
		this.color = color;
	}

	public SizeDto getSize() {
		return size;
	}

	public void setSize(SizeDto size) {
		this.size = size;
	}

	public GenderDto getGender() {
		return gender;
	}

	public void setGender(GenderDto gender) {
		this.gender = gender;
	}

	public MaterialDto getMaterial() {
		return material;
	}

	public void setMaterial(MaterialDto material) {
		this.material = material;
	}

	public List<ProductDto> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductDto> productList) {
		this.productList = productList;
	}

}
