package com.shirtmoxy.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.shirtmoxy.app.dto.CategoryDto;
import com.shirtmoxy.app.entity.Category;
import com.shirtmoxy.app.exception.CategoryException;
import com.shirtmoxy.app.repository.CategoryRepository;
import com.shirtmoxy.app.utils.helper.converter.ObjectConverter;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepo;
	
	@Autowired
    @Qualifier("CategoryConverter")
	private ObjectConverter<CategoryDto, Category> categoryConverter;

	@Override
	public List<CategoryDto> readAllCategories() throws CategoryException {
		// Create a Sort object to sort by id in ascending order
        Sort sortById = Sort.by(Sort.Direction.ASC, "id");
		
		List<Category> categoryList = categoryRepo.findAll(sortById);

		// throw category exception if no categories found
	    if (categoryList.isEmpty()) {
	        throw new CategoryException("No categories found");
	    }
	    
		List<CategoryDto> categoryDtoList = new ArrayList<>();
		for (Category category : categoryList) {
			CategoryDto categoryDto = categoryConverter.convertToDTO(category);
			categoryDtoList.add(categoryDto);
		}

		return categoryDtoList;
	}

}
