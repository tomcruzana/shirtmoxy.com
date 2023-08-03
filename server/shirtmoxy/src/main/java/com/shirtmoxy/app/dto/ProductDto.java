package com.shirtmoxy.app.dto;

import java.math.BigDecimal;
import java.util.Date;
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

	private CategoryDto category;

	private ManufacturerDto manufacturer;

	@NotEmpty(message = "Name is required and must not be empty")
	@Size(max = 255, message = "Name cannot exceed 255 characters")
	private String name;

	@NotEmpty(message = "Description is required and must not be empty")
	@Size(max = 255, message = "Description cannot exceed 255 characters")
	private String description;

	@DecimalMax(value = "999999.99", inclusive = true, message = "Weight must be less than or equal to 999999.99")
	private BigDecimal weight;

	@DecimalMax(value = "999999.99", inclusive = true, message = "Price must be less than or equal to 999999.99")
	private BigDecimal unitPrice;

	private VariantDto variant;

	private BarcodeDto barcode;

	@NotNull(message = "isActive must not be null")
	private boolean isActive;

	private int unitsInStock;

	private Date dateCreated;

	private Date lastUpdated;

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

	public CategoryDto getCategory() {
		return category;
	}

	public void setCategory(CategoryDto category) {
		this.category = category;
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

	public ManufacturerDto getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(ManufacturerDto manufacturer) {
		this.manufacturer = manufacturer;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal price) {
		this.unitPrice = price;
	}

	public VariantDto getVariant() {
		return variant;
	}

	public void setVariant(VariantDto variant) {
		this.variant = variant;
	}

	public BarcodeDto getBarcode() {
		return barcode;
	}

	public void setBarcode(BarcodeDto barcode) {
		this.barcode = barcode;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public int getUnitsInStock() {
		return unitsInStock;
	}

	public void setUnitsInStock(int unitsInStock) {
		this.unitsInStock = unitsInStock;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

}
