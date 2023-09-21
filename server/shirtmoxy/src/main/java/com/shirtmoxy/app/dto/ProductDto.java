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
	
	@NotEmpty(message = "Name is required and must not be empty")
	@Size(max = 255, message = "Name cannot exceed 255 characters")
	private String name;
	
	@NotEmpty(message = "Description is required and must not be empty")
	@Size(max = 512, message = "Description cannot exceed 255 characters")
	private String description;
	
	@DecimalMax(value = "999999.99", inclusive = true, message = "Price must be less than or equal to 999999.99")
	private BigDecimal unitPrice;
	
	@DecimalMax(value = "999999.99", inclusive = true, message = "Weight must be less than or equal to 999999.99")
	private BigDecimal weight;
	
	private int unitsInStock;
	
	@NotNull(message = "isActive must not be null")
	private boolean isActive;

	private Date dateCreated;

	private Date lastUpdated;

	private Set<ProductMediaDto> productMediaSet;
	
	private ProductTypeDto productType;

	private CategoryDto category;

	private ManufacturerDto manufacturer;
	
	private GenderDto gender;
	
	private ColorDto color;
	
	private SizeDto size;
	
	private MaterialDto material;

	private BarcodeDto barcode;

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

	public ProductTypeDto getProductType() {
		return productType;
	}

	public void setProductType(ProductTypeDto productType) {
		this.productType = productType;
	}

	public GenderDto getGender() {
		return gender;
	}

	public void setGender(GenderDto gender) {
		this.gender = gender;
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

	public MaterialDto getMaterial() {
		return material;
	}

	public void setMaterial(MaterialDto material) {
		this.material = material;
	}

	@Override
	public String toString() {
		return "ProductDto [productMediaSet=" + productMediaSet + "]";
	}
	
	

}
