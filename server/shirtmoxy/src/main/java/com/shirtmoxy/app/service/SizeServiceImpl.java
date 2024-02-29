package com.shirtmoxy.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.shirtmoxy.app.dto.SizeDto;
import com.shirtmoxy.app.entity.Size;
import com.shirtmoxy.app.exception.SizeException;
import com.shirtmoxy.app.repository.SizeRepository;
import com.shirtmoxy.app.utils.helper.converter.ObjectConverter;

@Service
public class SizeServiceImpl implements SizeService {

	@Autowired
	private SizeRepository sizeRepo;

	@Autowired
	@Qualifier("SizeConverter")
	private ObjectConverter<SizeDto, Size> sizeConverter;

	@Override
	public List<SizeDto> readAllSizes() throws SizeException {
		// Create a Sort object to sort by id in ascending order
		Sort sortById = Sort.by(Sort.Direction.ASC, "id");

		List<Size> sizeList = sizeRepo.findAll(sortById);

		// throw exception if none found
		if (sizeList.isEmpty()) {
			throw new SizeException("No sizes found");
		}

		List<SizeDto> sizeDtoList = new ArrayList<>();
		for (Size size : sizeList) {
			SizeDto sizeDto = sizeConverter.convertToDTO(size);
			sizeDtoList.add(sizeDto);
		}

		return sizeDtoList;
	}

	@Override
	public List<SizeDto> readProductSizesByProductName(String name) throws SizeException {
		List<Size> availableSizes = sizeRepo.findAllSizesByProductName(name);

		// check if availableColors is null or empty
		if (availableSizes == null || availableSizes.isEmpty()) {
			throw new SizeException("No available sizes found.");
		}

		List<SizeDto> availableSizesDtos = new ArrayList<>();

		for (Size size : availableSizes) {
			SizeDto availableSizesDto = sizeConverter.convertToDTO(size);
			availableSizesDtos.add(availableSizesDto);
		}

		return availableSizesDtos;
	}
	
	@Override
	public List<SizeDto> readProductInStockSizes(String name, int colorId) throws SizeException {
		List<Size> inStockSizes = sizeRepo.findAllProductInStockSizes(name, colorId, 0);

		// check if availableColors is null or empty
		if (inStockSizes == null || inStockSizes.isEmpty()) {
			throw new SizeException("No available sizes found.");
		}

		List<SizeDto> availableSizesDtos = new ArrayList<>();

		for (Size size : inStockSizes) {
			SizeDto inStockSizesDto = sizeConverter.convertToDTO(size);
			availableSizesDtos.add(inStockSizesDto);
		}

		return availableSizesDtos;
	}
	
	@Override
	public List<SizeDto> readProductOutOfStockSizes(String name, int colorId) throws SizeException {
		List<Size> outOfStockSizes = sizeRepo.findAllProductOutOfStockSizes(name, colorId, 0);

		// check if availableColors is null or empty
		if (outOfStockSizes == null || outOfStockSizes.isEmpty()) {
			throw new SizeException("No available sizes found.");
		}

		List<SizeDto> outOfStockSizesDtos = new ArrayList<>();

		for (Size size : outOfStockSizes) {
			SizeDto outOfStockSizesDto = sizeConverter.convertToDTO(size);
			outOfStockSizesDtos.add(outOfStockSizesDto);
		}

		return outOfStockSizesDtos;
	}

}
