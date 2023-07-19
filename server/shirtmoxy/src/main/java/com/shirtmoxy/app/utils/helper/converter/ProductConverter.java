package com.shirtmoxy.app.utils.helper.converter;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.shirtmoxy.app.dto.BarcodeDto;
import com.shirtmoxy.app.dto.CategoryDto;
import com.shirtmoxy.app.dto.ColorDto;
import com.shirtmoxy.app.dto.GenderDto;
import com.shirtmoxy.app.dto.MaterialDto;
import com.shirtmoxy.app.dto.MediaFormatDto;
import com.shirtmoxy.app.dto.ProductDto;
import com.shirtmoxy.app.dto.ProductMediaDto;
import com.shirtmoxy.app.dto.SizeDto;
import com.shirtmoxy.app.dto.VariantDto;
import com.shirtmoxy.app.entity.Barcode;
import com.shirtmoxy.app.entity.Category;
import com.shirtmoxy.app.entity.Color;
import com.shirtmoxy.app.entity.Gender;
import com.shirtmoxy.app.entity.Material;
import com.shirtmoxy.app.entity.MediaFormat;
import com.shirtmoxy.app.entity.Product;
import com.shirtmoxy.app.entity.ProductMedia;
import com.shirtmoxy.app.entity.Size;
import com.shirtmoxy.app.entity.Variant;

@Component
public class ProductConverter implements ObjectConverter<ProductDto, Product> {

	@Override
	public ProductDto convertToDTO(Product entity) {
		ProductDto dto = new ProductDto();

		dto.setId(entity.getId());
		dto.setSku(entity.getSku());
		dto.setProductMediaSet(convertProductMediaSetToDto(entity.getProductMediaSet()));
		dto.setCategory(convertCategoryToDto(entity.getCategory()));
		dto.setName(entity.getName());
		dto.setDescription(entity.getDescription());
		dto.setManufacturer(entity.getManufacturer());
		dto.setWeight(entity.getWeight());
		dto.setUnitPrice(entity.getUnitPrice());
		dto.setVariant(convertVariantToDto(entity.getVariant()));
		dto.setBarcode(convertBarcodeToDto(entity.getBarcode()));
		dto.setActive(entity.isActive());
		dto.setUnitsInStock(entity.getUnitsInStock());
		dto.setDateCreated(entity.getDateCreated());
		dto.setLastUpdated(entity.getLastUpdated());

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
			productMediaDto.setMediaFormat(convertMediaFormatToDto(productMedia.getMediaFormat()));
			productMediaDto.setName(productMedia.getName());
			productMediaDto.setUrl(productMedia.getUrl());

			productMediaDtoSet.add(productMediaDto);
		}

		return productMediaDtoSet;
	}

	public MediaFormatDto convertMediaFormatToDto(MediaFormat mediaFormat) {
		MediaFormatDto mediaFormatDto = new MediaFormatDto();

		mediaFormatDto.setId(mediaFormat.getId());
		mediaFormatDto.setType(mediaFormat.getType());

		return mediaFormatDto;
	}

	public CategoryDto convertCategoryToDto(Category category) {
		CategoryDto categoryDto = new CategoryDto();

		categoryDto.setId(category.getId());
		categoryDto.setName(category.getName());

		return categoryDto;
	}

	private VariantDto convertVariantToDto(Variant variant) {
		VariantDto variantDto = new VariantDto();

		variantDto.setId(variant.getId());
		variantDto.setInventoryCount(variant.getInventoryCount());
		variantDto.setColor(convertColorToDto(variant.getColor()));
		variantDto.setSize(convertSizeToDto(variant.getSize()));
		variantDto.setGender(convertGenderToDto(variant.getGender()));
		variantDto.setMaterial(convertMaterialToDto(variant.getMaterial()));

		return variantDto;
	}

	public ColorDto convertColorToDto(Color color) {
		ColorDto colorDto = new ColorDto();

		colorDto.setId(color.getId());
		colorDto.setColor(color.getColor());

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

	public GenderDto convertGenderToDto(Gender gender) {
		GenderDto genderDto = new GenderDto();

		genderDto.setId(gender.getId());
		genderDto.setType(gender.getType());

		return genderDto;
	}

	public MaterialDto convertMaterialToDto(Material material) {
		MaterialDto materialDto = new MaterialDto();

		materialDto.setId(material.getId());
		materialDto.setMaterial(material.getMaterial());

		return materialDto;
	}

	public BarcodeDto convertBarcodeToDto(Barcode barcode) {
		BarcodeDto barcodeDto = new BarcodeDto();

		barcodeDto.setId(barcode.getId());
		barcodeDto.setType(barcode.getType());

		return barcodeDto;
	}

}
