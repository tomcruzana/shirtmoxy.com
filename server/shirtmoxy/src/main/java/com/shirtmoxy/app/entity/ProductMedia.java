package com.shirtmoxy.app.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "product_media")
public class ProductMedia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotEmpty(message = "Name is required and must not be empty")
	@Size(max = 255, message = "Name must not exceed 255 characters")
	@Column(nullable = false, unique = true, length = 255)
	private String name;
	
	@NotEmpty(message = "URL is required and must not be empty")
    @Size(max = 255, message = "URL must not exceed 255 characters")
    @Column(nullable = false, length = 255)
	private String url;

	@ManyToMany(mappedBy = "productMediaSet")
	private Set<Product> productSet;

	public ProductMedia() {
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Set<Product> getProductSet() {
		return productSet;
	}

	public void setProductSet(Set<Product> productSet) {
		this.productSet = productSet;
	}

}
