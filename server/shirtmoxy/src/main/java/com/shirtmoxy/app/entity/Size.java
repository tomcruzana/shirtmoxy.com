package com.shirtmoxy.app.entity;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "size")
public class Size {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotEmpty(message = "Type is required and must not be empty")
	@Column(length = 3, nullable = false)
	private String type;

	@Digits(integer = 3, fraction = 1, message = "Length must have a maximum of 8 digits, including 2 decimal places")
	@DecimalMin(value = "0.0", inclusive = true, message = "Length must be greater than or equal to 0.0")
	@DecimalMax(value = "999.9", inclusive = true, message = "Length must be less than or equal to 999.9")
	@Column(nullable = false, columnDefinition = "DECIMAL(4,1)")
	private BigDecimal length;

	@Digits(integer = 3, fraction = 1, message = "Width must have a maximum of 8 digits, including 2 decimal places")
	@DecimalMin(value = "0.0", inclusive = true, message = "Width must be greater than or equal to 0.0")
	@DecimalMax(value = "999.9", inclusive = true, message = "Width must be less than or equal to 999.9")
	@Column(nullable = false, columnDefinition = "DECIMAL(4,1)")
	private BigDecimal width;;

	@OneToMany(mappedBy = "size", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Variant> variantList;

	public Size() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getLength() {
		return length;
	}

	public void setLength(BigDecimal length) {
		this.length = length;
	}

	public BigDecimal getWidth() {
		return width;
	}

	public void setWidth(BigDecimal width) {
		this.width = width;
	}

	public List<Variant> getVariantList() {
		return variantList;
	}

	public void setVariantList(List<Variant> variantList) {
		this.variantList = variantList;
	}

}
