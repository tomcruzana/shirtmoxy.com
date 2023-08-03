package com.shirtmoxy.app.utils.helper.converter;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.shirtmoxy.app.dto.CategoryDto;
import com.shirtmoxy.app.entity.Category;

@Component
@Qualifier("CategoryConverter")
public class CategoryConverter implements ObjectConverter<CategoryDto, Category> {

	@Override
	public CategoryDto convertToDTO(Category entity) {
		CategoryDto dto = new CategoryDto();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		return dto;
	}

	@Override
	public Category convertToEntity(CategoryDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
