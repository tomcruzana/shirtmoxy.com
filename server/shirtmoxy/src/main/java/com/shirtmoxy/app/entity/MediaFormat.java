package com.shirtmoxy.app.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "media_format")
public class MediaFormat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
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
