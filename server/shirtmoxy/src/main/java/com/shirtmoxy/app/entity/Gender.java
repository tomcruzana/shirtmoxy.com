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
@Table(name = "gender")
public class Gender {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message = "Type is required and must not be empty")
	@Size(min = 1, max = 12, message = "Type must have a length of 1")
    @Column(unique = true, nullable = false, length = 1)
	private String type;

	@OneToMany(mappedBy = "gender", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Variant> variantList;

	public Gender() {
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

	public List<Variant> getVariantList() {
		return variantList;
	}

	public void setVariantList(List<Variant> variantList) {
		this.variantList = variantList;
	}

}
