package com.shirtmoxy.app.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shirtmoxy.app.dto.ProductDto;
import com.shirtmoxy.app.dto.ProductSkuDto;
import com.shirtmoxy.app.entity.Category;
import com.shirtmoxy.app.entity.Color;
import com.shirtmoxy.app.entity.Gender;
import com.shirtmoxy.app.entity.Manufacturer;
import com.shirtmoxy.app.entity.Material;
import com.shirtmoxy.app.entity.Product;
import com.shirtmoxy.app.entity.Size;
import com.shirtmoxy.app.exception.ProductException;
import com.shirtmoxy.app.repository.CategoryRepository;
import com.shirtmoxy.app.repository.ColorRepository;
import com.shirtmoxy.app.repository.GenderRepository;
import com.shirtmoxy.app.repository.ManufacturerRepository;
import com.shirtmoxy.app.repository.MaterialRepository;
import com.shirtmoxy.app.repository.ProductRepository;
import com.shirtmoxy.app.repository.SizeRepository;
import com.shirtmoxy.app.utils.helper.converter.ObjectConverter;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	// number of product items per page
	// private final int SEARCH_RESULT_PER_PAGE = 5;

	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private CategoryRepository categoryRepo;

	@Autowired
	private ManufacturerRepository manufacturerRepo;

	@Autowired
	private GenderRepository genderRepo;

	@Autowired
	private ColorRepository colorRepo;

	@Autowired
	private SizeRepository sizeRepo;
	
	@Autowired
	private MaterialRepository materialRepo;

	@Autowired
	@Qualifier("ProductConverter")
	private ObjectConverter<ProductDto, Product> productConverter;

	@Override
	public Page<ProductDto> readFilteredProducts(String productType, String searchQuery, List<String> categories,
			List<String> manufacturers, List<String> genders, List<String> colors, List<String> sizes,
			List<String> materials, int pageNum, int pageSize) throws ProductException {
		// Create a Pageable object for pagination
		Pageable pageable = PageRequest.of(pageNum - 1, pageSize, Sort.by("id"));

		// check if product type is null or empty
		if (productType == null || productType.isEmpty()) {
			throw new ProductException("Product type must be specified.");
		}

		// @TODO: temporary sanitation & validation
		if (searchQuery != null && !searchQuery.isEmpty()) {
			// Trim leading and trailing whitespace
			searchQuery = searchQuery.trim();
			System.out.println("Log: Trimmed searchQuery: |" + searchQuery + "|");
		}

		// Check if category is null or empty
		if (categories == null || categories.isEmpty()) {
			// Fetch all category names from a repository method
			List<Category> categoryList = categoryRepo.findAll();

			// Extract category names from Category objects
			List<String> categoryNames = categoryList.stream().map(Category::getName).collect(Collectors.toList());

			// Assign the categoryNames to the category list
			categories = categoryNames;

			// TEMP LOG
			String result = categories.stream().collect(Collectors.joining(", "));

			System.out.println("CATEGORY WAS DETECTED EMPTY. SO WE ADDED ALL" + result);
		}

		// Check if manufacturers is null or empty
		if (manufacturers == null || manufacturers.isEmpty()) {
			// Fetch all category names from a repository method
			List<Manufacturer> manufacturerList = manufacturerRepo.findAll();

			List<String> manufacturerNames = manufacturerList.stream().map(Manufacturer::getName)
					.collect(Collectors.toList());

			manufacturers = manufacturerNames;

			// TEMP LOG
			String result = manufacturers.stream().collect(Collectors.joining(", "));

			System.out.println("MANUFACTURER WAS DETECTED EMPTY. SO WE ADDED ALL" + result);
		}

		// Check if genders is null or empty
		if (genders == null || genders.isEmpty()) {
			// Fetch all category names from a repository method
			List<Gender> genderList = genderRepo.findAll();

			List<String> genderNames = genderList.stream().map(Gender::getType).collect(Collectors.toList());

			genders = genderNames;

			// TEMP LOG
			String result = genders.stream().collect(Collectors.joining(", "));

			System.out.println("GENDER WAS DETECTED EMPTY. SO WE ADDED ALL" + result);
		}

		// Check if colors is null or empty
		if (colors == null || colors.isEmpty()) {
			// Fetch all category names from a repository method
			List<Color> colorList = colorRepo.findAll();

			List<String> colorNames = colorList.stream().map(Color::getName).collect(Collectors.toList());

			colors = colorNames;

			// TEMP LOG
			String result = colors.stream().collect(Collectors.joining(", "));

			System.out.println("COLOR WAS DETECTED EMPTY. SO WE ADDED ALL" + result);
		}

		// Check if sizes is null or empty
		if (sizes == null || sizes.isEmpty()) {
			// Fetch all category names from a repository method
			List<Size> sizeList = sizeRepo.findAll();

			List<String> sizeNames = sizeList.stream().map(Size::getType).collect(Collectors.toList());

			sizes = sizeNames;

			// TEMP LOG
			String result = sizes.stream().collect(Collectors.joining(", "));

			System.out.println("SIZE WAS DETECTED EMPTY. SO WE ADDED ALL" + result);
		}

		// Check if materials is null or empty
		if (materials == null || materials.isEmpty()) {
			// Fetch all category names from a repository method
			List<Material> materialList = materialRepo.findAll();

			List<String> materialNames = materialList.stream().map(Material::getType).collect(Collectors.toList());

			materials = materialNames;

			// TEMP LOG
			String result = materials.stream().collect(Collectors.joining(", "));

			System.out.println("MATERIAL WAS DETECTED EMPTY. SO WE ADDED ALL" + result);
		}

		// Call the repository method with the provided parameters
		Page<Product> filteredProducts = productRepo.findFilteredProducts(productType, searchQuery, categories,
				manufacturers, genders, colors, sizes, materials, pageable);

		// Convert the Page<Product> to DTOs using the ProductConverter
		List<ProductDto> productDtos = filteredProducts.getContent().stream().map(productConverter::convertToDTO)
				.collect(Collectors.toList());

		// Create a PageImpl for the final return output
		return new PageImpl<>(productDtos, pageable, filteredProducts.getTotalElements());
	}

	@Override
	public ProductDto readProductDetailsBySku(String sku) throws ProductException {
		Optional<Product> productOptional = productRepo.findBySku(sku);
		Product product = productOptional.orElseThrow(() -> new ProductException("No products found"));

		return productConverter.convertToDTO(product);
	}

	@Override
	public ProductSkuDto readProductSkuByNameAndColor(String productName, int colorId) throws ProductException {
		Optional<Product> productOptional = productRepo.findProductsByNameAndColorGrouped(productName, colorId);
		Product product = productOptional.orElseThrow(() -> new ProductException("No product SKUs found"));

		ProductSkuDto productSkuDto = new ProductSkuDto();
		productSkuDto.setSku(product.getSku());

		return productSkuDto;
	}

	@Override
	public ProductDto readProductDetailsByNameAndColor(String productName, int colorId) throws ProductException {
		Optional<Product> productOptional = productRepo.findProductsByNameAndColorGrouped(productName, colorId);
		Product product = productOptional.orElseThrow(() -> new ProductException("No products found"));

		return productConverter.convertToDTO(product);
	}

}
