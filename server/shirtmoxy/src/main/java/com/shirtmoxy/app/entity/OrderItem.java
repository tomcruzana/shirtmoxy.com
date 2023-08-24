package com.shirtmoxy.app.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "order_item")
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotEmpty(message = "Image URL tracking number is required")
	@Size(max = 255, message = "Image URL tracking number cannot exceed 255 characters")
	@Column(name = "image_url", nullable = false, length = 255)
	private String imageUrl;

	@Digits(integer = 19, fraction = 2, message = "Unit price must have a maximum of 19 digits, including 2 decimal places")
	@DecimalMin(value = "0.00", inclusive = true, message = "Unit price must be greater than or equal to 0.00")
	@Column(name = "unit_price", nullable = false, columnDefinition = "DECIMAL(19,2)")
	private BigDecimal unitPrice;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "product_id")
	private Long productId;

	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;

	public OrderItem() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}
