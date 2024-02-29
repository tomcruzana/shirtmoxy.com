package com.shirtmoxy.app.dto;

import java.util.List;

public class ProductOverviewDto {
	private String name;
	private List<ColorDto> colorList;

	public ProductOverviewDto() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ColorDto> getColorList() {
		return colorList;
	}

	public void setColorList(List<ColorDto> colorList) {
		this.colorList = colorList;
	}

}
