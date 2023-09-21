package com.shirtmoxy.app.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotEmpty(message = "SKU is required and must not be empty")
	@Size(max = 255, message = "SKU cannot exceed 255 characters")
	@Column(unique = true, nullable = false, length = 255)
	private String sku;

	@NotEmpty(message = "Name is required and must not be empty")
	@Size(max = 255, message = "Name cannot exceed 255 characters")
	@Column(nullable = false, length = 255)
	private String name;

	@NotEmpty(message = "Description is required and must not be empty")
	@Size(max = 512, message = "Description cannot exceed 255 characters")
	@Column(nullable = false, length = 512)
	private String description;

	@Digits(integer = 8, fraction = 2, message = "Price must have a maximum of 8 digits, including 2 decimal places")
	@DecimalMin(value = "0.00", inclusive = true, message = "Price must be greater than or equal to 0.00")
	@Column(name = "unit_price", nullable = false, columnDefinition = "DECIMAL(10,2)")
	private BigDecimal unitPrice;

	@Digits(integer = 8, fraction = 2, message = "Weight must have a maximum of 8 digits, including 2 decimal places")
	@DecimalMin(value = "0.00", inclusive = true, message = "Weight must be greater than or equal to 0.00")
	@DecimalMax(value = "999999.99", inclusive = true, message = "Weight must be less than or equal to 999999.99")
	@Column(name = "weight", columnDefinition = "DECIMAL(10,2)")
	private BigDecimal weight;

	@Column(name = "units_in_stock")
	private int unitsInStock;

	@NotNull(message = "isActive must not be null")
	@Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
	private boolean isActive;

	@Column(name = "date_created")
	@CreationTimestamp
	private Date dateCreated;

	@Column(name = "last_updated")
	@UpdateTimestamp
	private Date lastUpdated;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "product_and_product_media", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "product_media_id"))
	private Set<ProductMedia> productMediaSet;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_type_id", nullable = false)
	private ProductType productType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "manufacturer_id", nullable = false)
	private Manufacturer manufacturer;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gender_id", nullable = false)
	private Gender gender;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "color_id", nullable = false)
	private Color color;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "size_id", nullable = false)
	private com.shirtmoxy.app.entity.Size size;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "material_id", nullable = false)
	private Material material;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "barcode_id")
	private Barcode barcode;

	public Product() {
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

	public Set<ProductMedia> getProductMediaSet() {
		return productMediaSet;
	}

	public void setProductMediaSet(Set<ProductMedia> productMediaSet) {
		this.productMediaSet = productMediaSet;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
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

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public Barcode getBarcode() {
		return barcode;
	}

	public void setBarcode(Barcode barcode) {
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

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public com.shirtmoxy.app.entity.Size getSize() {
		return size;
	}

	public void setSize(com.shirtmoxy.app.entity.Size size) {
		this.size = size;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

}
