package com.shirtmoxy.app.service;

import java.util.List;

import com.shirtmoxy.app.dto.CategoryDto;
import com.shirtmoxy.app.exception.CategoryException;

public interface CategoryService {
	public List<CategoryDto> readAllCategories() throws CategoryException;
}
