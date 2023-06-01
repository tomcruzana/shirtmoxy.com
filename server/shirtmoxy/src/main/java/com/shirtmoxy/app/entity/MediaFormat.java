package com.shirtmoxy.app.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "media_format")
public class MediaFormat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message = "Type is required and must not be empty")
    @Size(max = 50, message = "Type cannot exceed 50 characters")
    @Column(unique = true, nullable = false, length = 50)
	private String type;

	@OneToMany(mappedBy = "mediaFormat", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ProductMedia> ProductMediaList;

	public MediaFormat() {
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

	public List<ProductMedia> getProductMediaList() {
		return ProductMediaList;
	}

	public void setProductMediaList(List<ProductMedia> productMediaList) {
		ProductMediaList = productMediaList;
	}

}
