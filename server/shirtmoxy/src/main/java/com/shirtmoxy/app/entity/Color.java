package com.shirtmoxy.app.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "color")
public class Color {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank(message = "Color is required")
	@Size(max = 255, message = "Color cannot exceed 255 characters")
	@Column(unique = true, nullable = false, length = 255)
	private String name;

	@NotBlank(message = "Red is required")
	@Size(min = 1, max = 3, message = "Red cannot exceed 3 characters")
	@Column(nullable = false, length = 3)
	private String red;

	@NotBlank(message = "Green is required")
	@Size(min = 1, max = 3, message = "Green cannot exceed 3 characters")
	@Column(nullable = false, length = 3)
	private String green;

	@NotBlank(message = "Blue is required")
	@Size(min = 1, max = 3, message = "Blue cannot exceed 3 characters")
	@Column(nullable = false, length = 3)
	private String blue;

	@OneToMany(mappedBy = "color")
	private List<Product> productList;

	public Color() {
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

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

}
