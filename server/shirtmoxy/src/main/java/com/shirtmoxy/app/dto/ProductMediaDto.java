package com.shirtmoxy.app.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class ProductMediaDto {

	private int id;

	private MediaFormatDto mediaFormat;

	@NotEmpty(message = "Name is required and must not be empty")
	@Size(max = 255, message = "Name must not exceed 255 characters")
	private String name;

	@NotEmpty(message = "URL is required and must not be empty")
	@Size(max = 255, message = "URL must not exceed 255 characters")
	private String url;

	public ProductMediaDto() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public MediaFormatDto getMediaFormat() {
		return mediaFormat;
	}

	public void setMediaFormat(MediaFormatDto mediaFormat) {
		this.mediaFormat = mediaFormat;
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

}
