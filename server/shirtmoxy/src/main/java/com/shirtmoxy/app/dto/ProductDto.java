package com.shirtmoxy.app.dto;

import java.math.BigDecimal;
import java.util.Set;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProductDto {

	private int id;

	@NotEmpty(message = "SKU is required and must not be empty")
	@Size(max = 255, message = "SKU cannot exceed 255 characters")
	private String sku;

	private Set<ProductMediaDto> productMediaSet;

	private int categoryId;

	@NotEmpty(message = "Name is required and must not be empty")
	@Size(max = 255, message = "Name cannot exceed 255 characters")
	private String name;

	@NotEmpty(message = "Description is required and must not be empty")
	@Size(max = 255, message = "Description cannot exceed 255 characters")
	private String description;

	@Size(max = 255, message = "Manufacturer cannot exceed 255 characters")
	private String manufacturer;

	@DecimalMax(value = "999999.99", inclusive = true, message = "Weight must be less than or equal to 999999.99")
	private BigDecimal weight;

	@DecimalMax(value = "999999.99", inclusive = true, message = "Price must be less than or equal to 999999.99")
	private BigDecimal price;

	@DecimalMax(value = "999999.99", inclusive = true, message = "Tax must be less than or equal to 999999.99")
	private BigDecimal tax;

	private int variantId;

	private int barcodeId;

	@NotNull(message = "isActive must not be null")
	private boolean isActive;

	public ProductDto() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public Set<ProductMediaDto> getProductMediaSet() {
		return productMediaSet;
	}

	public void setProductMediaSet(Set<ProductMediaDto> productMediaSet) {
		this.productMediaSet = productMediaSet;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getTax() {
		return tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	public int getVariantId() {
		return variantId;
	}

	public void setVariantId(int variantId) {
		this.variantId = variantId;
	}

	public int getBarcodeId() {
		return barcodeId;
	}

	public void setBarcodeId(int barcodeId) {
		this.barcodeId = barcodeId;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
