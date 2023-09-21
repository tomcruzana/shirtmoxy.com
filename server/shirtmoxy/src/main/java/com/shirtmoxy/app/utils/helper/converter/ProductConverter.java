package com.shirtmoxy.app.utils.helper.converter;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.shirtmoxy.app.dto.BarcodeDto;
import com.shirtmoxy.app.dto.CategoryDto;
import com.shirtmoxy.app.dto.ColorDto;
import com.shirtmoxy.app.dto.GenderDto;
import com.shirtmoxy.app.dto.ManufacturerDto;
import com.shirtmoxy.app.dto.MaterialDto;
import com.shirtmoxy.app.dto.ProductDto;
import com.shirtmoxy.app.dto.ProductMediaDto;
import com.shirtmoxy.app.dto.ProductTypeDto;
import com.shirtmoxy.app.dto.SizeDto;
import com.shirtmoxy.app.entity.Barcode;
import com.shirtmoxy.app.entity.Category;
import com.shirtmoxy.app.entity.Color;
import com.shirtmoxy.app.entity.Gender;
import com.shirtmoxy.app.entity.Manufacturer;
import com.shirtmoxy.app.entity.Material;
import com.shirtmoxy.app.entity.Product;
import com.shirtmoxy.app.entity.ProductMedia;
import com.shirtmoxy.app.entity.ProductType;
import com.shirtmoxy.app.entity.Size;

@Component
@Qualifier("ProductConverter")
public class ProductConverter implements ObjectConverter<ProductDto, Product> {

	@Override
	public ProductDto convertToDTO(Product entity) {
		ProductDto dto = new ProductDto();

		dto.setId(entity.getId());
		dto.setSku(entity.getSku());
		dto.setName(entity.getName());
		dto.setDescription(entity.getDescription());
		dto.setUnitPrice(entity.getUnitPrice());
		dto.setWeight(entity.getWeight());
		dto.setUnitsInStock(entity.getUnitsInStock());
		dto.setActive(entity.isActive());
		dto.setDateCreated(entity.getDateCreated());
		dto.setLastUpdated(entity.getLastUpdated());

		dto.setProductMediaSet(convertProductMediaSetToDto(entity.getProductMediaSet()));
		dto.setProductType(convertProductTypeToDto(entity.getProductType()));
		dto.setCategory(convertCategoryToDto(entity.getCategory()));
		dto.setManufacturer(convertManufacturerToDto(entity.getManufacturer()));
		dto.setGender(convertGenderToDto(entity.getGender()));
		dto.setColor(convertColorToDto(entity.getColor()));
		dto.setSize(convertSizeToDto(entity.getSize()));
		dto.setMaterial(convertMaterialToDto(entity.getMaterial()));
		dto.setBarcode(convertBarcodeToDto(entity.getBarcode()));

		return dto;
	}

	@Override
	public Product convertToEntity(ProductDto dto) {
		return null;
	}

	private Set<ProductMediaDto> convertProductMediaSetToDto(Set<ProductMedia> productMediaSet) {
		Set<ProductMediaDto> productMediaDtoSet = new HashSet<>();

		for (ProductMedia productMedia : productMediaSet) {
			ProductMediaDto productMediaDto = new ProductMediaDto();

			productMediaDto.setId(productMedia.getId());
			productMediaDto.setName(productMedia.getName());
			productMediaDto.setUrl(productMedia.getUrl());

			productMediaDtoSet.add(productMediaDto);
		}

		return productMediaDtoSet;
	}

	public ProductTypeDto convertProductTypeToDto(ProductType productType) {
		ProductTypeDto productTypeDto = new ProductTypeDto();

		productTypeDto.setId(productType.getId());
		productTypeDto.setName(productType.getName());

		return productTypeDto;
	}

	public CategoryDto convertCategoryToDto(Category category) {
		CategoryDto categoryDto = new CategoryDto();

		categoryDto.setId(category.getId());
		categoryDto.setName(category.getName());

		return categoryDto;
	}

	public ManufacturerDto convertManufacturerToDto(Manufacturer manufacturer) {
		ManufacturerDto manufacturerDto = new ManufacturerDto();

		manufacturerDto.setId(manufacturer.getId());
		manufacturerDto.setName(manufacturer.getName());

		return manufacturerDto;
	}

	public GenderDto convertGenderToDto(Gender gender) {
		GenderDto genderDto = new GenderDto();

		genderDto.setId(gender.getId());
		genderDto.setType(gender.getType());

		return genderDto;
	}

	public ColorDto convertColorToDto(Color color) {
		ColorDto colorDto = new ColorDto();

		colorDto.setId(color.getId());
		colorDto.setName(color.getName());
		colorDto.setRed(color.getRed());
		colorDto.setGreen(color.getGreen());
		colorDto.setBlue(color.getBlue());

		return colorDto;
	}

	public SizeDto convertSizeToDto(Size size) {
		SizeDto sizeDto = new SizeDto();

		sizeDto.setId(size.getId());
		sizeDto.setType(size.getType());
		sizeDto.setLength(size.getLength());
		sizeDto.setWidth(size.getWidth());

		return sizeDto;
	}

	public MaterialDto convertMaterialToDto(Material material) {
		MaterialDto materialDto = new MaterialDto();

		materialDto.setId(material.getId());
		materialDto.setType(material.getType());
		return materialDto;
	}

	public BarcodeDto convertBarcodeToDto(Barcode barcode) {
		BarcodeDto barcodeDto = new BarcodeDto();

		barcodeDto.setId(barcode.getId());
		barcodeDto.setType(barcode.getType());

		return barcodeDto;
	}

}
