package com.shirtmoxy.app.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "country")
public class Country {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank(message = "Code is required")
	@Column(nullable = false, length = 2)
	private String code;

	@NotBlank(message = "Name is required")
	@Column(nullable = false, length = 255)
	private String name;

	@OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<State> stateList;

	public Country() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<State> getStateList() {
		return stateList;
	}

	public void setStateList(List<State> stateList) {
		this.stateList = stateList;
	}

}
